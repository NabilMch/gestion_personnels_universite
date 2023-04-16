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
import edu.Module;
import edu.Professeur;

@WebServlet("/modules")
public class ModulesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private GestionBDD bdd;
       

	public ModulesServlet() {
		this.bdd = MyAppServletContextListener.bdd;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Module> modules = bdd.getAllModules();
			List<Professeur> professeurs = bdd.getAllProfesseurs();
			request.setAttribute("modules", modules);
			request.setAttribute("professeurs", professeurs);
			request.setAttribute("currentPage", "modules");
		    request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titre = request.getParameter("titre");
		int nb_heures_cours = new Integer (request.getParameter("nb_heures_cours"));
		int nb_heure_td = new Integer (request.getParameter("nb_heure_td"));
		int nb_heure_tp = new Integer (request.getParameter("nb_heure_tp"));
		int id_responsable = new Integer (request.getParameter("id_responsable"));
		Professeur responsable = null;
		try {
			responsable = this.bdd.getProfesseurById(id_responsable);
			Module m = new Module(titre, nb_heures_cours, nb_heure_td, nb_heure_tp, responsable);
			this.bdd.addModule(m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
