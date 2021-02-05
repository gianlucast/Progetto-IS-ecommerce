package it.techzone.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.dao.DriverManagerConnectionPool;

public class UserDAO {
	private static final String UTENTE_TABLE = "techzone.`utenteregstrato`";
	private static final String MANAGER_TABLE = "techzone.`manager`";

	
	public synchronized boolean doSaveUser(UtenteRegistrato user) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
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
		String query="SELECT * FROM "+UTENTE_TABLE+" WHERE EMAIL=? AND `PASSWORD`=SHA1(?)";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
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
		Manager u=null;
		String query="SELECT * FROM "+MANAGER_TABLE+" WHERE EMAIL=? AND `PASSWORD`=SHA1(?)";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				u=new Manager();
				u.setId(rs.getLong("ID"));
				u.setCognome(rs.getString("COGNOME"));
				u.setNome(rs.getString("NOME"));
				u.setEmail(email);
				u.setPassword(rs.getString("PASSWORD"));
				u.setSupportEmail(rs.getString("SUPPORTEMAIL"));
				u.setTelefono(rs.getLong("TELEFONO"));
			}
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
		ResultSet rs;
		String query1="SELECT * FROM "+MANAGER_TABLE+" WHERE EMAIL=?";
		String query2="SELECT * FROM "+UTENTE_TABLE+" WHERE EMAIL=?";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(query1);
			preparedStatement.setString(1, email);
			rs=preparedStatement.executeQuery();
			if(rs.next()) return true;
			
			connection2=DriverManagerConnectionPool.getConnection();
			preparedStatement2=connection.prepareStatement(query2);
			preparedStatement2.setString(1, email);
			rs=preparedStatement.executeQuery();
			if(rs.next()) return true;
			
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
}
