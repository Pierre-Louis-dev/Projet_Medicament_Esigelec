package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

/**
 * JFrame pour faire la recherche d'un médicament et afficher les détails le concernant 
 * @author Groupe 4
 *
 */
public class Details extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnOK;
	private JPanel panel;
	private JScrollPane scrollable;

	public Details(JTable table) {
		setTitle("Details");
		setSize(610, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		
		btnOK = new JButton("OK");
		btnOK.setBounds(250, 225, 128, 37);
		btnOK.addActionListener(this);
		getContentPane().add(btnOK);
		
		panel = new JPanel();
		panel.setBounds(10, 10, 586, 205);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		scrollable = new JScrollPane(table);
		panel.add(table.getTableHeader(),BorderLayout.NORTH);
		panel.add(scrollable);
		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();		
	}
}
