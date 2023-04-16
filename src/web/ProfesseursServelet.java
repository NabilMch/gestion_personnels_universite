package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.GestionBDD;

import edu.Professeur;

@WebServlet("/professeurs")
public class ProfesseursServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private GestionBDD bdd;
       

	public ProfesseursServelet() {
		this.bdd = MyAppServletContextListener.bdd;
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Professeur> professeurs = bdd.getAllProfesseurs();
			request.setAttribute("professeurs", professeurs);
			request.setAttribute("currentPage", "professeurs");
		    request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String date_naissance = request.getParameter("date_naissance");
		String diplome = request.getParameter("diplome");
		try {
			
			Professeur p = new Professeur( nom, prenom, date_naissance, diplome);
			this.bdd.addProfesseur(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
