package it.techzone.model.managers;
import java.sql.SQLException;

import it.techzone.model.beans.*;
import it.techzone.model.dao.*;
import org.apache.commons.validator.routines.EmailValidator;
import java.util.regex.*;

public class UserManager {
	private UserDAO userdao;
	private EmailValidator emailVal=EmailValidator.getInstance();
	
	public UtenteRegistrato saveUser(String email,String nome, String cognome, String password, long telefono, String indirizzo, String metodoPagamento) throws SQLException {
		//Serie di controlli su idoneità dei dati ricevuti
		if(password.length()>18||password.length()<6||password==null||email==null||email.length()<7||email.length()>254||nome==null||nome==""||cognome==null||cognome==""||indirizzo==null||indirizzo==""||metodoPagamento==""||metodoPagamento==null)
			return null;
		if(!emailVal.isValid(email)) {
			return null;
		}
		if(!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$", password)) {
			return null;
		}
		String tel=""+telefono;
		if(tel.length()<8||tel.length()>12) {
			return null;
		}
		
		userdao=new UserDAO();
		if(emailAlreadyUsed(email)) return null; //Altro errore se l'email esiste
		UtenteRegistrato u=new UtenteRegistrato(telefono,nome,cognome,password,email,metodoPagamento,indirizzo);
		userdao.doSaveUser(u);
		return userdao.doRetrieveByMail(email);
	}
	
	public boolean emailAlreadyUsed(String email) throws SQLException {
		if(!emailVal.isValid(email)) return false;
		userdao=new UserDAO();
		return userdao.checkEmailUsed(email);
	}
	
	public UtenteRegistrato authentication(String email, String password) throws SQLException {
		//Serie di controlli su idoneità dei dati ricevuti
		if(password.length()>18||password.length()<6||password==null||email==null||email.length()<7||email.length()>254) return null;
		if(!emailVal.isValid(email)) return null;
		if(!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$", password)) return null;
		if(!emailAlreadyUsed(email)) return null; //Se l'email non è presente nel db, non può avvenire l'accesso
		userdao=new UserDAO();
		return userdao.authenticate(email, password);
	}
	
	public Manager authenticationManager(String email, String password) throws SQLException{
		//Serie di controlli su idoneità dei dati ricevuti
		if(password.length()>18||password.length()<6||password==null||email==null||email.length()<7||email.length()>254) return null;
		if(!emailVal.isValid(email)) return null;
		if(!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$", password)) return null;
		if(!emailAlreadyUsed(email)) return null; //Se l'email non è presente nel db, non può avvenire l'accesso
		userdao=new UserDAO();
		return userdao.authenticateManager(email, password);
	}
}
