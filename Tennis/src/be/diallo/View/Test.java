package be.diallo.View;

import java.util.List;

import be.diallo.DAO.JoueurDAO;
import be.diallo.Enum.Genre;
import be.diallo.Enum.TMatch;
import be.diallo.POJO.Equipe;
import be.diallo.POJO.Joueur;
import be.diallo.POJO.Match;
import be.diallo.POJO.Ordonnancement;

public class Test {

	public static void main(String[] args) {
		// TODO 1 RECUPERER LA LISTE DES JOUEURS EN FONCTION DU CHOIX
		// TODO 2 GENNERE LA LISTE DES EQUIPE
		// TODO 
		/*List<Equipe> equipes = ModelEquipe.genererEquipe(TMatch.Simple, Genre.Femme);
		for(Equipe equipe : equipes) {
			equipe.toString();
		}*/
		Ordonnancement o = new Ordonnancement(1, 2, Genre.Mixte.name(), TMatch.Double.name());
		o.generateOrdonnancement(o);
		List<Match> matchJouer = o.getOrdreMatch();
		for(int i=0;i<matchJouer.size();i++) {
			System.out.println("Match "+i);
			Match match = matchJouer.get(i);
			System.out.println("Arbitre : "+match.getArbitre().getNom()+" "+match.getArbitre().getPrenom());
			System.out.println("Court : "+match.getCourt().getNom());
			System.out.println("Equipe 1 : "+match.getEquipes().get(0).getEquipe().get(0).getNom()+" "+match.getEquipes().get(0).getEquipe().get(0).getPrenom());
			System.out.println("Equipe 1 : "+match.getEquipes().get(0).getEquipe().get(1).getNom()+" "+match.getEquipes().get(0).getEquipe().get(1).getPrenom());
			System.out.println("Equipe 2 : "+match.getEquipes().get(1).getEquipe().get(0).getNom()+" "+match.getEquipes().get(1).getEquipe().get(0).getPrenom());
			System.out.println("Equipe 2 : "+match.getEquipes().get(1).getEquipe().get(1).getNom()+" "+match.getEquipes().get(1).getEquipe().get(1).getPrenom());
			System.out.println("Equipe gagnante : "+match.getEquipeGagnante().getEquipe().get(0).getNom()+" "+match.getEquipeGagnante().getEquipe().get(0).getPrenom());
			for(int j=0;j<match.getResultat().length;j++) {
				System.out.println("Set "+(j+1)+" : "+match.getResultat()[j][0]+"-"+match.getResultat()[j][1]);
			}
			System.out.println("-------------------------------------------");
		}
		Match finale = matchJouer.get(matchJouer.size()-1);
		System.out.println("Equipe Gagnante du tournoi : "+finale.getEquipeGagnante().getEquipe().get(0).getNom()+" "+finale.getEquipeGagnante().getEquipe().get(0).getPrenom());
		System.out.println("FIN DU PROGRAMME2");
		
		/*List<Equipe> equipes = ModelEquipe.genererEquipe( TMatch.Double, Genre.Homme);
		List<Equipe> equipes1 =  ModelEquipe.genererEquipe( TMatch.Simple, Genre.Homme);
		System.out.println(equipes1.size());
		while(equipes.size()> 1) {
			
			List<Match> matchs = ModelMatch.genererMatch(equipes);
			for(Match match : matchs) {
				Equipe tEquipe = ModelMatch.jouerMatch(match, 5);
				equipes1.remove(tEquipe);
				
				equipes=equipes1;
			
			
			}
			
		}
		
		System.out.println(" vainqueur "+ equipes1.size());
		*/
		
	}

}
