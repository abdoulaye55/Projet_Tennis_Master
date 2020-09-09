package be.diallo.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.diallo.Enum.Genre;
import be.diallo.Enum.TMatch;
import be.diallo.POJO.Match;
import be.diallo.POJO.Ordonnancement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Accueil extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil frame = new Accueil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Accueil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSimpleHomme = new JButton("Simple Homme");
		btnSimpleHomme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ordonnancement o = new Ordonnancement(1, 3, Genre.Homme.name(), TMatch.Simple.name());
				o.generateOrdonnancement(o);
				List<Match> matchJouer = o.getOrdreMatch();
				AffichageTournoiSimpleHomme frame = new AffichageTournoiSimpleHomme(matchJouer);
				frame.setVisible(true);
				dispose();
			}
		});
		btnSimpleHomme.setBounds(25, 11, 118, 52);
		contentPane.add(btnSimpleHomme);
		
		JButton btnSimpleFemme = new JButton("Simple Femme");
		btnSimpleFemme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ordonnancement o = new Ordonnancement(1, 2, Genre.Femme.name(), TMatch.Simple.name());
				o.generateOrdonnancement(o);
				List<Match> matchJouer = o.getOrdreMatch();
				AffichageTournoiSimpleFemme frame = new AffichageTournoiSimpleFemme(matchJouer);
				frame.setVisible(true);
				dispose();
			}
		});
		btnSimpleFemme.setBounds(287, 11, 118, 52);
		contentPane.add(btnSimpleFemme);
		
		JButton btnDoubleHomme = new JButton("Double Homme");
		btnDoubleHomme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ordonnancement o = new Ordonnancement(1, 2, Genre.Homme.name(), TMatch.Double.name());
				o.generateOrdonnancement(o);
				List<Match> matchJouer = o.getOrdreMatch();
				AffichageTournoiDoubleHomme frame = new AffichageTournoiDoubleHomme(matchJouer);
				frame.setVisible(true);
				dispose();
				
			}
		});
		btnDoubleHomme.setBounds(25, 198, 118, 52);
		contentPane.add(btnDoubleHomme);
		
		JButton btnDoubleFemme = new JButton("Double Femme");
		btnDoubleFemme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ordonnancement o = new Ordonnancement(1, 2, Genre.Femme.name(), TMatch.Double.name());
				o.generateOrdonnancement(o);
				List<Match> matchJouer = o.getOrdreMatch();
				AffichageTournoiDoubleFemme frame = new AffichageTournoiDoubleFemme(matchJouer);
				frame.setVisible(true);
				dispose();
			}
		});
		btnDoubleFemme.setBounds(287, 198, 118, 52);
		contentPane.add(btnDoubleFemme);
		
		JButton btnDoubleMixte = new JButton("Double Mixte");
		btnDoubleMixte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ordonnancement o = new Ordonnancement(1, 2, Genre.Mixte.name(), TMatch.Double.name());
				o.generateOrdonnancement(o);
				List<Match> matchJouer = o.getOrdreMatch();
				AffichageTournoiDoubleMixte frame = new AffichageTournoiDoubleMixte(matchJouer);
				frame.setVisible(true);
				dispose();
			}
		});
		btnDoubleMixte.setBounds(158, 114, 118, 52);
		contentPane.add(btnDoubleMixte);
	}
}
