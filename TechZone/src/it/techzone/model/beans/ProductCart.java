package it.techzone.model.beans;

public class ProductCart {
	
	public ProductCart(int quantita, Product prodotto) {
		super();
		this.quantita = quantita;
		this.prodotto = prodotto;
	}
	
	public ProductCart() {
		
	}
	
	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Product getProdotto() {
		return prodotto;
	}

	public void setProdotto(Product prodotto) {
		this.prodotto = prodotto;
	}

	@Override
	public String toString() {
		return "ProductCart [quantita=" + quantita + ", prodotto=" + prodotto + "]";
	}

	private int quantita;
	private Product prodotto;
}
