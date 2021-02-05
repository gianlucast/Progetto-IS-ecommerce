package it.techzone.model.beans;

import java.sql.Date;
import java.util.ArrayList;

public class Order {

	
	
	
	public Order(long numeroOrdine, Date dataInvio, Date dataArrivo, float totale, String stato, User utente,
			ArrayList<ProductOrder> prodotti) {
		
		this.numeroOrdine = numeroOrdine;
		this.dataInvio = dataInvio;
		this.dataArrivo = dataArrivo;
		this.totale = totale;
		this.stato = stato;
		this.utente = utente;
		this.prodotti = prodotti;
	}
	
	public Order() {
		
	}
	
	public long getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(long numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public Date getDataInvio() {
		return dataInvio;
	}

	public void setDataInvio(Date dataInvio) {
		this.dataInvio = dataInvio;
	}

	public Date getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public float getTotale() {
		return totale;
	}

	public void setTotale(float totale) {
		this.totale = totale;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

	public ArrayList<ProductOrder> getProdotti() {
		return prodotti;
	}

	public void setProdotti(ArrayList<ProductOrder> prodotti) {
		this.prodotti = prodotti;
	}

	
	@Override
	public String toString() {
		return "Order [numeroOrdine=" + numeroOrdine + ", dataInvio=" + dataInvio + ", dataArrivo=" + dataArrivo
				+ ", totale=" + totale + ", stato=" + stato + ", utente=" + utente + ", prodotti=" + prodotti + "]";
	}


	private long numeroOrdine;
	private Date dataInvio;
	private Date dataArrivo;
	private float totale;
	private String stato;
	private User utente;
	private ArrayList<ProductOrder> prodotti;
}
