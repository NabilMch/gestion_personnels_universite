package edu;

public class Assistant extends Personnel {

	public String diplome;
	public String getDiplome() {
		return diplome;
	}

	public Personnel responsable;

	public Assistant(int id, String nom, String prenom, String date_naissance, String diplome, Personnel responsable) {
		super(id, nom, prenom, date_naissance);
		this.diplome = diplome;
		this.responsable = responsable;
	}
	
	public Assistant(String nom, String prenom, String date_naissance, String diplome, Personnel responsable) {
		super(nom, prenom, date_naissance);
		this.diplome = diplome;
		this.responsable = responsable;
	}
	
	public void modifierResponsable(Personnel responsable) {
		this.responsable = responsable;
	}

	@Override
	public String toString() {
		return "Assistant [diplome=" + diplome + ", responsable=" + responsable.nom + ", id=" + id + ", nom=" + nom
				+ ", prenom=" + prenom + ", date_naissance=" + date_naissance + "]";
	}
	
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public Personnel getResponsable() {
		return responsable;
	}

	public void setResponsable(Personnel responsable) {
		this.responsable = responsable;
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
