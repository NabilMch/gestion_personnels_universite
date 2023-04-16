package edu;

public class Module extends Object{

	public String titre;
	public int nb_heures_cours;
	public int nb_heure_td;
	public int nb_heure_tp;
	public Professeur responsable;

	public Module(String titre, int nb_heures_cours, int nb_heure_td, int nb_heure_tp, Professeur responsable) {
		this.titre = titre;
		this.nb_heures_cours = nb_heures_cours;
		this.nb_heure_td = nb_heure_td;
		this.nb_heure_tp = nb_heure_tp;
		this.responsable = responsable;

	}

	void ajouter_h_cc(int nb_h) {
		this.nb_heures_cours = nb_h;
	}

	void ajouter_h_td(int nb_h) {
		this.nb_heure_td = nb_h;
	}

	void ajouter_h_tp(int nb_h) {
		this.nb_heure_tp = nb_h;
	}
//héritée de la classe parente
	@Override
	public String toString() {
		return "Module [titre=" + titre + ", nb_heures_cours=" + nb_heures_cours + ", nb_heure_td=" + nb_heure_td
				+ ", nb_heure_tp=" + nb_heure_tp + "]";
	}

	public String getTitre() {
		return titre;
	}

	public int getNb_heures_cours() {
		return nb_heures_cours;
	}

	public int getNb_heure_td() {
		return nb_heure_td;
	}

	public int getNb_heure_tp() {
		return nb_heure_tp;
	}

	public Professeur getResponsable() {
		return responsable;
	}
	
	
	
	

}
