package it.techzone.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import it.techzone.model.beans.Order;
import it.techzone.model.beans.Product;
import it.techzone.model.beans.ProductOrder;
import it.techzone.model.beans.UtenteRegistrato;





public class OrderDao {
	private static final String ORDER_TABLE = "techzone.`order`";
	private static final String PRODUCTORDER_TABLE="techzone.productorder";
	
	public synchronized boolean doSaveOrder(Order order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		//Prima parte del salvataggio di un ordine. La query inserisce i dati nella tabella degli ordini.
		String insertSQL="INSERT INTO "+ORDER_TABLE+" (dataInvio, totale, stato, idUtente) "
				+ "VALUES (?,?,?,?)";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			preparedStatement.setTimestamp(1, timestamp);
			preparedStatement.setFloat(2, order.getTotale());
			preparedStatement.setString(3, "In preparazione");
			preparedStatement.setLong(4, order.getUtente().getId());
			
			preparedStatement.executeUpdate();
			connection.commit();
			//Non posseggo il codice dell'ordine appena inserito, essendo questo generato automaticamente dal DBMS. Quindi seleziono l'ultimo ordine effettuato dall'utente
			String selectSQL="SELECT MAX(numeroOrdine) FROM "+ORDER_TABLE+" WHERE `idUtente`=?";
			preparedStatement2=connection.prepareStatement(selectSQL);
			preparedStatement2.setFloat(1, order.getUtente().getId());
			
			ResultSet rs=preparedStatement2.executeQuery();
			rs.next();
			long idorder=rs.getLong(1); //Prendo l'id dell'ordine appena inserito.
			//Questa query è quella che, per ogni prodotto acquistato, aggiorna la tabella dei ProductOrder.
			String insert2="INSERT INTO "+PRODUCTORDER_TABLE+" (numeroOrdine, codiceProdotto, costo, quantita) "
					+ "VALUES (?,?,?,?)";
			
			ArrayList<ProductOrder> prodotti=order.getProdotti();
			for(ProductOrder prodottoOrdine:prodotti) {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insert2);
				ProductDAO prendiProd=new ProductDAO();
				//Prendo il prodotto in questo modo per poterne in seguito modificare la qta disponibile nel DB.
				Product prodottoaggiornato= prendiProd.retrieveProductById(prodottoOrdine.getProdotto().getCodice());
				preparedStatement.setLong(1, idorder);
				preparedStatement.setLong(2, prodottoaggiornato.getCodice());
				preparedStatement.setFloat(3, prodottoaggiornato.getCosto());
				preparedStatement.setInt(4, prodottoOrdine.getQuantita());
				preparedStatement.executeUpdate();
				connection.commit();
				
				//MODIFICA QTA DISPONIBILE NEL DB
				prodottoaggiornato.setQuantita(prodottoaggiornato.getQuantita()-prodottoOrdine.getQuantita());
				prendiProd.doUpdate(prodottoaggiornato);
			}
			return true;
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (preparedStatement2 != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}
	
