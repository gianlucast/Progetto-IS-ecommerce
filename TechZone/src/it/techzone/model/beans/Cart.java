package it.techzone.model.beans;

import java.util.ArrayList;

public class Cart {
	
	public Cart(ArrayList<ProductCart> productList, float prezzoTotale) {
		super();
		this.productList = productList;
		this.prezzoTotale = prezzoTotale;
	}
	
	public Cart() {
		productList=new ArrayList<ProductCart>();
	}
	
	public ArrayList<ProductCart> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<ProductCart> productList) {
		this.productList = productList;
		calcolaPrezzoTotale();
	}

	public float getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(float prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	
	private void calcolaPrezzoTotale() {
		prezzoTotale=0;
		for(int i=0;i<productList.size();i++) {
			prezzoTotale+=(productList.get(i).getQuantita()*productList.get(i).getProdotto().getCosto());
		}
	}

	public int isInCart(Product p) {
		if(productList.size()<1) return -1;
		for(int i=0;i<productList.size();i++) {
			if(productList.get(i).getProdotto().getCodice()==p.getCodice())
				return i;
		}
		return -1;
	}

	public boolean addToCart(Product prodotto, int quantita) {
		ProductCart item=new ProductCart(quantita,prodotto);
		int pos=isInCart(prodotto);
		if(pos>-1) {
			if((productList.get(pos).getQuantita()+quantita)>prodotto.getQuantita()) {
				productList.get(pos).setQuantita(prodotto.getQuantita());
				calcolaPrezzoTotale();
				return false;
			}
			else {
				productList.get(pos).setQuantita(productList.get(pos).getQuantita()+quantita);
				calcolaPrezzoTotale();
				return true;
			}
		}
		productList.add(item);
		calcolaPrezzoTotale();
		return true;
	}
	
	public boolean removeFromCart(Product prodotto) {
		int pos=isInCart(prodotto);
		if(pos==-1) return false;
		productList.remove(pos);
		calcolaPrezzoTotale();
		return true;
	}

	public boolean setQuantityInCart(Product prodotto, int quantita) {
		int pos=isInCart(prodotto);
		if(pos<0) return false;
		if(quantita<=0) return removeFromCart(prodotto);
		if(quantita>prodotto.getQuantita()) return false;
		productList.get(pos).setQuantita(quantita);
		//aggiunta del calcolo prezzo dopo la modifica del carrello?
		calcolaPrezzoTotale();
		return true;
	}

	@Override
	public String toString() {
		return "Cart [productList=" + productList + ", prezzoTotale=" + prezzoTotale + "]";
	}

	private ArrayList<ProductCart> productList;
	private float prezzoTotale;
}
