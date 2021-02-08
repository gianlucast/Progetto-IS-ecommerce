<%@ page language="java"
	pageEncoding="UTF-8"%>
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.vinylzone.control.*,it.vinylzone.model.*"%>
<html>
<head><title>Fattura VinylZone</title></head>
<body onload="download_test()">
	<script>
		function download_test(){
			window.print();
			window.onafterprint=function(){
				window.history.back();
			}
		}
	
	</script>
	
	<%
		Order ordine=(Order)request.getAttribute("ordine");
		if(ordine==null){
			response.sendRedirect("Catalogo.jsp"); 
			return ;
		}
		ArrayList<CartItem> prodotti=ordine.getOrderline();
	%>
	
	<h3>Il tuo ordine VinylZone:</h3>
	
	
	<table width="100%">
	<tr>
		<td><b>Indirizzo di Spedizione:</b><br>
		<b>Nome: </b><%=ordine.getIndirizzosped().getNome()+" "+ordine.getIndirizzosped().getCognome()%><br>
		<b>Via: </b><%=ordine.getIndirizzosped().getVia()%><br>
		<b>Numero civico: </b><%=ordine.getIndirizzosped().getNumero_civico()%><br>
		<b>Cap: </b><%=ordine.getIndirizzosped().getCap()%><br>
		<b>Comune: </b><%=ordine.getIndirizzosped().getComune()%><br>
	
		<td><b>Indirizzo di Fatturazione:</b><br>
		<b>Nome: </b><%=ordine.getIndirizzofatt().getNome()+" "+ordine.getIndirizzosped().getCognome()%><br>
		<b>Via: </b><%=ordine.getIndirizzofatt().getVia()%><br>
		<b>Numero civico: </b><%=ordine.getIndirizzofatt().getNumero_civico()%><br>
		<b>Cap: </b><%=ordine.getIndirizzofatt().getCap()%><br>
		<b>Comune: </b><%=ordine.getIndirizzofatt().getComune()%><br>
	</tr>
	</table>
	<table border="1" width="100%">
		<th>Codice prodotto</th>
		<th>Nome prodotto</th>
		<th>Qtà</th>
		<th>IVA</th>
		<th>Prezzo totale(iva incl.)</th>
		<%for(int i=0;i<prodotti.size();i++){ %>
		<tr>
			<td><%= prodotti.get(i).getCode()%>
			<td><%= prodotti.get(i).getName()%>
			<td><%= prodotti.get(i).getNum()%>
			<td>22
			<td><%=prodotti.get(i).getTotalPrice()%>
		</tr>
		<%}request.setAttribute("ordine",null);%>
	</table>
</body>
</html>