package admin;

import project.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;

/**
 * JFrame pour afficher l'historique de connexion des personnels de santé
 * @author Groupe 4
 *
 */
public class Historique extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelHistorique;
	private JPanel panelSouth;
	private JTable tableHistorique;
	private JScrollPane scrollable;
	private JButton btnRafraichir;
	private JButton btnFermer;
	
	public Historique() {
		setTitle("Historique des connexions");
		setSize(610, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		Enregistrement enr = new Enregistrement();
		ArrayList<String> historique = enr.getHistorique();
		String[] header= {"Date","Login","Adresse IP","Action"};
		String[][] donnees = new String[historique.size()][4] ;
		for(int i=0;i<historique.size();i++) {
			String[] parts = historique.get(i).split("\t",4);			
			donnees[i][0]=parts[0];
			donnees[i][1]=parts[1];
			donnees[i][2]=parts[2];
			donnees[i][3]=parts[3];
		}
		tableHistorique = new JTable(donnees, header);
		tableHistorique.setEnabled(false);
		scrollable = new JScrollPane(tableHistorique);
		
		btnRafraichir = new JButton("Rafraîchir");
		btnRafraichir.setMnemonic('r');
		btnRafraichir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRafraichir.setBounds(23, 10, 139, 27);
		btnRafraichir.addActionListener(this);
		
		btnFermer = new JButton("Fermer");
		btnFermer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFermer.setBounds(411, 10, 139, 27);
		btnFermer.addActionListener(this);
		
		panelHistorique = new JPanel();
		panelHistorique.setBounds(0, 0, 596, 518);
		getContentPane().add(panelHistorique);
		panelHistorique.setLayout(new BorderLayout(0, 0));
		panelHistorique.add(tableHistorique.getTableHeader(), BorderLayout.NORTH);
		panelHistorique.add(scrollable);
		
		panelSouth = new JPanel();
		panelSouth.setBounds(0, 518, 596, 46);
		getContentPane().add(panelSouth);
		panelSouth.setLayout(null);
		panelSouth.add(btnFermer);
		panelSouth.add(btnRafraichir);
		
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnRafraichir) {
			this.dispose();
			new Historique();
		}else if(e.getSource()==btnFermer) {
			this.dispose();
		}
		
	}
}
