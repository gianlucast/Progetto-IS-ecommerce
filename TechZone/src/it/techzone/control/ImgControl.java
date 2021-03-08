package it.techzone.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Product;
import it.techzone.model.managers.ProductManager;


@WebServlet("/imgControl")
public class ImgControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductManager pm= new ProductManager();
    public ImgControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session= request.getSession();
			try {
				if(request.getParameter("id")!=null) {
					Product prod=pm.retrieveProduct(Long.parseLong(request.getParameter("id")));
					//System.out.println(prod.getName());
					byte[] photo=prod.getImmagine();
					ServletOutputStream out = response.getOutputStream();
					if(photo != null)
					{
						out.write(photo);
						response.setContentType("image/jpeg");			
					}
					out.close();
				}
			} 	
			catch(Exception e2) {
		
				session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
				response.sendRedirect("./HomePage.jsp");	
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
