package be.diallo.POJO;

import java.util.*;


public class Ordonnancement implements java.io.Serializable{


	private static final long serialVersionUID = 4L;
	private int ID;
    private int nbrSetGagnant;
	private String genre;
	private String tMatch;

	private List<Match> ordreMatch;
	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}

	public int getNbrSetGagnant() {
		return nbrSetGagnant;
	}

	public void setNbrSetGagnant(int nbrSetGagnant) {
		this.nbrSetGagnant = nbrSetGagnant;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String gettMatch() {
		return tMatch;
	}

	public void settMatch(String tMatch) {
		this.tMatch = tMatch;
	}

	public List<Match> getOrdreMatch() {
		return ordreMatch;
	}

	public void setOrdreMatch(List<Match> ordreMatch) {
		this.ordreMatch = ordreMatch;
	}

	public Ordonnancement(int Id, int NbrSetG, String genre, String tMatch) {
		this.ID = Id;
		this.nbrSetGagnant = NbrSetG;
		this.genre = genre;
		this.tMatch = tMatch;
		ordreMatch = new ArrayList<Match>();
	}
	public static void generateOrdonnancement(Ordonnancement obj) {
		//1. récupérer les équipes avec les joueur
		List<Equipe> listeEquipe = Equipe.findAllByType(obj.getGenre(), obj.gettMatch());
		//2. créer les premiers matchs
		//MatchDAO matchDAO = new MatchDAO();
		List<Match> listePremierMatch = Match.generateMatchPremierTour(listeEquipe);
		obj.setOrdreMatch(listePremierMatch);
		//3. Jouer les premiers matchs
		List<Match> listeMatchSuivant = Match.jouerTour(listePremierMatch, obj.getGenre(), obj.gettMatch());
		obj.getOrdreMatch().addAll(listeMatchSuivant);
	
		//4. créer les autres tours du tournoi et exécuter
		while(listeMatchSuivant.size()>1) {
			
			listeMatchSuivant = Match.jouerTour(listeMatchSuivant, obj.getGenre(), obj.gettMatch());
			obj.getOrdreMatch().addAll(listeMatchSuivant);
		}
		Match.simulerMatch(obj.getOrdreMatch().get(obj.getOrdreMatch().size()-1),obj.getGenre(), obj.gettMatch());
		System.out.println(obj.getOrdreMatch().size());
		
	}
	
}
