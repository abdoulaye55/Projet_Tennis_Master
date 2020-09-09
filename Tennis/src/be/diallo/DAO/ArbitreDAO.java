package be.diallo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import be.diallo.Enum.Genre;
import be.diallo.POJO.Arbitre;

public class ArbitreDAO extends DAO<Arbitre> {
	static Connection connec = TournoiConnection.getInstance();

	
	public ArbitreDAO() {
		// TODO Auto-generated constructor stub
		super(connec);
	}
	@Override
	public boolean create(Arbitre a) {
		try {
			PreparedStatement p = connec.prepareStatement("INSERT INTO Arbitre (ID_Arbitre_PK, Nom, Prenom, Age, Genre, Badge) VALUES (?,?,?,?,?,?)");
			p.setString(1, a.getNom());
			p.setString(2, a.getPrenom());
			p.setInt(3,a.getAge());
			p.setString(4, a.getGenre().toString());
			p.setString(5, a.getBadge());
			// exécute l'insertion et renvoie un chiffre représentant le nombre de ligne ajoutée, si l'insertion c'est mal passée, il renverra 0
			int checkUpdate = p.executeUpdate(); 
			// On vérifie si l'insertion c'est bien passée donc supérieur à 0
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
					//Libère l'object connexion si celui-ci est null
					connec.close();
			}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Arbitre a) {
		try {
			PreparedStatement p = connec.prepareStatement("DELETE FROM Arbitre WHERE ID_Personne_Arbitre_PK=?");
			p.setInt(1, a.getID());
			// exécute l'insertion et renvoie un chiffre représentant le nombre de ligne ajoutée, si l'insertion c'est mal passée, il renverra 0
			int checkUpdate = p.executeUpdate(); 
			// On vérifie si l'insertion c'est bien passée donc supérieur à 0
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
					//Libère l'object connexion si celui-ci est null
					connec.close();
			}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean update(Arbitre a) {
		
		try {
			//Sert de comparateur
			Arbitre arbitre = a.find(a.getID());
			int checkUpdatePersonne;
			int checkUpdateArbitre;
			PreparedStatement pPersonne = connec.prepareStatement("UPDATE Personne SET Nom = ?, Prenom = ?, Age = ?, Genre = ? WHERE nom = ?");
			PreparedStatement pArbitre = connec.prepareStatement("UPDATE Arbitre SET Badge = ? WHERE nom = ?");
			//Set des valeurs pour la table Personne
			pPersonne.setString(1, a.getNom());
			pPersonne.setString(2, a.getPrenom());
			pPersonne.setInt(3, a.getAge());
			pPersonne.setString(4, a.getGenre().toString());
			pPersonne.setInt(5, a.getID());
			//Set des valeurs pour la table Arbitre
			pArbitre.setString(1, a.getBadge());
			pArbitre.setInt(2, a.getID());
			//Vérifie si le badge  a été changé, si c'est le cas, il le modifie.
			if (arbitre.getBadge().equals(a.getBadge())) {
				// exécute l'UPDATE et renvoie un chiffre représentant le nombre de ligne modifiée, si l'insertion c'est mal passée, il renverra 0
				checkUpdatePersonne = pPersonne.executeUpdate();
				if(checkUpdatePersonne > 0) 
					return true;
				else
					return false;
			}
			else {
				// exécute les UPDATE et renvoie un chiffre représentant le nombre de ligne modifiée, si l'insertion c'est mal passée, il renverra 0
				checkUpdatePersonne = pPersonne.executeUpdate();
				checkUpdateArbitre = pArbitre.executeUpdate();
				if(checkUpdatePersonne > 0 && checkUpdateArbitre > 0) 
					return true;
				else
					return false;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		/*finally {
			try {
				if(connect != null){
					//Libère l'object connexion si celui-ci est null
					connect.close();
			}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return false;
		
	}

	@Override
	public Arbitre find(int id) {
		Arbitre a = null;
		ResultSet result = null;
		try {
			result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Arbitre WHERE ID_Arbitre_PK = " + id);
			if(result.first()) {
				a = new Arbitre(id,result.getString("Nom"),result.getString("Prenom"), result.getInt("Age"), Genre.fromString(result.getString("Genre")), result.getString("Badge"));
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return a;
	}

	@Override
	public List<Arbitre> findAll() {
		List<Arbitre> ListA = new ArrayList<Arbitre>();
		ResultSet result = null;
		try {
			if(connec.isClosed()) {
				connec = TournoiConnection.getInstance();
			}
			result = connec.createStatement().executeQuery("SELECT * FROM Arbitre");
			while(result.next()) {
				ListA.add(new Arbitre(result.getInt("ID_Arbitre_PK"),result.getString("Nom"),result.getString("Prenom"), result.getInt("Age"), Genre.fromString(result.getString("Genre")), result.getString("Badge")));
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		/*ListA.add(new Arbitre(1, "Diallo", "thierno", 120, Genre.Homme, "or"));
		ListA.add(new Arbitre(1, "Diallo", "thierno", 120, Genre.Homme, "or"));
		ListA.add(new Arbitre(1, "Diallo", "thierno", 120, Genre.Homme, "or"));
		ListA.add(new Arbitre(1, "Diallo", "thierno", 120, Genre.Homme, "or"));
		ListA.add(new Arbitre(1, "Diallo", "thierno", 120, Genre.Homme, "or"));
		ListA.add(new Arbitre(1, "Diallo", "thierno", 120, Genre.Homme, "or"));
		ListA.add(new Arbitre(1, "Diallo", "thierno", 120, Genre.Femme, "or"));
		ListA.add(new Arbitre(1, "Diallo", "thierno", 120, Genre.Femme, "or"));
		ListA.add(new Arbitre(1, "Diallo", "thierno", 120, Genre.Femme, "or"));*/
		return ListA;
	}
	
}

