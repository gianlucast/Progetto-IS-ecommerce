package it.techzone.model.managers;
import java.sql.SQLException;
import java.util.ArrayList;

import it.techzone.model.beans.*;
import it.techzone.model.dao.*;

public class ProductManager {
	private ProductDAO productdao;
	
	public Product retrieveProduct(long id) throws SQLException {
		productdao=new ProductDAO();
		return productdao.retrieveProductById(id); //ritorna il prodotto con quell'id
	}
	
	public ArrayList<Product> searchProductsByName(String nameProd) throws SQLException{
		if(nameProd==null||nameProd=="") return null;
		productdao=new ProductDAO();
		return productdao.doRetrieveByName(nameProd); //ritorna la lista dei prodotti con corrispondenza al nomeProd
	}
	
	public ArrayList<Product> searchProductsByCat(String categoria) throws SQLException{
		if(categoria==null||categoria=="") return null;
		productdao=new ProductDAO();
		return productdao.doRetrieveByCat(categoria); //ritorna la lista dei prodotti con corrispondenza alla categoria
	}
	
	public boolean deleteProduct(long id) throws SQLException{
		productdao=new ProductDAO();
		return productdao.doDeleteProduct(id); //ritorna il risultato dell'eliminazione (true se è andata a buon fine, false altrimenti).
	}
	
	public boolean updateProduct(long idProdotto, String descrizione, String nomeProd, int quantita, String categoria, String tipo, float costo) throws SQLException {
		if(nomeProd==null||nomeProd==""||categoria==null||categoria==""||descrizione==""||descrizione==null||quantita<0||tipo==""||tipo==null||costo<=0||idProdotto<=0) return false;
		Product p=new Product();
		p.setCategoria(categoria);
		p.setCodice(idProdotto);
		p.setCosto(costo);
		p.setDescrizione(descrizione);
		p.setNomeProd(nomeProd);
		p.setQuantita(quantita);
		p.setTipo(tipo);
		productdao=new ProductDAO();
		return productdao.doUpdate(p);
	}
	
	public ArrayList<Product> getAllProducts(String ordinevisualizzazione) throws SQLException{
		if(ordinevisualizzazione==null) return null;
		productdao=new ProductDAO();
		return productdao.doRetrieveAll(ordinevisualizzazione); //restituisce la lista di tutti i prodotti (non eliminati) del DB
	}
}
