package admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * JFrame qui représente la page d'accueil du client lourd
 * @author Groupe 4
 *
 */
public class ConnexionGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textLogin;
	private JLabel labelLogin;
	private JPasswordField password;
	private JLabel labelPassword;
	private JButton btnConnexion;
	private JButton btnQuitter;
	
	
	
	public ConnexionGUI() {
		setTitle("Référencement de médicaments");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(430, 315);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		textLogin = new JTextField();
		textLogin.setBounds(121, 39, 275, 19);
		getContentPane().add(textLogin);
		
		password = new JPasswordField();
		password.setBounds(121, 112, 275, 19);
		getContentPane().add(password);
		
		labelLogin = new JLabel("Login");
		labelLogin.setLabelFor(textLogin);
		labelLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelLogin.setBounds(40, 39, 55, 19);
		getContentPane().add(labelLogin);
		
		labelPassword = new JLabel("Mot de passe");
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		labelPassword.setLabelFor(password);
		labelPassword.setBounds(10, 112, 118, 19);
		getContentPane().add(labelPassword);
		
		btnConnexion = new JButton("Connexion");
		btnConnexion.setMnemonic('c');
		btnConnexion.setBounds(43, 205, 100, 34);
		btnConnexion.addActionListener(this);
		getContentPane().add(btnConnexion);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.setMnemonic('q');
		btnQuitter.setBounds(259, 205, 100, 34);
		btnQuitter.addActionListener(this);
		getContentPane().add(btnQuitter);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnQuitter) {
			this.dispose();
			
			
		}else if(e.getSource()==btnConnexion) {
			AdminDAO dao = new AdminDAO();
			String login = textLogin.getText();
			
			@SuppressWarnings("deprecation")
			String mdp = password.getText();
			if(dao.Connexion(login, mdp)) {
				password.setText("");
				new PrincipalGUI(this);
			}else {
				JOptionPane.showMessageDialog(this, "Veuillez entrer de bons identifiants", "Warning",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ConnexionGUI();
	}	
}
