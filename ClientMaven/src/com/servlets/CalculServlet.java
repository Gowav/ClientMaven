package com.servlets;

import java.io.IOException;
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
 * Servlet implementation class CalculServlet
 */
@WebServlet("/CalculServlet")
public class CalculServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		List<Ville> liste = connexionAPI.get();
		session.setAttribute("listeVilles", liste);
		
		if(request.getParameter("submit")!=null) {
			Ville a = connexionAPI.get(request.getParameter("villeA"));
			double longitudeA = Double.valueOf(a.getLongitude())*Math.PI/180;
			double latitudeA = Double.valueOf(a.getLatitude())*Math.PI/180;
			
			Ville b = connexionAPI.get(request.getParameter("villeB"));
			double longitudeB = Double.valueOf(b.getLongitude())*Math.PI/180;
			double latitudeB = Double.valueOf(b.getLatitude())*Math.PI/180;
			
			double distance = Math.acos(Math.sin(latitudeA)*Math.sin(latitudeB)+Math.cos(latitudeA)*Math.cos(latitudeB)*Math.cos(longitudeA-longitudeB))*6371;
			
			session.setAttribute("nomA", a.getNomCommune());
			session.setAttribute("nomB", b.getNomCommune());
			session.setAttribute("distance", distance);
		}
		
		RequestDispatcher dispat = request.getRequestDispatcher("calculVilles.jsp");
		dispat.forward(request, response);
	}

}
