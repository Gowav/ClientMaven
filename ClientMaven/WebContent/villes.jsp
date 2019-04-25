<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.blo.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Liste des villes </title>
	<style>
		table{border-collapse: collapse}
    	td{ border: 2px solid black}
    	thead{font-weight: bold}
    	tfoot{font-weight: bold}
    </style>
</head>
<body>
	<h1> Liste des villes</h1>
	<table>
		<thead>
			<tr>
				<td>Code commune INSEE</td>
				<td>Nom commune</td>
				<td>Code postal</td>
				<td>Libellé acheminement</td>
				<td>Ligne 5</td>
				<td>Longitude</td>
				<td>Latitude</td>
				<td>Modifier</td>
			<tr>
		</thead>
		
		<tbody>
			<% List<Ville> liste = (ArrayList<Ville>) session.getAttribute("listeVilles"); %>
			<% for(Ville v : liste){%>
			<tr>
				<td><%= v.getCodeCommuneINSEE() %></td>
				<td><%= v.getNomCommune() %></td>
				<td><%= v.getCodePostal() %></td>
				<td><%= v.getLibelleAcheminement() %></td>
				<td><%= v.getLigne5()%></td>
				<td><%= v.getLongitude() %></td>
				<td><%= v.getLatitude() %></td>
				<td>
					<form method="post" action="PutServlet">
						<input type="hidden" name="codeCommuneINSEEModif" value="<%= v.getCodeCommuneINSEE() %>" />
						<input type="submit" name="pageModif" value="Modifier">
					</form>
				</td>
			</tr>
			<% } %>
		</tbody>
		
		
		<tfoot>
			<tr>
				<td>Code commune INSEE</td>
				<td>Nom commune</td>
				<td>Code postal</td>
				<td>Libellé acheminement</td>
				<td>Ligne 5</td>
				<td>Longitude</td>
				<td>Latitude</td>
				<td>Modifier</td>
			<tr>
		</tfoot>
		
	</table>
	<br><br>
	<form method="post" action="GetAllServlet" >
		Page : 
		<select name="numeroPage">
			<% for(int nb=1; nb<=Integer.valueOf(session.getAttribute("nbPages").toString()); nb++){
					if(nb==Integer.valueOf(session.getAttribute("numeroPage").toString())){%>
		   				<option value="<%=nb%>" selected><%=nb%></option>
		   			<%}else{ %>
		   				<option value="<%=nb%>"><%=nb%></option>
		   	<% }} %>
		</select>
		<input type="submit" value="GO" />
	</form>
	<br><br>
	<a href="index.jsp">Retour</a>
</body>
</html>