	public boolean doUpdateOrder(Order order) throws SQLException {
		if(order.getNumeroOrdine()<0) throw new SQLException();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query="";
		int tipo=0;
		if(order.getStato().equalsIgnoreCase("Consegnato")) {
			//Se l'ordine è stato consegnato, inseriamo anche la data di arrivo
			query="UPDATE "+ORDER_TABLE+" SET stato='Consegnato', dataArrivo=? WHERE numeroOrdine=?";
		}else {
			query="UPDATE "+ORDER_TABLE+" SET stato=? WHERE numeroOrdine=?";
			tipo=1;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			if(tipo==0) {
				//Data correte
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				preparedStatement.setTimestamp(1, timestamp);
			}else 
				preparedStatement.setString(1, order.getStato());
			preparedStatement.setLong(2, order.getNumeroOrdine());
			preparedStatement.executeUpdate();
			connection.commit();
			return true;
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public Order retrieveOrderById(long id) throws SQLException {
		//if(id<0) throw new SQLException();
		Order ordine=null;
		ArrayList<ProductOrder> prodotti=new ArrayList<ProductOrder>();
		ProductDAO productdao=new ProductDAO();
		UserDAO userdao=new UserDAO();
		Product prodotto;
		long codiceProdotto;
		float costo;
		int quantita;
		Connection connection = null;
		Connection connection2=null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		//Prima query che va a prendere i dati dell'ordine esclusi i prodotti in esso incluso.
		String select="SELECT * FROM "+ORDER_TABLE+" WHERE numeroOrdine=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(select);
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				//Se c'è un ordine con quell'id entra nell'if
				ordine=new Order();
				ordine.setNumeroOrdine(id);
				ordine.setDataInvio(rs.getTimestamp("dataInvio"));
				ordine.setStato(rs.getString("stato"));
				//Se è consegnato, sarà presente anche la data di arrivo.
				if(ordine.getStato().equalsIgnoreCase("Consegnato"))
					ordine.setDataArrivo(rs.getTimestamp("dataArrivo"));
				else
					ordine.setDataArrivo(null);
				ordine.setTotale(rs.getFloat("totale"));
				ordine.setUtente(userdao.doRetrieveById(rs.getLong("idUtente")));
				
				//RIEMPIMENTO CON PRODOTTI DI QUELL'ORDINE
				String select2= "SELECT * FROM "+PRODUCTORDER_TABLE+" WHERE numeroOrdine= ?";
				connection2 = DriverManagerConnectionPool.getConnection();
				preparedStatement2 = connection.prepareStatement(select2);
				preparedStatement2.setLong(1, ordine.getNumeroOrdine());
				ResultSet rs2 = preparedStatement2.executeQuery();
				while(rs2.next()) {
					codiceProdotto=rs2.getLong("codiceProdotto");
					prodotto=productdao.retrieveProductById(codiceProdotto);
					costo=rs2.getFloat("costo");
					quantita=rs2.getInt("quantita");
					prodotti.add(new ProductOrder(prodotto,costo,quantita));
				}
				ordine.setProdotti(prodotti);
				connection.commit();
				connection2.commit();
			}
			//CHE SIA NULL (Se non è stato trovato nessun ordine con quell'id) o un oggetto consistente.
			return ordine;
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (preparedStatement2 != null)
					preparedStatement2.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				DriverManagerConnectionPool.releaseConnection(connection2);
			}
		}
		
	}
	
	
	public ArrayList<Order> retrieveOrdersByMail(String email) throws SQLException{
		if(email==null||email=="") throw new SQLException();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Connection connection2 = null;
		PreparedStatement preparedStatement2 = null;
		UserDAO userdao=new UserDAO();
		ArrayList<Order> ordini =new ArrayList<Order>();
		//SELEZIONO IL CODICE IDENTIFICATIVO DELL'UTENTE CHE HA QUELLA MAIL (POSSIBILITA' CHE LA MAIL VARI, L'ID NON CAMBIA).
		UtenteRegistrato utente=userdao.doRetrieveByMail(email);
		if(utente==null) return ordini;
		//SELEZIONO TUTTI GLI ORDINI EFFETTUATI DALL'UTENTE CHE HA QUELL'ID
		String selectSQL = "SELECT * FROM " + ORDER_TABLE + " WHERE idUtente = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setLong(1, utente.getId());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				//PER OGNUNO DI QUESTI, INVECE DI ANDARE AD EFFETTUARE UNA NUOVA PROCEDURA DI RIEMPIMENTO, RICHIAMO QUELLA GIA' FATTA.
				ordini.add(retrieveOrderById(rs.getLong("numeroOrdine")));
			}
			connection.commit();
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (preparedStatement2 != null)
					preparedStatement2.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				DriverManagerConnectionPool.releaseConnection(connection2);
			}
		}
		connection.commit();
		return ordini;
	}
		
}


