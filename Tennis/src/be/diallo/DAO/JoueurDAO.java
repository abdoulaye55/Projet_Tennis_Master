package be.diallo.DAO;


import java.sql.*;
import java.util.*;

import be.diallo.Enum.*;
import be.diallo.POJO.*;


public class JoueurDAO extends DAO<Joueur> {
	static Connection connec = TournoiConnection.getInstance();
	public JoueurDAO() {
		super(connec);
	}
	

	@Override
	public boolean create(Joueur j) {
		try {
			PreparedStatement p = connec.prepareStatement("INSERT INTO Joueur (default, Nom, Prenom, Age, Genre, Badge) VALUES (?,?,?,?,?)");
			p.setString(1, j.getNom());
			p.setString(2, j.getPrenom());
			p.setInt(3, j.getAge());
			p.setInt(4, j.getClassement());
			p.setString(5, j.getGenre().toString());
			// ex�cute l'insertion et renvoie un chiffre repr�sentant le nombre de ligne ajout�e, si l'insertion c'est mal pass�e, il renverra 0
			int checkUpdate = p.executeUpdate(); 
			// On v�rifie si l'insertion c'est bien pass�e donc sup�rieur a 0
			if(checkUpdate > 0) 
				return true;
			else
				return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connec != null){
					//Lib�re l'object connexion si celui-ci est null
					connec.close();
			}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Joueur j) {
		try {
			PreparedStatement p =connec.prepareStatement("DELETE FROM Joueur WHERE ID_Personne_Joueur_PK=?");
			p.setInt(1, j.getID());
			// ex�cute l'insertion et renvoie un chiffre repr�sentant le nombre de ligne ajout�e, si l'insertion c'est mal pass�e, il renverra 0
			int checkUpdate = p.executeUpdate(); 
			// On v�rifie si l'insertion c'est bien pass�e donc sup�rieur � 0
			if(checkUpdate > 0) 
				return true;
			else
				return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connec != null){
					//Lib�re l'object connexion si celui-ci est null
				   connec.close();
			}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean update(Joueur j) {
		try {
			Joueur joueur = j.find(j.getID());
			int checkUpdatePersonne;
			int checkUpdateJoueur;
			PreparedStatement pPersonne = connec.prepareStatement("UPDATE Personne SET Nom = ?, Prenom = ?, Age = ?, Genre = ? WHERE nom = ?");
			PreparedStatement pArbitre = connec.prepareStatement("UPDATE Joueur SET Classement = ? WHERE nom = ?");
			
			//Set des valeurs pour la table Personne
			pPersonne.setString(1, j.getNom());
			pPersonne.setString(2, j.getPrenom());
			pPersonne.setInt(3, j.getAge());
			pPersonne.setString(4, j.getGenre().toString());
			pPersonne.setInt(5, j.getID());
			
			//Set des valeurs pour la table Joueur
			pArbitre.setInt(1, j.getClassement());
			pArbitre.setInt(2, j.getID());
			//V�rifie si le classement  a �t� chang�, si c'est le cas, il le modifie.
			if (joueur.getClassement() == j.getClassement()) {
				// ex�cute l'UPDATE et renvoie un chiffre repr�sentant le nombre de ligne modifi�e, si l'insertion c'est mal pass�e, il renverra 0
				checkUpdatePersonne = pPersonne.executeUpdate();
				if(checkUpdatePersonne > 0) 
					return true;
				else
					return false;
			}
			else {
				// ex�cute les UPDATE et renvoie un chiffre repr�sentant le nombre de ligne modifi�e, si l'insertion c'est mal pass�e, il renverra 0
				checkUpdatePersonne = pPersonne.executeUpdate();
				checkUpdateJoueur = pArbitre.executeUpdate();
				if(checkUpdatePersonne > 0 && checkUpdateJoueur > 0) 
					return true;
				else
					return false;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connec != null){
					//Lib�re l'object connexion si celui-ci est null
					connec.close();
			}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Joueur find(int id) {
		Joueur j = null;
		ResultSet result = null;
		try {
			result = connec.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Joueur WHERE ID_Joueur_PK = " + id);
			if(result.first()) {
				j = new Joueur(id,result.getString("Nom"),result.getString("Prenom"), result.getInt("Age"), Genre.fromString(result.getString("Genre")), result.getInt("Classement"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connec != null)
				   connec.close();
				if(result != null)
					result.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return j;
	}

	@Override
	public ArrayList<Joueur> findAll() {
		ArrayList<Joueur> ListJ = new ArrayList<Joueur>();
		ResultSet result = null;
		try {
			result = connec
					.createStatement()
					.executeQuery("SELECT * FROM Joueur;");
			while(result.next()) {
				ListJ.add(new Joueur(result.getInt("Id_Joueur_PK"),result.getString("Nom"),result.getString("Prenom"), result.getInt("Age"), Genre.fromString(result.getString("Genre")), result.getInt("Classement")));
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ListJ;
	}
	public ArrayList<Joueur> findByGenre(Genre g){
		ArrayList<Joueur> ListJ = new ArrayList<Joueur>();
		ResultSet result = null;
		try {
			PreparedStatement p = connec.prepareStatement("SELECT * FROM Joueur WHERE Genre LIKE ? ;");
			p.setString(1, g.toString());
			result = p.executeQuery();
			//result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne INNER JOIN Joueur ON Personne.ID_Personne_PK = Joueur.ID_Personne_Joueur_PK WHERE Personne.Genre = ? ;");
			while(result.next()) {
				ListJ.add(new Joueur(result.getInt("Id_Joueur_PK"),result.getString("Nom"),result.getString("Prenom"), result.getInt("Age"), Genre.fromString(result.getString("Genre")), result.getInt("Classement")));
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		/*finally {
			try {
				if(connec != null)
				    connec.close();
				if(result != null)
					result.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		return ListJ;
	}
	

}
