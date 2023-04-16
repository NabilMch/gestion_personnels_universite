package web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.GestionBDD;

public class MyAppServletContextListener implements ServletContextListener {

	public static GestionBDD bdd;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");
		try {
			MyAppServletContextListener.bdd = new GestionBDD("C:\\Users\\HP\\Desktop\\gestion_personnels_universite\\WebContent\\db.sqlite");
			MyAppServletContextListener.bdd.initTables();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
