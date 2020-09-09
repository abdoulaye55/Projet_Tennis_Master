package be.diallo.POJO;

import java.time.*;
import java.util.*;

import be.diallo.DAO.ArbitreDAO;
import be.diallo.DAO.CourtDAO;
import be.diallo.Enum.*;


public class Match implements java.io.Serializable {

	private static final long serialVersionUID = 4L;

	private int ID;
	private LocalDate date;
	private float duree;
	private List<Equipe> equipes;
	private Arbitre arbitre;
	private Court court;
	private int[][] resultat;

	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public float getDuree() {
		return duree;
	}

	public void setDuree(float duree) {
		this.duree = duree;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public Arbitre getArbitre() {
		return arbitre;
	}

	public void setArbitre(Arbitre arbitre) {
		this.arbitre = arbitre;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}
	
	public int[][] getResultat() {
		return resultat;
	}
	public void setResultat(int[][] resultat) {
		this.resultat = resultat;
	}
	
	public Equipe getEquipeGagnante() {
		if(equipes.get(0).getNbrVictoire()>equipes.get(1).getNbrVictoire()) {
			return equipes.get(0);
		}
		else {
			return equipes.get(1);
		}
	}
	
	public Equipe getEquipePerdante() {
		if(equipes.get(0).getNbrVictoire()>equipes.get(1).getNbrVictoire()) {
			return equipes.get(1);
		}
		else {
			return equipes.get(0);
		}
	}
	

	/*
	public Match(TMatch tm, Genre g) {
		creerMatch(tm, g);
	}*/
	
	public Match() {
		resultat=new int[5][2];
	}
	//méthode test en mode console
	public void afficheEquipe() {
		for (int i = 0; i < this.equipes.size(); i++) {
			System.out.println(this.equipes.get(i).toString());
		}
	}

	public static List<Match> generateMatchPremierTour(List<Equipe> equipes){
		Random random = new Random();
		List<Match> matchs = new ArrayList<Match>();
		ArbitreDAO arbitreDAO = new ArbitreDAO();
		List<Arbitre> arbitres = arbitreDAO.findAll();
		CourtDAO courtDAO = new CourtDAO();
		List<Court> courts = courtDAO.findAll();
		
		int nbreDeMatch = equipes.size()/2;
		for(int i=0;i<nbreDeMatch;i++) {
			Match m = new Match();
	
			m.setArbitre(arbitres.get(random.nextInt(arbitres.size())));
			m.setCourt(courts.get(random.nextInt(courts.size())));
			
			Equipe equipe1 = equipes.get(random.nextInt(equipes.size()));
			equipes.remove(equipe1);
			
			Equipe equipe2 = equipes.get(random.nextInt(equipes.size()));
			equipes.remove(equipe2);
			
			ArrayList<Equipe> equipeMatch = new ArrayList<Equipe>();
			equipeMatch.add(equipe1);
			equipeMatch.add(equipe2);
			
			m.setEquipes(equipeMatch);
			
			matchs.add(m);
		}
		return matchs;
	}
	
	public static List<Match> generateMatchAutreTour(List<Equipe> equipes){
		Random random = new Random();
		List<Match> matchs = new ArrayList<Match>();
		ArbitreDAO arbitreDAO = new ArbitreDAO();
		List<Arbitre> arbitres = arbitreDAO.findAll();
		CourtDAO courtDAO = new CourtDAO();
		List<Court> courts = courtDAO.findAll();
		
		int nbreDeMatch = equipes.size()/2;
		for(int i=0;i<nbreDeMatch;i++) {
			Match m = new Match();
	
			m.setArbitre(arbitres.get(random.nextInt(arbitres.size())));
			m.setCourt(courts.get(random.nextInt(courts.size())));
			
			Equipe equipe1 = equipes.get(0);
			equipes.remove(equipe1);
			
			Equipe equipe2 = equipes.get(0);
			equipes.remove(equipe2);
			
			ArrayList<Equipe> equipeMatch = new ArrayList<Equipe>();
			equipeMatch.add(equipe1);
			equipeMatch.add(equipe2);
			
			m.setEquipes(equipeMatch);
			
			matchs.add(m);
		}
		return matchs;
	}
	
	public static List<Match> jouerTour(List<Match> matchAJouer, String genre, String tMatch){
		List<Equipe> equipesGagnantes = new ArrayList<Equipe>();
		//simuler les matchs et récupérer les équipes gagnantes
		for(int i=0;i<matchAJouer.size();i++) {
			equipesGagnantes.add(simulerMatch(matchAJouer.get(i), genre, tMatch));
		}
		//créer les nouveaux matchs
		return generateMatchAutreTour(equipesGagnantes);
	}
	
	public static Equipe simulerMatch(Match m, String genre, String tMatch) {
		//homme simple
		int nbrSetGagnant = 0;
		if(Genre.Homme.name().equals(genre) && TMatch.Simple.name().equals(tMatch)) {
			nbrSetGagnant=3;
		}
		else {
			nbrSetGagnant=2;
		}
		int setGagneEquipe1 = 0;
		int setGagneEquipe2 = 0;
		int setEnCours=0;
		while(setGagneEquipe1!=nbrSetGagnant && setGagneEquipe2!=nbrSetGagnant) {
			//simuler un nouveau set
			int[] set = simulerSet();
			if((set[0]==6 && set[1]<5) || (set[0]==7 && set[1]==5) || (set[0]==7 && set[1]==6)) {
				setGagneEquipe1++;
			}
			else {
				setGagneEquipe2++;
			}
			//ajout le set dans le match
			
			m.getResultat()[setEnCours] = set;
			setEnCours++;
						
		}
		if(setGagneEquipe1==nbrSetGagnant) {
			m.getEquipes().get(0).setNbrVictoire(m.getEquipes().get(0).getNbrVictoire()+1);
			return m.getEquipes().get(0);
		}
		else {
			m.getEquipes().get(1).setNbrVictoire(m.getEquipes().get(1).getNbrVictoire()+1);
			return m.getEquipes().get(1);
		}
	}
	
	public static int[] simulerSet() {
		// obtenir un set complet ex: 6-2 ou 2-6
		int set[] = new int[2];
		set[0] = 0;
		set[1] = 0;
		boolean continuer = true;
		while(continuer) {
//			System.out.println(set[0]+" - "+set[1]);
			//le premier à 6 et l'autre à moins de -5 ou 7/5 ou 5-7 ou 7-6
			if((set[0]==6 && set[1]<5) || (set[1]==6 && set[0]<5) || (set[0]==7 && set[1]==5) || (set[0]==5 && set[1]==7) || (set[0]==7 && set[1]==6) || (set[0]==6 && set[1]==7)) {
				continuer = false;
			}
			else {
				//donner un jeu à une des deux equipes
				if(Math.random() < 0.5) {
					set[0] = set[0] + 1 ;
				}
				else {
					set[1] = set[1] + 1 ;
				}
			}
		}
		return set;
	}
	

}
