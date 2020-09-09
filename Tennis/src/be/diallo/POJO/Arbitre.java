package be.diallo.POJO;

import java.util.List;

import be.diallo.DAO.*;
import be.diallo.Enum.*;

public class Arbitre extends Personne implements java.io.Serializable {

	public Arbitre(int Id, String Prenom, String Nom, int Age, Genre Genre) {
		super(Id, Prenom, Nom, Age, Genre);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 4L;
	private String badge;
	public String getBadge() 
	{
		return badge;
	}

	public void setBadge(String badge) 
	{
		this.badge = badge;
	}

	private DAO<Arbitre>Adao = new ArbitreDAO();
	public Arbitre () {}
	public Arbitre (int Id, String Prenom, String Nom, int Age, Genre Genre, String Badge) 
	{
		super(Id, Prenom,Nom,Age,Genre);
		this.badge = Badge;
	}
	public boolean create(Arbitre a) 
	{
		return Adao.create(a);
	}
	public Arbitre find(int id) {
		return Adao.find(id);
	}
	public List<Arbitre> findAll(){
		return Adao.findAll();
	}
}
