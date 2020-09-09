package be.diallo.POJO;

import be.diallo.Enum.*;

public abstract class Personne implements java.io.Serializable {

	private static final long serialVersionUID = 4L;
	private int ID;
	private String prenom;
	private String nom;
	private int age;
	private Genre genre;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	
	public Personne() {}
	public Personne (int Id, String Prenom, String Nom, int Age, Genre Genre) {
		this.ID = Id;
		this.prenom = Prenom;
		this.nom = Nom;
		this.age = Age;
		this.genre = Genre;
	}
}

