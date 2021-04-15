package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import project.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * JFrame pour rechercher un médicament
 * @author Groupe 4
 *
 */
public class RechercherGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrincipalGUI parent;
	private JButton btnVoir;
	private JButton btnRechercher;
	private JTable laTable;
	private JSpinner spinner;
	private JScrollPane scrollable;
	private JButton btnPrecedent;
	private JPanel north;
	private JPanel center;
	private JPanel south;
	private JTextField textRecherche;
	
	
	public RechercherGUI(PrincipalGUI parent, JTable tableMedicaments) {
		setTitle("Rechercher un médicament");
		this.parent=parent;
		parent.setVisible(false);
		setSize(610, 600);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		btnVoir = new JButton("Voir les informations");
		btnVoir.setBounds(421, 10, 165, 21);
		btnVoir.addActionListener(this);
		
		
		
		btnPrecedent = new JButton("<-- Précédent");
		btnPrecedent.setBounds(10, 10, 115, 21);
		btnPrecedent.addActionListener(this);
		
		textRecherche = new JTextField();
		textRecherche.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textRecherche.setBounds(0, 10, 417, 19);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 130, 1));
		spinner.setBounds(427, 10, 43, 20);
		
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(480, 9, 106, 21);
		btnRechercher.addActionListener(this);
		
		center = new JPanel();
		center.setBounds(0, 35, 596, 486);
		getContentPane().add(center);
		center.setLayout(new BorderLayout());
		if(tableMedicaments != null) {
			laTable = tableMedicaments;
			scrollable = new JScrollPane(tableMedicaments);
			center.add(tableMedicaments.getTableHeader(),BorderLayout.NORTH);
			center.add(scrollable);
		}		
		
		
		south = new JPanel();
		south.setBounds(0, 523, 596, 39);
		getContentPane().add(south);
		south.setLayout(null);
		south.add(btnPrecedent);
		south.add(btnVoir);
		
		north = new JPanel();
		north.setBounds(0, 0, 596, 37);
		getContentPane().add(north);
		north.setLayout(null);
		north.add(textRecherche);
		north.add(spinner);
		north.add(btnRechercher);
		
		
		
		
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnPrecedent) {
			this.dispose();
			parent.setVisible(true);
			
			
		}else if(e.getSource()==btnRechercher) {
			String nom = textRecherche.getText();
			int range = (int) spinner.getValue();
			DAOfactory dao = new DAOfactory();
			ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchingMedicaments(nom, range);
			String[] header = {"CIS","Nom","Forme pharmaceutique","Etat"};
			String[][] donnees = new String[medicaments.size()][4];
			for(int i=0;i<medicaments.size();i++) {
				donnees[i][0] = medicaments.get(i).getAttributes().get("cis");
				donnees[i][1] = medicaments.get(i).getAttributes().get("denom");
				donnees[i][2] = medicaments.get(i).getAttributes().get("forme");
				donnees[i][3] = medicaments.get(i).getAttributes().get("etatCom");
			}
			JTable tableMedicaments = new JTable(donnees, header);
			this.dispose();
			new RechercherGUI(parent,tableMedicaments);
			
		
		}else if(e.getSource()==btnVoir) {
			int ligne = laTable.getSelectedRow();
			if(ligne == -1) {
				JOptionPane.showMessageDialog(this, "Veuillez selectionner un medicament");
			}else {
				int cis = Integer.parseInt((String) laTable.getValueAt(ligne, 0));
				DAOfactory dao = new DAOfactory();
				HashMap<String, Object> resultat = dao.getAbout(cis);
				new InformationsGUI(this,resultat);
			}
		}
		
	}
	
	public static void main(String[] args) {
		new RechercherGUI(new PrincipalGUI(new ConnexionGUI()),null);
	}
}
