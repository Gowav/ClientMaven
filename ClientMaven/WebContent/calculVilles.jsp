<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.blo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Calculer la distance entre 2 villes</title>
</head>
<body>
	<% if(session.getAttribute("distance")!=null){ %>
	<h2><%= session.getAttribute("nomA") %> - <%= session.getAttribute("nomB") %> = <%= session.getAttribute("distance") %> km</h2><br>
	<%  session.removeAttribute("nomA");
		session.removeAttribute("nomB");
		session.removeAttribute("distance");
		} %>
		
	<form method="post" action="CalculServlet">
		Ville de départ:<br>
		<select name="villeA">
			<% List<Ville> liste = (ArrayList<Ville>) session.getAttribute("listeVilles"); %>
			<% for(Ville v : liste){%>
		   		<option value="<%=v.getCodeCommuneINSEE()%>"><%=v.getCodePostal()%> - <%=v.getNomCommune()%></option>
		   	<% } %>
		</select>
		<br><br>
		
		Ville d'arrivée:<br>
		<select name="villeB">
			<% for(Ville v : liste){%>
		   		<option value="<%=v.getCodeCommuneINSEE()%>"><%=v.getCodePostal()%> - <%=v.getNomCommune()%></option>
		   	<% } %>
		</select>
		<br><br>
		
		<input type="submit" id="submit" name="submit" value="Calculer">
	</form>
	<br>

	<a href="index.jsp">Retour</a>
</body>
</html>