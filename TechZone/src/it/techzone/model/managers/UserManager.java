package it.techzone.model.managers;
import java.sql.SQLException;

import it.techzone.model.beans.*;
import it.techzone.model.dao.*;

public class UserManager {
	private UserDAO userdao;
	
	public UtenteRegistrato saveUser(String email,String nome, String cognome, String password, long telefono, String indirizzo, String metodoPagamento) throws SQLException {
		userdao=new UserDAO();
		if(emailAlreadyUsed(email)) return null;
		UtenteRegistrato u=new UtenteRegistrato(telefono,nome,cognome,password,email,metodoPagamento,indirizzo);
		userdao.doSaveUser(u);
		return userdao.doRetrieveByMail(email);
	}
	
	public boolean emailAlreadyUsed(String email) throws SQLException {
		userdao=new UserDAO();
		return userdao.checkEmailUsed(email);
	}
	
	public UtenteRegistrato authentication(String email, String password) throws SQLException {
		if(!emailAlreadyUsed(email)) return null;
		userdao=new UserDAO();
		return userdao.authenticate(email, password);
	}
	
	public Manager authenticationManager(String email, String password) throws SQLException{
		if(!emailAlreadyUsed(email)) return null;
		userdao=new UserDAO();
		return userdao.authenticateManager(email, password);
	}
}
