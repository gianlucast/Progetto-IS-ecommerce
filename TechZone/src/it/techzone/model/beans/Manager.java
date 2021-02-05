package it.techzone.model.beans;

public class Manager {
private long id,telefono;
private String nome,cognome,password,email,supportEmail;

public Manager()
{
	
}//Costruttore vuoto

public Manager(long id, long telefono, String nome, String cognome, String password, String email,
		String supportEmail) {
	super();
	this.id = id;
	this.telefono = telefono;
	this.nome = nome;
	this.cognome = cognome;
	this.password = password;
	this.email = email;
	this.supportEmail = supportEmail;
}//Costruttore completo

public Manager(long telefono, String nome, String cognome, String password, String email, String supportEmail) {
	super();
	this.telefono = telefono;
	this.nome = nome;
	this.cognome = cognome;
	this.password = password;
	this.email = email;
	this.supportEmail = supportEmail;
}//Costruttore senza ID


//Metodi getters e setters
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public long getTelefono() {
	return telefono;
}

public void setTelefono(long telefono) {
	this.telefono = telefono;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getCognome() {
	return cognome;
}

public void setCognome(String cognome) {
	this.cognome = cognome;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getSupportEmail() {
	return supportEmail;
}

public void setSupportEmail(String supportEmail) {
	this.supportEmail = supportEmail;
}

}
