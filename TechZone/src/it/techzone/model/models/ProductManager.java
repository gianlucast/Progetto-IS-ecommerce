package it.techzone.model.models;
import java.sql.SQLException;
import java.util.ArrayList;

import it.techzone.model.beans.*;
import it.techzone.model.dao.*;

public class ProductManager {
	private ProductDAO productdao;
	
	public Product retrieveProduct(long id) throws SQLException {
		productdao=new ProductDAO();
		return productdao.retrieveProductById(id);
	}
	
	public ArrayList<Product> searchProductsByName(String nameProd) throws SQLException{
		productdao=new ProductDAO();
		return productdao.doRetrieveByName(nameProd);
	}
	
	public ArrayList<Product> searchProductsByCat(String categoria) throws SQLException{
		productdao=new ProductDAO();
		return productdao.doRetrieveByCat(categoria);
	}
	
	public boolean deleteProduct(long id) throws SQLException{
		productdao=new ProductDAO();
		return productdao.doDeleteProduct(id);
	}
	
	public boolean updateProduct(long idProdotto, String descrizione, String nomeProd, int quantita, String categoria, String tipo, float costo) throws SQLException {
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
		productdao=new ProductDAO();
		return productdao.doRetrieveAll(ordinevisualizzazione);
	}
}
