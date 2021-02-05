package it.techzone.model.beans;

public class ProductOrder {
	private Product prodotto;
	private float costo;
	private int quantita;
	
	
	public ProductOrder() {
		super();
	}

	public ProductOrder(Product prodotto, float costo, int quantita) {
		super();
		this.prodotto = prodotto;
		this.costo = costo;
		this.quantita = quantita;
	}
	
	public Product getProdotto() {
		return prodotto; 
	}
	public void setProdotto(Product prodotto) {
		this.prodotto = prodotto;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	
}
