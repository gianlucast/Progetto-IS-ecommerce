package it.techzone.model.beans;

public class UtenteRegistrato extends User{
	private String pagamento,indirizzo;

	
	
	
	public UtenteRegistrato() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UtenteRegistrato(long id, long telefono, String nome, String cognome, String password, String email, String pagamento, String indirizzo) {
		super(id, telefono, nome, cognome, password, email);
		this.pagamento=pagamento;
		this.indirizzo=indirizzo;
	}

	public UtenteRegistrato(long telefono, String nome, String cognome, String password, String email, String pagamento, String indirizzo) {
		super(telefono, nome, cognome, password, email);
		this.pagamento=pagamento;
		this.indirizzo=indirizzo;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	
}
