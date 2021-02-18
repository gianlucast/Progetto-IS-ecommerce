package it.techzone.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import it.techzone.model.beans.Product;





public class ProductDAO {
	private static final String PRODUCT_TABLE = "techzone.`PRODUCT`";
	
	public synchronized Product retrieveProductById(long id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " +PRODUCT_TABLE+ " WHERE codiceProdotto = ? AND eliminato=0";
		Product p=null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setLong(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				p=new Product();
				p.setCodice(id);
				p.setCategoria(rs.getString("CATEGORIA"));
				p.setTipo(rs.getString("TIPO"));
				p.setDescrizione(rs.getString("DESCRIZIONE"));
				p.setNomeProd(rs.getString("nomeProd"));
				p.setQuantita(rs.getInt("QUANTITA"));
				p.setCosto(rs.getFloat("COSTO"));
				p.setImmagine(rs.getBytes("IMMAGINE"));
			}
			connection.commit();
			return p;
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public synchronized boolean doDeleteProduct(long id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "UPDATE " + PRODUCT_TABLE + " SET eliminato = 1 WHERE codiceProdotto= ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setLong(1, id);

			result = preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	public synchronized ArrayList<Product> doRetrieveAll(String ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Product> products = new ArrayList<Product>();

		String selectSQL = "SELECT * FROM " + PRODUCT_TABLE+" WHERE eliminato=0";

		if (ordine != null && !ordine.equals("")) {
			selectSQL += " ORDER BY " + ordine;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
	
			while(rs.next()) {
				Product p=new Product();
				p.setCodice(rs.getLong("codiceProdotto"));
				p.setCategoria(rs.getString("CATEGORIA"));
				p.setTipo(rs.getString("TIPO"));
				p.setDescrizione(rs.getString("DESCRIZIONE"));
				p.setNomeProd(rs.getString("nomeProd"));
				p.setQuantita(rs.getInt("QUANTITA"));
				p.setCosto(rs.getFloat("COSTO"));
				p.setImmagine(rs.getBytes("IMMAGINE"));
				products.add(p);
			}
			connection.commit();
			return products;
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public ArrayList<Product> doRetrieveByCat(String cat) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			if(cat==null||cat=="") throw new SQLException();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM "+PRODUCT_TABLE+" WHERE categoria=? AND eliminato=0");
			ps.setString(1, cat);
			ArrayList<Product> prodotti = new ArrayList<Product>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product p=new Product();
				p.setCodice(rs.getLong("codiceProdotto"));
				p.setCategoria(rs.getString("CATEGORIA"));
				p.setTipo(rs.getString("TIPO"));
				p.setDescrizione(rs.getString("DESCRIZIONE"));
				p.setNomeProd(rs.getString("nomeProd"));
				p.setQuantita(rs.getInt("QUANTITA"));
				p.setCosto(rs.getFloat("COSTO"));
				p.setImmagine(rs.getBytes("IMMAGINE"));
				prodotti.add(p);
			}
			con.commit();
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Product> doRetrieveByName(String nameProd) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			if(nameProd==null||nameProd=="") throw new SQLException();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM "+PRODUCT_TABLE+" WHERE nomeProd LIKE ? AND eliminato=0");
			ps.setString(1, "%"+nameProd+"%");
			ArrayList<Product> prodotti = new ArrayList<Product>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product p=new Product();
				p.setCodice(rs.getLong("codiceProdotto"));
				p.setCategoria(rs.getString("CATEGORIA"));
				p.setTipo(rs.getString("TIPO"));
				p.setDescrizione(rs.getString("DESCRIZIONE"));
				p.setNomeProd(rs.getString("nomeProd"));
				p.setQuantita(rs.getInt("QUANTITA"));
				p.setCosto(rs.getFloat("COSTO"));
				p.setImmagine(rs.getBytes("IMMAGINE"));
				prodotti.add(p);
			}
			con.commit();
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public synchronized boolean doUpdate(Product prodotto) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL="UPDATE "+PRODUCT_TABLE+" SET nomeProd=?, descrizione=?, quantita=?, categoria=?, tipo=?, costo=?"
				+ " WHERE codiceProdotto=?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, prodotto.getNomeProd());
			preparedStatement.setString(2, prodotto.getDescrizione());
			preparedStatement.setInt(3, prodotto.getQuantita());
			preparedStatement.setString(4, prodotto.getCategoria());
			preparedStatement.setString(5, prodotto.getTipo());
			preparedStatement.setFloat(6, prodotto.getCosto());
			preparedStatement.setLong(7, prodotto.getCodice());
			int result=preparedStatement.executeUpdate();
			connection.commit();
			return (result>0);
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

}
