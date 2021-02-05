package it.techzone.model.beans;

//creato da gianluca, modificato da gerardo

public class Product {

	public Product(long codice, float costo, String immagine, String descrizione, String nomeProd, int quantita, String categoria, String tipo) {
		
		this.codice = codice;
		this.costo = costo;
		this.immagine = immagine;
		this.descrizione = descrizione;
		this.nomeProd = nomeProd;
        this.quantita = quantita;
        this.categoria = categoria;
        this.tipo = tipo;
	}
	
	public Product(){
		
	}
	
	public long getCodice() {
		return codice;
	}

	public void setCodice(long codice) {
		this.codice = codice;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNomeProd() {
		return nomeProd;
	}

	public void setNomeProd(String nomeProd) {
		this.nomeProd = nomeProd;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Product [codice=" + codice + ", costo=" + costo + ", immagine=" + immagine + ", descrizione="
				+ descrizione + ", nomeProd=" + nomeProd + ", quantita=" + quantita + ", categoria=" + categoria
				+ ", tipo=" + tipo + "]";
	}

	private long codice;
	private float costo;
	private String immagine;
	private String descrizione;
	private String nomeProd;
	private int quantita;
	private String categoria;
	private String tipo;
}
