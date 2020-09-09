package be.diallo.POJO;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import be.diallo.DAO.JoueurDAO;
import be.diallo.Enum.*;

public class Equipe {

	private ArrayList<Joueur> equipe;
	private int nbrVictoire;
	
	//private CatalogJoueurs CJ = CatalogJoueurs.getInstance();
	
	public ArrayList<Joueur> getEquipe() {
		return equipe;
	}

	public Equipe() {
		equipe = new ArrayList<Joueur>();
		this.nbrVictoire=0;
	}
	
	public Equipe(String string, String string2, int i) {
		// TODO Auto-generated constructor stub
	}

	public int getNbrVictoire() {
		return nbrVictoire;
	}

	public void addJoueur(Joueur j) {
		equipe.add(j);
	}

	public void setNbrVictoire(int nbrVictoire) {
		this.nbrVictoire = nbrVictoire;
	}


	public void setEquipe(ArrayList<Joueur> equipe) {
		this.equipe = equipe;
	}


	@Override
	public String toString() {
		//return equipe.toString();
		if(equipe.size()==2)
			return equipe.get(0).getNom() + " " +equipe.get(0).getPrenom()+"/" +equipe.get(1).getNom() + " " +equipe.get(1).getPrenom();
		else
			return equipe.get(0).getNom() + " " +equipe.get(0).getPrenom();
	}
	public static List<Equipe> findAllByType(String genre, String tMatch) {
		Random random = new Random();
		List<Equipe> listeEquipe = new ArrayList<Equipe>();
		//1. récupérer les joueurs du bon genre et du bon type
		JoueurDAO joueurDAO = new JoueurDAO();
		List<Joueur> joueurs = null;
		List<Joueur> joueursH = null;
		List<Joueur> joueursF = null;
		if(genre.equals(Genre.Femme.name())) {
			joueurs = joueurDAO.findByGenre(Genre.Femme);
		}
		else if(genre.equals(Genre.Homme.name())) {
			joueurs = joueurDAO.findByGenre(Genre.Homme);
		}
		else {
			//mixte --> hommes et femmes
			joueursH = joueurDAO.findByGenre(Genre.Homme);
			// on doit avoir seulement 64 hommes pour le tournoi et pas 128
			for(int i=0;i<64;i++) {
				Joueur j = joueursH.get(random.nextInt(joueursH.size()));
				joueursH.remove(j);
			}
			joueursF = joueurDAO.findByGenre(Genre.Femme);
			for(int i=0;i<64;i++) {
				Joueur j = joueursF.get(random.nextInt(joueursF.size()));
				joueursF.remove(j);
			}
		}
		
		if(genre.equals(Genre.Mixte.name())) {
			joueursH = joueurDAO.findByGenre(Genre.Homme);
			joueursF = joueurDAO.findByGenre(Genre.Femme);
			for(int i=0;i<64;i++) {
				Equipe e = new Equipe();
				Joueur j = joueursH.get(random.nextInt(joueursH.size()));
				e.addJoueur(j);
				joueursH.remove(j);
				j = joueursF.get(random.nextInt(joueursF.size()));
				e.addJoueur(j);
				joueursF.remove(j);
				listeEquipe.add(e);
			}
		}
		else if(tMatch.equals(TMatch.Simple.name())) {
			for(int i=0;i<128;i++) {
				Equipe e = new Equipe();
				e.addJoueur(joueurs.get(i));
				listeEquipe.add(e);
			}
		}
		else {
			//double
			for(int i=0;i<64;i++) {
				Equipe e = new Equipe();
				Joueur j = joueurs.get(random.nextInt(joueurs.size()));
				e.addJoueur(j);
				joueurs.remove(j);
				j = joueurs.get(random.nextInt(joueurs.size()));
				e.addJoueur(j);
				joueurs.remove(j);
				listeEquipe.add(e);
			}
		}
		return listeEquipe;
		
	}
}
