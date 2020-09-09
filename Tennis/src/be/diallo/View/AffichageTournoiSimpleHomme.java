package be.diallo.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.border.EmptyBorder;

import be.diallo.Enum.Genre;
import be.diallo.Enum.TMatch;
import be.diallo.POJO.Match;
import be.diallo.POJO.Ordonnancement;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.List;

public class AffichageTournoiSimpleHomme extends JFrame {

	private JPanel contentPane;
	private List<Match> matchs;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AffichageTournoiSimple frame = new AffichageTournoiSimple();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public AffichageTournoiSimpleHomme(List<Match> matchs) {
		this.matchs = matchs;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 7));
		
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(128,1));
		panel.add(panel1);
		Ordonnancement o = new Ordonnancement(1, 2, Genre.Homme.name(), TMatch.Simple.name());
		o.generateOrdonnancement(o);
		
		
		//List<Match> matchJouer = o.getOrdreMatch();
		for(int i=0;i<matchs.size();i++) {
			Match match = matchs.get(i);
			panel1.add(new ViewMatch(match, "MATCH EN COURS..."));
		}
		Match finale = matchs.get(matchs.size()-1);
		System.out.println("Equipe Gagnante du tournoi : "+finale.getEquipeGagnante().getEquipe().get(0).getNom()+" "+finale.getEquipeGagnante().getEquipe().get(0).getPrenom());
		panel1.add(new ViewMatch(finale,"GRANDE FINALE...."));
		JOptionPane.showConfirmDialog(panel1,( "LE gagant est " +finale.getEquipeGagnante().getEquipe().get(0).getNom()));
		
		//for..
		
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5, 5, 1004, 680);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(1024, 700));
        contentPane.add(scrollPane);
        this.setContentPane(contentPane);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}

}
