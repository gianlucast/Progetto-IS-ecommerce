package it.techzone.model.beans;

public class User {
private	long id,telefono;
private	String nome,cognome,password,email,indirizzo,metodoPagamento;
public User()
{
	
}//costruttore vuoto



public User(long id, long telefono, String nome, String cognome, String password, String email, String indirizzo,
		String metodoPagamento) {
	super();
	this.id = id;
	this.telefono = telefono;
	this.nome = nome;
	this.cognome = cognome;
	this.password = password;
	this.email = email;
	this.indirizzo = indirizzo;
	this.metodoPagamento = metodoPagamento;
}//costruttore completo

public User(long telefono, String nome, String cognome, String password, String email, String indirizzo,
		String metodoPagamento) {
	super();
	this.telefono = telefono;
	this.nome = nome;
	this.cognome = cognome;
	this.password = password;
	this.email = email;
	this.indirizzo = indirizzo;
	this.metodoPagamento = metodoPagamento;
}//costruttore senza ID


//Metodi getters and setters
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
public String getIndirizzo() {
	return indirizzo;
}
public void setIndirizzo(String indirizzo) {
	this.indirizzo = indirizzo;
}
public String getMetodoPagamento() {
	return metodoPagamento;
}
public void setMetodoPagamento(String metodoPagamento) {
	this.metodoPagamento = metodoPagamento;
}
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

}
