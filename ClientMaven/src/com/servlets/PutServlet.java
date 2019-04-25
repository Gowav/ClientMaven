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
 * Servlet implementation class PutServlet
 */
@WebServlet("/PutServlet")
public class PutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PutServlet() {
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
		String message;
		
		if(request.getParameter("pageModif")!=null) {
			session.setAttribute("codeCommuneINSEEModif",request.getParameter("codeCommuneINSEEModif"));
		
		}else if(request.getParameter("modifier")!=null) {
			
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
				connexionAPI.put(session.getAttribute("codeCommuneINSEEModif").toString(),ville);
				message = "Modification(s) réussie(s)";
			}catch(Exception e) {
				message = "Echec de la modification. Veuillez réessayer!";
			}
			
			session.setAttribute("message", message);
		}else if(request.getParameter("supprimer")!=null) {

			try{
				connexionAPI.delete(session.getAttribute("codeCommuneINSEEModif").toString());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}

			RequestDispatcher dispat = request.getRequestDispatcher("index.jsp");
			dispat.forward(request, response);
		}

		try{
			Ville villeModif = connexionAPI.get(session.getAttribute("codeCommuneINSEEModif").toString());
			session.setAttribute("villeModif", villeModif);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		RequestDispatcher dispat = request.getRequestDispatcher("modifVille.jsp");
		dispat.forward(request, response);
	}
}
