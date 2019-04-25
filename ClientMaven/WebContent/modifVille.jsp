<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.blo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier une ville</title>
</head>
<body>
	<% if(session.getAttribute("message")!=null){ %>
	<h2><%= session.getAttribute("message") %></h2><br>
	<%  session.removeAttribute("message");
		} 
		Ville villeModif = (Ville)session.getAttribute("villeModif");%>
	<form method="post" action="PutServlet">
		<label for="codeCommuneINSEE">Code commune INSEE</label>
		<input id="codeCommuneINSEE" name="codeCommuneINSEE" type="number" min="00001" max="99999" value="<%=villeModif.getCodeCommuneINSEE() %>" required/>
		<br>
		
		<label for="nomCommune">Nom commune</label>
		<input id="nomCommune" name="nomCommune" type="text" value="<%=villeModif.getNomCommune() %>" required/>
		<br>
		
		<label for="codePostal">Code postal</label>
		<input id="codePostal" name="codePostal" type="number" min="00001" max="99999" value="<%=villeModif.getCodePostal() %>" required/>
		<br>
		
		<label for="libelleAcheminement">Libelle acheminement</label>
		<input id="libelleAcheminement" name="libelleAcheminement" type="text" value="<%=villeModif.getLibelleAcheminement() %>" required/>
		<br>
		
		<label for="ligne5">Ligne 5</label>
		<input id="ligne5" name="ligne5" type="text" value="<%=villeModif.getLigne5() %>" />
		<br>
		
		<label for="longitude">Longitude</label>
		<input id="longitude" name="longitude" type="number" step="0.0000000001" value="<%=villeModif.getLongitude() %>" required/>
		<br>
		
		<label for="latitude">Latitude</label>
		<input id="latitude" name="latitude" type="number" step="0.0000000001" value="<%=villeModif.getLatitude() %>" required/>
		<br>
		
		<input type="submit" name="modifier" value="Modifier">
	</form><br>
	
	<form method="post" action="PutServlet">
		<input type="submit" name="supprimer" value="Supprimer">
	</form>
	<br>

	<a href="index.jsp">Retour</a>
</body>
</html>