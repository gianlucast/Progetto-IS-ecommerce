package it.techzone.model.beans;

public class Manager extends User {
private String supportEmail;

public Manager()
{
	
}//Costruttore vuoto

public Manager(long id, long telefono, String nome, String cognome, String password, String email,
		String supportEmail) {
	super(id,telefono,nome,cognome,password,email);
	this.supportEmail = supportEmail;
}//Costruttore completo

public Manager(long telefono, String nome, String cognome, String password, String email, String supportEmail) {
	super(telefono,nome,cognome,password,email);
	this.supportEmail = supportEmail;
}//Costruttore senza ID


//Metodi getters e setters

public String getSupportEmail() {
	return supportEmail;
}

public void setSupportEmail(String supportEmail) {
	this.supportEmail = supportEmail;
}

}
