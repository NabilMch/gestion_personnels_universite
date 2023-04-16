package edu;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestionBDD {

	private String dataBaseFilePath;
	public Connection connection;
	private Statement stmt;

	public GestionBDD(String dataBaseFilePath) throws Exception {
		super();
		this.dataBaseFilePath = dataBaseFilePath;
		this.connect();
	}
	
	public void purgeDataBase() throws Exception {
		new FileWriter(this.dataBaseFilePath , false).close();
	}

	private void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + this.dataBaseFilePath);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	public void initTables() throws SQLException {
		
		this.stmt = this.connection.createStatement();
		String query1 = "CREATE TABLE IF NOT EXISTS Professeur (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOM TEXT, PRENOM TEXT, DATE_NAISSANCE TEXT, DIPLOME TEXT);";
		String query2 = "CREATE TABLE IF NOT EXISTS Assistant (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOM TEXT, PRENOM TEXT, DATE_NAISSANCE TEXT, DIPLOME TEXT, ID_PROF, FOREIGN KEY (ID_PROF) REFERENCES Professeur (ID));";
		String query3 = "CREATE TABLE IF NOT EXISTS Module (TITRE TEXT PRIMARY KEY NOT NULL, H_COURS INT, H_TP INT, H_TD INT, ID_PROF, FOREIGN KEY (ID_PROF) REFERENCES Professeur (ID));";
		this.stmt.addBatch(query1);
		this.stmt.addBatch(query2);
		this.stmt.addBatch(query3);
		this.stmt.executeBatch();
		this.stmt.close();
		
		System.out.println("Tables created if not exist");
	}

	public void addProfesseur(Professeur p) throws SQLException {
		this.stmt = this.connection.createStatement();
		String query = String.format(
				"INSERT INTO Professeur (NOM, PRENOM, DATE_NAISSANCE, DIPLOME) VALUES ('%s', '%s', '%s', '%s');", 
				p.nom, p.prenom, p.date_naissance, p.diplome);
		this.stmt.execute(query);
		this.stmt.close();
		
		System.out.println("Professeur Added to DataBase : " + p);
	}
	

	public void addAssistant(Assistant a) throws SQLException {
		this.stmt = this.connection.createStatement();
		String query = String.format(
				"INSERT INTO Assistant ( NOM, PRENOM, DATE_NAISSANCE, DIPLOME, ID_PROF) VALUES ( '%s', '%s', '%s', '%s', %d);", 
				a.nom, a.prenom, a.date_naissance, a.diplome, a.responsable.id);
		this.stmt.execute(query);
		this.stmt.close();
		
		System.out.println("Assistant Added to DataBase : " + a);
	}
	
	public void addModule(Module m) throws SQLException {
		this.stmt = this.connection.createStatement();
		String query = String.format(
				"INSERT INTO Module (TITRE, H_COURS, H_TP, H_TD, ID_PROF) VALUES ('%s', %d, %d, %d, %d);", m.titre,
				m.nb_heures_cours, m.nb_heure_tp, m.nb_heure_td, m.responsable.id);
		this.stmt.execute(query);
		this.stmt.close();
		
		System.out.println("Module Added to DataBase : " + m);
	}
	
	
	public List<Professeur> getAllProfesseurs() throws SQLException{
		List<Professeur> result = new ArrayList<>();
		this.stmt = this.connection.createStatement();
		String query = "SELECT * FROM Professeur";
		this.stmt.execute(query);
		ResultSet rs = this.stmt.executeQuery(query);
		while(rs.next()) {
			int id = rs.getInt("ID");
			String nom = rs.getString("NOM");
			String prenom = rs.getString("PRENOM");
			String date_naissance = rs.getString("DATE_NAISSANCE");
			String diplome = rs.getString("DIPLOME");
			Professeur x = new Professeur(id, nom, prenom, date_naissance, diplome);
			result.add(x);
		}
		rs.close();
		this.stmt.close();
		
		return result;
	}
	
	
	public List<Assistant> getAllAssistatns() throws SQLException{
		List<Assistant> result = new ArrayList<>();
		this.stmt = this.connection.createStatement();
		String query = "SELECT a.*, p.ID as P_ID, p.NOM as P_NOM, p.PRENOM as P_PRENOM, p.DATE_NAISSANCE as P_DATE_NAISSANCE, p.diplome as P_DIPLOME FROM Assistant a, Professeur p WHERE a.ID_PROF = p.ID";
		this.stmt.execute(query);
		ResultSet rs = this.stmt.executeQuery(query);
		while(rs.next()) {
			int id = rs.getInt("ID");
			String nom = rs.getString("NOM");
			String prenom = rs.getString("PRENOM");
			String date_naissance = rs.getString("DATE_NAISSANCE");
			String diplome = rs.getString("DIPLOME");
			
			int p_id = rs.getInt("P_ID");
			String p_nom = rs.getString("P_NOM");
			String p_prenom = rs.getString("P_PRENOM");
			String p_date_naissance = rs.getString("P_DATE_NAISSANCE");
			String p_diplome = rs.getString("P_DIPLOME");
			Professeur responsable = new Professeur(p_id, p_nom, p_prenom, p_date_naissance, p_diplome);
			Assistant x = new Assistant(id, nom, prenom, date_naissance, diplome, responsable);
			result.add(x);
		}
		rs.close();
		this.stmt.close();
		
		return result;
	}
	
	
	public List<Module> getAllModules() throws SQLException {
		List<Module> result = new ArrayList<>();
		this.stmt = this.connection.createStatement();
		String query = "SELECT m.*, p.ID as P_ID, p.NOM as P_NOM, p.PRENOM as P_PRENOM, p.DATE_NAISSANCE as P_DATE_NAISSANCE, p.diplome as P_DIPLOME FROM Module m, Professeur p  WHERE m.ID_PROF = p.ID";
		this.stmt.execute(query);
		ResultSet rs = this.stmt.executeQuery(query);
		while(rs.next()) {
			String titre = rs.getString("TITRE");
			int nb_heures_cours = rs.getInt("H_COURS");
			int nb_heure_td = rs.getInt("H_TD");
			int nb_heure_tp = rs.getInt("H_TP");
			
			int p_id = rs.getInt("P_ID");
			String p_nom = rs.getString("P_NOM");
			String p_prenom = rs.getString("P_PRENOM");
			String p_date_naissance = rs.getString("P_DATE_NAISSANCE");
			String p_diplome = rs.getString("P_DIPLOME");
			Professeur responsable = new Professeur(p_id, p_nom, p_prenom, p_date_naissance, p_diplome);
			Module x = new Module(titre, nb_heures_cours, nb_heure_td, nb_heure_tp, responsable);
			result.add(x);
		}
		rs.close();
		this.stmt.close();
		
		return result;
	}
	
	public Professeur getProfesseurById(int id) throws SQLException {
		Professeur result = null;
		this.stmt = this.connection.createStatement();
		String query = "SELECT * FROM Professeur WHERE id="+id;
		this.stmt.execute(query);
		ResultSet rs = this.stmt.executeQuery(query);
		while(rs.next()) {
			String nom = rs.getString("NOM");
			String prenom = rs.getString("PRENOM");
			String date_naissance = rs.getString("DATE_NAISSANCE");
			String diplome = rs.getString("DIPLOME");
			result = new Professeur(id, nom, prenom, date_naissance, diplome);
		}
		rs.close();
		this.stmt.close();
		return result;
	}

}
