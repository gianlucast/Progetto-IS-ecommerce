package it.techzone.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.dao.DriverManagerConnectionPool;

public class UserDAO {
	private static final String UTENTE_TABLE = "techzone.`utenteregstrato`";
	private static final String MANAGER_TABLE = "techzone.`manager`";

	
	public synchronized boolean doSaveUser(UtenteRegistrato user) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String query="INSERT INTO "+ UTENTE_TABLE+" "
				+ "(NOME, COGNOME, TELEFONO, `PASSWORD`, EMAIL, INDIRIZZO, METODOPAGAMENTO) "
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
}
