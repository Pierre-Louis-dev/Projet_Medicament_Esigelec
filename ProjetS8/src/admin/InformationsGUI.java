package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project.*;
import javax.swing.JButton;

/**
 * JFrame pour afficher les informations d'un médicament en détail
 * @author Groupe 4
 *
 */
public class InformationsGUI extends JFrame implements ActionListener{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RechercherGUI parent;
	private HashMap<String, Object> all;
	private JButton btnPrecedent;
	private JButton btnSpecialites;
	private JButton btnPresentations;
	private JButton btnInfos;
	private JButton btnAsmr;
	private JButton btnSmr;
	private JButton btnGenerique;
	private JButton btnCompositions;
	private JButton btnConditions;
	private JButton btnLiens;
	
	
	public InformationsGUI(RechercherGUI parent,HashMap<String, Object> all) {
		this.parent=parent;
		this.all=all;
		DTO_CIS_bdpm med = (DTO_CIS_bdpm) all.get("specialites"); 
		setTitle(med.getAttributes().get("denom"));
		parent.setVisible(false);
		setSize(400, 560);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		btnPrecedent = new JButton("Precedent");
		btnPrecedent.setBounds(10, 10, 140, 28);
		btnPrecedent.addActionListener(this);
		getContentPane().add(btnPrecedent);
		
		btnSpecialites = new JButton("Specialites");
		btnSpecialites.setMnemonic('s');
		btnSpecialites.setBounds(106, 48, 163, 29);
		btnSpecialites.addActionListener(this);
		getContentPane().add(btnSpecialites);
		
		btnPresentations = new JButton("Presentations");
		btnPresentations.setMnemonic('p');
		btnPresentations.setBounds(106, 158, 163, 29);
		btnPresentations.addActionListener(this);
		getContentPane().add(btnPresentations);
		
		btnInfos = new JButton("Infos Importantes");
		btnInfos.setMnemonic('i');
		btnInfos.setBounds(106, 433, 163, 29);
		btnInfos.addActionListener(this);
		getContentPane().add(btnInfos);
		
		btnAsmr = new JButton("Avis ASMR");
		btnAsmr.setMnemonic('a');
		btnAsmr.setBounds(106, 323, 163, 29);
		btnAsmr.addActionListener(this);
		getContentPane().add(btnAsmr);
		
		btnSmr = new JButton("Avis SMR");
		btnSmr.setMnemonic('m');
		btnSmr.setBounds(106, 268, 163, 29);
		btnSmr.addActionListener(this);
		getContentPane().add(btnSmr);
		
		btnGenerique = new JButton("Groupe générique");
		btnGenerique.setMnemonic('g');
		btnGenerique.setBounds(106, 103, 163, 29);
		btnGenerique.addActionListener(this);
		getContentPane().add(btnGenerique);
		
		btnCompositions = new JButton("Compositions");
		btnCompositions.setMnemonic('c');
		btnCompositions.setBounds(106, 213, 163, 29);
		btnCompositions.addActionListener(this);
		getContentPane().add(btnCompositions);
		
		btnConditions = new JButton("Conditions");
		btnConditions.setMnemonic('d');
		btnConditions.setBounds(106, 378, 163, 29);
		btnConditions.addActionListener(this);
		getContentPane().add(btnConditions);
		
		btnLiens = new JButton("Lien");
		btnLiens.setMnemonic('l');
		btnLiens.setBounds(106, 484, 163, 28);
		btnLiens.addActionListener(this);
		getContentPane().add(btnLiens);
		
		setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stubs	
		if(e.getSource()==btnPrecedent) {
			this.dispose();
			parent.setVisible(true);
		}else if(e.getSource()==btnSpecialites) {
			DTO_CIS_bdpm m = (DTO_CIS_bdpm)all.get("specialites");
			JOptionPane.showMessageDialog(null, "CIS : "+ m.getAttributes().get("cis")
					+"\nNom : "+  m.getAttributes().get("denom")
					+"\nForme Pharmaceutique : "+  m.getAttributes().get("forme")
					+"\nVoies d'administration : "+  m.getAttributes().get("voies")
					+"\nStatut : "+  m.getAttributes().get("statAam")
					+"\nType de procédure : "+  m.getAttributes().get("typeProcedAam")
					+"\nEtat : "+  m.getAttributes().get("etatCom")
					+"\nStatut BDM : "+  m.getAttributes().get("statutBdm")
					+"\nAutorisation Europeenne : "+  m.getAttributes().get("numAutEur")
					+"\nTitulaires : "+  m.getAttributes().get("titulaires")
					+"\nSurveillance : "+  m.getAttributes().get("surveillance"), "Specialites", JOptionPane.INFORMATION_MESSAGE);
		
		
		
		}else if(e.getSource()==btnPresentations) {
			ArrayList<DTO_CIS_CIP_bdpm> c = (ArrayList<DTO_CIS_CIP_bdpm>)all.get("presentations");
			if(c.size()==0) {
				JOptionPane.showMessageDialog(this, "Pas de présentation");
			}else {
				String[] header = {"cis","cip7","Libelle","Statut","Etat","Date","cip13","Agrement","Taux","Prix","Indications"};
				Object[][] donnees = new Object[c.size()][11];
				for(int i=0;i<c.size();i++) {
					donnees[i][0] = c.get(i).getAttributes().get("cis");
					donnees[i][1] = c.get(i).getAttributes().get("cip7");
					donnees[i][2] = c.get(i).getAttributes().get("libelle");
					donnees[i][3] = c.get(i).getAttributes().get("statut");
					donnees[i][4] = c.get(i).getAttributes().get("etatCom");
					donnees[i][5] = c.get(i).getAttributes().get("dateDeclaCom");
					donnees[i][6] = c.get(i).getAttributes().get("cip13");
					donnees[i][7] = c.get(i).getAttributes().get("agrement");
					donnees[i][8] = c.get(i).getAttributes().get("taux");
					donnees[i][9] = c.get(i).getAttributes().get("prix");
					donnees[i][10] = c.get(i).getAttributes().get("indications");
				}
				JTable table = new JTable(donnees,header);
				new Details(table);
			}		
		
		
		
		}else if(e.getSource()==btnGenerique) {
			ArrayList<DTO_CIS_GENER_bdpm> groupes = (ArrayList<DTO_CIS_GENER_bdpm>) all.get("generique");
			if(groupes.size()==0) {
				JOptionPane.showMessageDialog(this, "Pas de groupe générique associé");
			}else {
				DTO_CIS_GENER_bdpm m = groupes.get(0);
				JOptionPane.showMessageDialog(null, "Identifiant groupe : "+ m.getAttributes().get("idGroup")
						+"\nLibelle : "+  m.getAttributes().get("libelleGroup")
						+"\nCIS : "+  m.getAttributes().get("cis")
						+"\nType : "+  m.getAttributes().get("typeGener")
						+"\nNuméro de tri : "+  m.getAttributes().get("numeroTri"), "Groupe", JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		
		}else if(e.getSource()==btnAsmr) {
			ArrayList<DTO_CIS_HAS_ASMR_bdpm> c = (ArrayList<DTO_CIS_HAS_ASMR_bdpm>)all.get("asmr");
			if(c.size()==0) {
				JOptionPane.showMessageDialog(this, "Pas d'avis ASMR");
			}else {
				String[] header = {"cis","Code Dossier","Motif","Date","Valeur","Libelle"};
				Object[][] donnees = new Object[c.size()][6];
				for(int i=0;i<c.size();i++) {
					donnees[i][0] = c.get(i).getAttributes().get("cis");
					donnees[i][1] = c.get(i).getAttributes().get("codeDossier");
					donnees[i][2] = c.get(i).getAttributes().get("motif");
					donnees[i][3] = c.get(i).getAttributes().get("date");
					donnees[i][4] = c.get(i).getAttributes().get("valeurASMR");
					donnees[i][5] = c.get(i).getAttributes().get("libelleASMR");
				}
				JTable table = new JTable(donnees,header);
				new Details(table);
			}
			
		
		
		
		}else if(e.getSource()==btnSmr) {
			ArrayList<DTO_CIS_HAS_SMR_bdpm> c = (ArrayList<DTO_CIS_HAS_SMR_bdpm>)all.get("smr");
			if(c.size()==0) {
				JOptionPane.showMessageDialog(this, "Pas d'avis SMR");
			}else {
				String[] header = {"cis","Code Dossier","Motif","Date","Valeur","Libelle"};
				Object[][] donnees = new Object[c.size()][6];
				for(int i=0;i<c.size();i++) {
					donnees[i][0] = c.get(i).getAttributes().get("cis");
					donnees[i][1] = c.get(i).getAttributes().get("codeDossier");
					donnees[i][2] = c.get(i).getAttributes().get("motif");
					donnees[i][3] = c.get(i).getAttributes().get("date");
					donnees[i][4] = c.get(i).getAttributes().get("valeurSMR");
					donnees[i][5] = c.get(i).getAttributes().get("libelleSMR");
				}
				JTable table = new JTable(donnees,header);
				new Details(table);
			}
		
		
		
		}else if(e.getSource()==btnLiens) {
			if(all.get("liens")!= null) {
				DTO_HAS_LiensPageCT_bdpm m = (DTO_HAS_LiensPageCT_bdpm) all.get("liens");
					JOptionPane.showMessageDialog(null, "Code de Dossier : "+ m.getAttributes().get("codeDossier")
							+"\nLien : "+  m.getAttributes().get("lien")
							, "Liens", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "Pas de lien");
			}
			
			
		}else if(e.getSource()==btnConditions) {
			ArrayList<DTO_CIS_CPD_bdpm> c = (ArrayList<DTO_CIS_CPD_bdpm>) all.get("conditions");
			if(c.size()==0) {
				JOptionPane.showMessageDialog(this, "Pas de condition");
			}else {
				String[] header = {"cis","cpd"};
				Object[][] donnees = new Object[c.size()][2];
				for(int i=0;i<c.size();i++) {
					donnees[i][0] = c.get(i).getAttributes().get("cis");
					donnees[i][1] = c.get(i).getAttributes().get("cpd");
				}
				JTable table = new JTable(donnees,header);
				new Details(table);
			}
			
		
		
		}else if(e.getSource()==btnCompositions) {
			ArrayList<DTO_CIS_COMPO_bdpm> c = (ArrayList<DTO_CIS_COMPO_bdpm>)all.get("compositions");
			if(c.size()==0) {
				JOptionPane.showMessageDialog(this, "Pas de compostion");
			}else {
				String[] header = {"cis","designation","Code Substance","Nom","Dosage","Reference","Nature","Numero"};
				Object[][] donnees = new Object[c.size()][8];
				for(int i=0;i<c.size();i++) {
					donnees[i][0] = c.get(i).getAttributes().get("cis");
					donnees[i][1] = c.get(i).getAttributes().get("designation");
					donnees[i][2] = c.get(i).getAttributes().get("codeSubstance");
					donnees[i][3] = c.get(i).getAttributes().get("denomination");
					donnees[i][4] = c.get(i).getAttributes().get("dosage");
					donnees[i][5] = c.get(i).getAttributes().get("reference");
					donnees[i][6] = c.get(i).getAttributes().get("natureComposant");
					donnees[i][7] = c.get(i).getAttributes().get("numeroLiaison");
				}
				JTable table = new JTable(donnees,header);
				new Details(table);
			}
		
		
		
		}else if(e.getSource()==btnInfos) {
			ArrayList<DTO_CIS_INFOS_bdpm> c = (ArrayList<DTO_CIS_INFOS_bdpm>)all.get("infos");
			if(c.size()==0) {
				JOptionPane.showMessageDialog(this, "Pas d'information");
			}else {
				String[] header = {"cis","Debut","Fin","Lien"};
				Object[][] donnees = new Object[c.size()][4];
				for(int i=0;i<c.size();i++) {
					donnees[i][0] = c.get(i).getAttributes().get("cis");
					donnees[i][1] = c.get(i).getAttributes().get("dateDebut");
					donnees[i][2] = c.get(i).getAttributes().get("dateFin");
					donnees[i][3] = c.get(i).getAttributes().get("lienText");
				}
				JTable table = new JTable(donnees,header);
				new Details(table);
			}
		}
		
	}
}
