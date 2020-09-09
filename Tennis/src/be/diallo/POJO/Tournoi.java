package be.diallo.POJO;

import java.util.*;

public class Tournoi implements java.io.Serializable {

	private static final long serialVersionUID = 4L;
	private int ID;
  private String nomTournoi;
	private ArrayList<Ordonnancement> ordonnancement;
  
	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}

	public String getNomTournoi() {
		return nomTournoi;
	}

	public void setNomTournoi(String nomTournoi) {
		this.nomTournoi = nomTournoi;
	}

	public ArrayList<Ordonnancement> getOrdonnancement() {
		return ordonnancement;
	}

	public void setOrdonnancement(ArrayList<Ordonnancement> ordonnancement) {
		this.ordonnancement = ordonnancement;
	}

	public Tournoi() {}
	public Tournoi(int Id, String NomT) {
		this.ID = Id;
		this.nomTournoi = NomT;
		this.ordonnancement = new ArrayList<Ordonnancement>();
	}
}
