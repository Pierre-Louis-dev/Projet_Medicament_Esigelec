package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;

/**
 * JFrame d'accueil du client lourd, vient juste après la connexion
 * @author Groupe 4
 *
 */
public class PrincipalGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnGestion;
	private JButton btnRechercher;
	private JButton btnDeconnexion;
	private ConnexionGUI parent;
	private JButton btnHistorique;
	private JButton btnHistUtilisateur;
	
	public PrincipalGUI(ConnexionGUI connexion) {
		parent = connexion;
		connexion.setVisible(false);
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		btnGestion = new JButton("Gérer les utilisateurs");
		btnGestion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGestion.setMnemonic('g');
		btnGestion.setBounds(47, 100, 334, 33);
		btnGestion.addActionListener(this);
		getContentPane().add(btnGestion);
		
		btnRechercher = new JButton("Rechercher un médicament");
		btnRechercher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRechercher.setMnemonic('r');
		btnRechercher.setBounds(47, 158, 334, 33);
		btnRechercher.addActionListener(this);
		getContentPane().add(btnRechercher);
		
		btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.setFont(new Font("Tahoma", Font.ITALIC, 10));
		btnDeconnexion.setBounds(149, 280, 114, 33);
		btnDeconnexion.addActionListener(this);
		getContentPane().add(btnDeconnexion);
		
		btnHistorique = new JButton("Historique des connexions");
		btnHistorique.setMnemonic('h');
		btnHistorique.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHistorique.setBounds(47, 37, 334, 33);
		btnHistorique.addActionListener(this);
		getContentPane().add(btnHistorique);
		
		btnHistUtilisateur = new JButton("Historique des utilisateurs");
		btnHistUtilisateur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHistUtilisateur.setMnemonic('u');
		btnHistUtilisateur.setBounds(47, 221, 334, 33);
		btnHistUtilisateur.addActionListener(this);
		getContentPane().add(btnHistUtilisateur);
		
		
		
		setSize(431, 380);
		setResizable(false);
		setLocationRelativeTo(connexion);
		setVisible(true);		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnDeconnexion) {
			this.dispose();
			parent.setVisible(true);			
		}else if(e.getSource()==btnGestion) {
			new GestionGUI(this);
		}else if(e.getSource()==btnRechercher) {
			new RechercherGUI(this,null);
		}else if(e.getSource()==btnHistorique) {
			new Historique();
		}else if(e.getSource()==btnHistUtilisateur) {
			new HistoriqueUtilisateurs();
		}
		
	}
}
