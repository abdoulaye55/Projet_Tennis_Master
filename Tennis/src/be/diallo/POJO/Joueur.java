package be.diallo.POJO;

import java.util.List;

import be.diallo.DAO.ArbitreDAO;
import be.diallo.DAO.DAO;
import be.diallo.DAO.JoueurDAO;
import be.diallo.DAO.TournoiConnection;
import be.diallo.Enum.*;

public class Joueur extends Personne implements java.io.Serializable {

	private static final long serialVersionUID = 4L;
	private int classement;
	
	public int getClassement() {
		return classement;
	}
	public void setClassement(int classement) {
		this.classement = classement;
	}
	
	private JoueurDAO Jdao = new JoueurDAO();
	
	public Joueur() {}
	public Joueur (int Id, String Nom, String Prenom, int Age, Genre Genre, int Classement) {
		super(Id, Prenom,Nom,Age,Genre);
		this.classement = Classement;
	}
	
	public boolean create(Joueur a) {
		return Jdao.create(a);
	}
	public Joueur find(int id) {
		return Jdao.find(id);
	}
	public List<Joueur> findAll(){
		return Jdao.findAll();
	}
	public List<Joueur> findByGenre(Genre g){
		return Jdao.findByGenre(g);
	}
}
