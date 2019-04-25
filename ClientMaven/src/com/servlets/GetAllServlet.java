package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class GetAllServlet
 */
@WebServlet("/GetAllServlet")
public class GetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("numeroPage")!=null) {
			session.setAttribute("numeroPage", request.getParameter("numeroPage"));
		}else if(session.getAttribute("numeroPage")!=null) {
			session.removeAttribute("numeroPage");
			session.setAttribute("numeroPage", 1);
		}else {
			session.setAttribute("numeroPage", 1);
		}
		List<Ville> liste = connexionAPI.get();
		
		int nbPages = (liste.size()/50)+1;
		int idPremVille = (Integer.valueOf(session.getAttribute("numeroPage").toString())-1)*50;
		List<Ville> liste50 = new ArrayList<Ville>();
		
		for(int nb=idPremVille; nb<Math.min(liste.size(),idPremVille+50); nb++) {
			liste50.add(liste.get(nb));
		}
		
		session.setAttribute("listeVilles", liste50);
		session.setAttribute("nbPages", nbPages);
		
		RequestDispatcher dispat = request.getRequestDispatcher("villes.jsp");
		dispat.forward(request, response);

	}

}