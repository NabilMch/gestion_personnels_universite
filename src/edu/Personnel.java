package edu;

public class Personnel {
	public int id;
	public String nom;
	public String prenom;
	public String date_naissance;

	public Personnel(int id, String nom, String prenom, String date_naissance) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
	}
	
	public Personnel(String nom, String prenom, String date_naissance) {

		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
	}

	@Override
	public String toString() {
		return "Personnel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance
				+ "]";
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
	
	
	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}
	
}
