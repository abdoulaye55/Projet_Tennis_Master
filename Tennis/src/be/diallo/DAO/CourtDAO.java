package be.diallo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.diallo.POJO.Court;

public class CourtDAO extends DAO<Court> {
	static Connection connec = TournoiConnection.getInstance();
	public CourtDAO() {
		super(connec);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Court obj) {
		try {
			PreparedStatement p = connec.prepareStatement("INSERT INTO Court (Couvert, TypeSurface, Nom) VALUES (?,?,?)");
			p.setBoolean(1, obj.getCouvert());
			p.setString(2, obj.getTypeSurface());
			p.setString(3,obj.getNom());
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
	public boolean delete(Court obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Court obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Court find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Court> findAll() {
		ArrayList<Court> listC = new ArrayList<Court>();
		ResultSet result = null;
		try {
			result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Court;");
			while(result.next()) {
				listC.add(new Court(result.getInt("ID_Court_PK"),result.getString("Nom"),result.getBoolean("Couvert"), result.getString("TypeSurface")));
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		/*finally {
			try {
				if(this.connect != null)
					this.connect.close();
				if(result != null)
					result.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		/*listC.add(new Court(1, "Terre battu", true, "Terre battu"));
		listC.add(new Court(1, "Terre poussiereuse", true, "Terre poussiereuse"));
		listC.add(new Court(1, "Terre rouge", true, "Terre rouge"));
		listC.add(new Court(1, "Terre battu", true, "Terre battu"));
		listC.add(new Court(1, "Terre battu", true, "Terre battu"));*/
		return listC;
	}

}

