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

	public boolean addToCart(Product prodotto, int quantita) {
		ProductCart item=new ProductCart(quantita,prodotto);
		if(productList.size()>0) {
			boolean flag=false;
			for(int i=0;i<productList.size()&&!flag;i++) {
				if(productList.get(i).getProdotto().getCodice()==prodotto.getCodice()) {
					if((productList.get(i).getQuantita()+quantita)>prodotto.getQuantita()) {
						productList.get(i).setQuantita(prodotto.getQuantita());
						return false;
					}
					else {
						productList.get(i).setQuantita(productList.get(i).getQuantita()+quantita);
						return true;
					}
							
				}
			}
			productList.add(item);
			return true;
		}else {
			productList.add(item);
			return true;
		}
	}
	
	@Override
	public String toString() {
		return "Cart [productList=" + productList + ", prezzoTotale=" + prezzoTotale + "]";
	}

	private ArrayList<ProductCart> productList;
	private float prezzoTotale;
}
