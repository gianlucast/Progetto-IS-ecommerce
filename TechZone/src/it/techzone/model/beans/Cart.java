package it.techzone.model.beans;

import java.util.ArrayList;

public class Cart {
	
	public Cart(ArrayList<ProductCart> productList, float prezzoTotale) {
		super();
		this.productList = productList;
		this.prezzoTotale = prezzoTotale;
	}
	
	public Cart() {
		
	}
	
	public ArrayList<ProductCart> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<ProductCart> productList) {
		this.productList = productList;
	}

	public float getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(float prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	@Override
	public String toString() {
		return "Cart [productList=" + productList + ", prezzoTotale=" + prezzoTotale + "]";
	}

	private ArrayList<ProductCart> productList;
	private float prezzoTotale;
}
