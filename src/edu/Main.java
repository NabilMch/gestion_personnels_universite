package edu;

import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("######## EXO - 01  ######## ");
		Professeur prof = new Professeur("MECHERI", "Nabil", "12/12/1995", "Doctorat Informatique");
		Assistant assist = new Assistant("Le Grand", "Laura", "12/11/2000", "Licence AEI", null);
		assist.responsable = prof;
		Module programmation = new Module("Programmation 2", 10, 11, 12, prof);

		System.out.println("**** Professeur : ****");
		System.out.println(prof.toString());

		System.out.println("\n**** Assistant : ****");
		System.out.println(assist.toString());

		System.out.println("\n**** Module : ****");
		System.out.println(programmation.toString());

		System.out.println("\n\n########  EXO - 02  ########");
		GestionBDD bdd = new GestionBDD("C:\\Users\\HP\\Desktop\\gestion_personnels_universite\\WebContent\\db.sqlite");
		bdd.purgeDataBase();
		bdd.initTables();
		bdd.addProfesseur(prof);
		bdd.addAssistant(assist);
		bdd.addModule(programmation);

		List<Professeur> profs = bdd.getAllProfesseurs();
		System.out.println(profs);

		List<Assistant> assists = bdd.getAllAssistatns();
		System.out.println(assists);

		List<Module> modules = bdd.getAllModules();
		System.out.println(modules);

	}

}
