package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.Assistant;
import edu.GestionBDD;

import edu.Professeur;

@WebServlet("/assistants")
public class AssistansServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private GestionBDD bdd;
       

	public AssistansServlet() {
		this.bdd = MyAppServletContextListener.bdd;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Assistant> assistants = bdd.getAllAssistatns();
			request.setAttribute("assistants", assistants);
			List<Professeur> professeurs = bdd.getAllProfesseurs();
			request.setAttribute("professeurs", professeurs);
			request.setAttribute("currentPage", "assistants");
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
		int id_responsable = new Integer (request.getParameter("id_responsable"));
		Professeur responsable = null;
		try {
			responsable = this.bdd.getProfesseurById(id_responsable);
			Assistant a = new Assistant(nom, prenom, date_naissance, diplome, responsable);
			this.bdd.addAssistant(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
