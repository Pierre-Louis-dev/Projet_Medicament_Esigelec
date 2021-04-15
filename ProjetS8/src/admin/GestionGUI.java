package admin;

import project.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * JFrame pour la gestion des Personnels de santé
 * @author Groupe 4
 *
 */
public class GestionGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrincipalGUI parent;
	private JButton btnValider;
	private JButton btnSupprimer;
	private JButton btnRafraichir;
	private JScrollPane scrollable;
	private JTable tableUsers;
	private JButton btnPrecedent;
	private JButton btnRefuser;
	private JPanel north;
	private JPanel south;
	
	
	public GestionGUI(PrincipalGUI parent) {
		setTitle("Gestion des utilisateurs");
		this.parent=parent;
		parent.setVisible(false);
		setSize(449, 350);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		PersonnelDAO dao = new PersonnelDAO();
		ArrayList<DTO_Personnel> personnels = dao.getPersonnels();
		String[] header= {"id","Nom","Prénom","Login","Profession","Statut"};
		String[][] donnees = new String[personnels.size()][6] ;
		for(int i=0;i<personnels.size();i++) {
			donnees[i][0]=personnels.get(i).getAttributes().get("id");
			donnees[i][1]=personnels.get(i).getAttributes().get("nom");
			donnees[i][2]=personnels.get(i).getAttributes().get("prenom");
			donnees[i][3]=personnels.get(i).getAttributes().get("login");
			donnees[i][4]=personnels.get(i).getAttributes().get("metier");
			donnees[i][5]=personnels.get(i).getAttributes().get("statut");
		}
		tableUsers = new JTable(donnees, header);
		scrollable = new JScrollPane(tableUsers);
		
		btnValider = new JButton("Valider");
		btnValider.setBounds(116, 20, 96, 21);
		btnValider.addActionListener(this);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(330, 20, 96, 21);
		btnSupprimer.addActionListener(this);
		
		btnRafraichir = new JButton("Rafraichir");
		btnRafraichir.setBounds(10, 20, 96, 21);
		btnRafraichir.addActionListener(this);
		
		btnPrecedent = new JButton("Précédent");
		btnPrecedent.setBounds(10, 55, 103, 21);
		btnPrecedent.addActionListener(this);
		
		
		btnRefuser = new JButton("Refuser");
		btnRefuser.setBounds(222, 20, 96, 21);
		btnRefuser.addActionListener(this);

		
		north = new JPanel();
		north.setBounds(0, 0, 437, 216);
		getContentPane().add(north);
		north.setLayout(new BorderLayout());
		north.add(tableUsers.getTableHeader(),BorderLayout.NORTH);
		north.add(scrollable);
		
		south = new JPanel();
		south.setBounds(0, 226, 437, 86);
		getContentPane().add(south);
		south.setLayout(null);
		south.add(btnPrecedent);
		south.add(btnValider);
		south.add(btnSupprimer);
		south.add(btnRafraichir);
		south.add(btnRefuser);
		
		
		
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnPrecedent)) {
			this.dispose();
			parent.setVisible(true);
		
		}else if(e.getSource()==btnSupprimer) {
			int ligne = tableUsers.getSelectedRow();
			String login = (String) tableUsers.getValueAt(ligne, 3);
			PersonnelDAO dao = new PersonnelDAO();
			if(dao.deletePersonnel(login)) {
				JOptionPane.showMessageDialog(this, "Utilisateur supprimé");
				this.dispose();
				new GestionGUI(parent);
			}else {
				JOptionPane.showMessageDialog(this, "Erreur");
			}
			
			
		}else if(e.getSource()==btnValider) {
			int ligne = tableUsers.getSelectedRow();
			String login = (String) tableUsers.getValueAt(ligne, 3);
			PersonnelDAO dao = new PersonnelDAO();
			if(dao.ValidateUserCredentials(login)) {
				JOptionPane.showMessageDialog(this, "Utilisateur validé");
				this.dispose();
				new GestionGUI(parent);
			}else {
				JOptionPane.showMessageDialog(this, "Erreur");
			}
			
		}else if(e.getSource()==btnRafraichir) {
			this.dispose();
			new GestionGUI(parent);
		}else if(e.getSource()==btnRefuser) {
			int ligne = tableUsers.getSelectedRow();
			String login = (String) tableUsers.getValueAt(ligne, 3);
			PersonnelDAO dao = new PersonnelDAO();
			if(dao.RefuseUserCredentials(login)) {
				JOptionPane.showMessageDialog(this, "Utilisateur refusé");
				this.dispose();
				new GestionGUI(parent);
			}else {
				JOptionPane.showMessageDialog(this, "Erreur");
			}
		}
		
	}
	
	public static void main(String[] args) {
		new GestionGUI(new PrincipalGUI(new ConnexionGUI()));
	}
}
