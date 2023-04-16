package edu;

public class Professeur extends Personnel {



	protected String diplome;

	public Professeur(int id, String nom, String prenom, String date_naissance, String diplome) {
		super(id, nom, prenom, date_naissance);
		this.diplome = diplome;
	}
	
	public Professeur(String nom, String prenom, String date_naissance, String diplome) {
		super(nom, prenom, date_naissance);
		this.diplome = diplome;
	}

	public void ajouteModule(Module module) {
		module.responsable = this;
	}

	@Override
	public String toString() {
		return "Professeur [diplome=" + diplome + ", id=" + id + ", nom=" + nom + ", prenom="
				+ prenom + ", date_naissance=" + date_naissance + "]";
	}

	public String getDiplome() {
		return diplome;
	}
	
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getDate_naissance() {
		return date_naissance;
	}
	

}
