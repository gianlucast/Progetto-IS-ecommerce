<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./css/lateralmenu.css">

</head>
<body>

<div id="mySidepanel" class="sidepanel">
 <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <p>Elettronica</p>
            <a href="productcataloguecontrol?by=categoria&q=Smartphone">Smartphone</a>
             <a href="productcataloguecontrol?by=categoria&q=TV">TV</a>
             <a href="productcataloguecontrol?by=categoria&q=Console">Console</a>
             <a href="productcataloguecontrol?by=categoria&q=Tablet">Tablet</a>
             <a href="productcataloguecontrol?by=categoria&q=Computer">Computer</a>
  <p>Altro</p>
             <a href="productcataloguecontrol?by=categoria&q=Frigoriferi">Frigoriferi</a>
             <a href="productcataloguecontrol?by=categoria&q=Fotografia">Fotografia</a>
             <a href="productcataloguecontrol?by=categoria&q=Lavatrici">Lavatrici</a>
             <a href="productcataloguecontrol?by=categoria&q=Condizionatori">Condizionatori</a>
</div>

<button class="openbtn" onclick="openNav()">☰  </button> 



<script>
function openNav() {
  document.getElementById("mySidepanel").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidepanel").style.width = "0";
}
</script>
   
</body>
</html> 
