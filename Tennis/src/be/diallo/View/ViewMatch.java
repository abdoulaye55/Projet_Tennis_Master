package be.diallo.View;

import java.awt.Panel;

import be.diallo.POJO.Match;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;

public class ViewMatch extends Panel {
	
	public ViewMatch (Match match, String finale )
	{
		super();
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(4,4));
		
		JLabel lblNewLabel_2 = new JLabel(finale);
		
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setText("Arbitre : " +match.getArbitre().getPrenom());
		lblNewLabel.setBounds(59, 43, 122, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		StringBuilder stringBuilder = new StringBuilder();
		panel.add(lblNewLabel_3);
		for(int j=0;j<match.getResultat().length;j++) {
			stringBuilder.append("  Set "+(j+1)+" : "+match.getResultat()[j][0]+" "+"-"+match.getResultat()[j][1]);
		}
		lblNewLabel_3.setText(stringBuilder.toString());
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(59, 87, 122, 26);
		lblNewLabel_1.setText(match.getCourt().getNom());
		panel.add(lblNewLabel_1);
		
		JLabel equipeGagnante = new JLabel("New label");
		equipeGagnante.setForeground(Color.GREEN);
		equipeGagnante.setText(match.getEquipeGagnante().toString());
		panel.add(equipeGagnante);
		
		JLabel equipePerdante = new JLabel("New label");
		equipePerdante.setForeground(Color.RED);
		panel.add(equipePerdante);
		equipePerdante.setText(match.getEquipePerdante().toString());
		this.setVisible(true);

		
	}
}
