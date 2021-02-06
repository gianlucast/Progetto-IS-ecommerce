package it.techzone.model.beans;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {

	
	
	
	public Order(long numeroOrdine, Timestamp dataInvio, Timestamp dataArrivo, float totale, String stato, UtenteRegistrato utente,
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

	public Timestamp getDataInvio() {
		return dataInvio;
	}

	public void setDataInvio(Timestamp dataInvio) {
		this.dataInvio = dataInvio;
	}

	public Timestamp getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(Timestamp dataArrivo) {
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

	public UtenteRegistrato getUtente() {
		return utente;
	}

	public void setUtente(UtenteRegistrato utente) {
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
	private Timestamp dataInvio;
	private Timestamp dataArrivo;
	private float totale;
	private String stato;
	private UtenteRegistrato utente;
	private ArrayList<ProductOrder> prodotti;
}
