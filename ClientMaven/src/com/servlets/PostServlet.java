package com.servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blo.Ville;
import com.dto.connexionAPI;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		DecimalFormat df = new DecimalFormat("#.##########");
		
		Ville ville = new Ville();
		ville.setCodeCommuneINSEE(String.format("%05d", Integer.valueOf(request.getParameter("codeCommuneINSEE"))));
		ville.setNomCommune(request.getParameter("nomCommune").toUpperCase());
		ville.setCodePostal(String.format("%05d", Integer.valueOf(request.getParameter("codePostal"))));
		ville.setLibelleAcheminement(request.getParameter("libelleAcheminement").toUpperCase());
		ville.setLongitude(df.format(Float.valueOf(request.getParameter("longitude"))).replaceAll(",","."));
		ville.setLatitude(df.format(Float.valueOf(request.getParameter("latitude"))).replaceAll(",","."));
		if(request.getParameter("ligne5")!=null) {
			ville.setLigne5(request.getParameter("ligne5").toUpperCase());
		}
		
		try{
			connexionAPI.post(ville);
			message = "Insertion réussie";
		}catch(Exception e) {
			message = "Echec de l'inserstion, merci de vérifier que ce code commune INSEE n'est pas déjà présent dans la liste des villes.";
		}
		
		session.setAttribute("message", message);
		
		RequestDispatcher dispat = request.getRequestDispatcher("insertVille.jsp");
		dispat.forward(request, response);
	}

}
