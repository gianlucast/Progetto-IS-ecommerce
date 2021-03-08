package it.techzone.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.dao.DriverManagerConnectionPool;

public class UserDAO {
	private static final String UTENTE_TABLE = "techzone.`utenteregistrato`";
	private static final String MANAGER_TABLE = "techzone.`manager`";

	
	public synchronized boolean doSaveUser(UtenteRegistrato user) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		//Metodo attivato in seguito alla registrazione
		String query="INSERT INTO "+ UTENTE_TABLE+" "
				+ "(NOME, COGNOME, TELEFONO, `PASSWORD`, EMAIL, INDIRIZZO, MetodoPagamento) "
				+ "VALUES (?,?,?,SHA1(?),?,?,?);";
		
		try{
			
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getCognome());
			preparedStatement.setLong(3, user.getTelefono());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getIndirizzo());
			preparedStatement.setString(7, user.getPagamento());
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
	
	public synchronized UtenteRegistrato authenticate(String email, String password) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		UtenteRegistrato u=null;
		if(email==null||password==null||email==""||password=="") throw new SQLException();
		//SHA1 perché la password è crittografata
		String query="SELECT * FROM "+UTENTE_TABLE+" WHERE EMAIL=? AND `PASSWORD`=SHA1(?)";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) { //se la query ha un risultato
				u=new UtenteRegistrato();
				u.setId(rs.getLong("ID"));
				u.setCognome(rs.getString("COGNOME"));
				u.setNome(rs.getString("NOME"));
				u.setEmail(email);
				u.setPassword(rs.getString("PASSWORD"));
				u.setIndirizzo(rs.getString("INDIRIZZO"));
				u.setPagamento(rs.getString("metodoPagamento"));
				u.setTelefono(rs.getLong("TELEFONO"));
			}
			connection.commit();
			//u è null se non viene trovata corrispondenza nel db.
			return u;
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}
	
	public synchronized Manager authenticateManager(String email, String password) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		if(email==null||password==null||email==""||password=="") throw new SQLException();
		Manager u=null;
		//SHA1 perché la password è crittografata
		String query="SELECT * FROM "+MANAGER_TABLE+" WHERE EMAIL=? AND `PASSWORD`=SHA1(?)";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) { //se viene trovato un risultato.
				u=new Manager();
				u.setId(rs.getLong("ID"));
				u.setCognome(rs.getString("COGNOME"));
				u.setNome(rs.getString("NOME"));
				u.setEmail(email);
				u.setPassword(rs.getString("PASSWORD"));
				u.setSupportEmail(rs.getString("SUPPORTEMAIL"));
				u.setTelefono(rs.getLong("TELEFONO"));
			}
			connection.commit();
			//u è null se non viene trovata corrispondenza nel db.
			return u;
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}
	
	public synchronized boolean checkEmailUsed(String email) throws SQLException {
		Connection connection=null;
		Connection connection2=null;
		PreparedStatement preparedStatement=null;
		PreparedStatement preparedStatement2 = null;
		if(email==null||email=="") throw new SQLException();
		ResultSet rs;
		String query1="SELECT * FROM "+MANAGER_TABLE+" WHERE EMAIL=?";
		String query2="SELECT * FROM "+UTENTE_TABLE+" WHERE EMAIL=?";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(query1);
			preparedStatement.setString(1, email);
			rs=preparedStatement.executeQuery();
			if(rs.next()) { //Se viene trovato un Manager con quella mail
				return true;
			}
			
			connection2=DriverManagerConnectionPool.getConnection();
			preparedStatement2=connection.prepareStatement(query2);
			preparedStatement2.setString(1, email);
			rs=preparedStatement2.executeQuery();
			if(rs.next()) { //Se viene trovato un Utente Registrato con quella mail
				connection.commit();
				return true;
			}
			connection.commit();
			//se non viene trovata corrispondenza né tra i manager né tra gli utenti
			return false;
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (preparedStatement2 != null)
					preparedStatement2.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection2);
			}
		}
	}
	
	public synchronized UtenteRegistrato doRetrieveByMail(String email) throws SQLException {
		Connection connection2=null;
		PreparedStatement preparedStatement2 = null;
		ResultSet rs;
		UtenteRegistrato utente=null;
		String query2="SELECT * FROM "+UTENTE_TABLE+" WHERE EMAIL=?";
		
		try {
			
			connection2=DriverManagerConnectionPool.getConnection();
			preparedStatement2=connection2.prepareStatement(query2);
			preparedStatement2.setString(1, email);
			rs=preparedStatement2.executeQuery();
			while(rs.next()) { //se viene trovato un risultato.
				utente=new UtenteRegistrato();
				utente.setId(rs.getLong("ID"));
				utente.setCognome(rs.getString("COGNOME"));
				utente.setNome(rs.getString("NOME"));
				utente.setEmail(email);
				utente.setPassword(rs.getString("PASSWORD"));
				utente.setIndirizzo(rs.getString("INDIRIZZO"));
				utente.setPagamento(rs.getString("metodoPagamento"));
				utente.setTelefono(rs.getLong("TELEFONO"));
			}
			connection2.commit();
			//utente è null se non viene trovato nessun utente con la mail passata come parametro
			return utente;
		}finally {
			try {
				if (preparedStatement2 != null)
					preparedStatement2.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection2);
			}
		}
	}
	
	public synchronized UtenteRegistrato doRetrieveById(long id) throws SQLException {
		Connection connection2=null;
		PreparedStatement preparedStatement2 = null;
		ResultSet rs;
		UtenteRegistrato utente=null;
		String query2="SELECT * FROM "+UTENTE_TABLE+" WHERE id=?";
		
		try {
			
			connection2=DriverManagerConnectionPool.getConnection();
			preparedStatement2=connection2.prepareStatement(query2);
			preparedStatement2.setFloat(1, id);
			rs=preparedStatement2.executeQuery();
			while(rs.next()) {
				utente=new UtenteRegistrato();
				utente.setId(rs.getLong("ID"));
				utente.setCognome(rs.getString("COGNOME"));
				utente.setNome(rs.getString("NOME"));
				utente.setEmail(rs.getString("email"));
				utente.setPassword(rs.getString("PASSWORD"));
				utente.setIndirizzo(rs.getString("INDIRIZZO"));
				utente.setPagamento(rs.getString("metodoPagamento"));
				utente.setTelefono(rs.getLong("TELEFONO"));
			}
			connection2.commit();
			//utente è null se non viene trovato un utente con l'id passato come parametro nel db
			return utente;
		}finally {
			try {
				if (preparedStatement2 != null)
					preparedStatement2.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection2);
			}
		}
	}
}
