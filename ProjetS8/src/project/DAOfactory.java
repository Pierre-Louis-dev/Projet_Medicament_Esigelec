package project;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;

/**
 * Classe DAOfactory
 * Permet de faire la liasion entre le client léger (la partie médicaments) et la base de données
 * @author Groupe 4
 *
 */
public class DAOfactory {
	private final String url = "jdbc:mysql://localhost/medicaments2";
	private final String user = "root";
	private final String password = "root";
	private final String location = "com.mysql.jdbc.Driver";
	private Connection conn = null;
	
	public DAOfactory() {
		try {
			Class.forName(location);
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	public boolean open() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
		return true;

	}
	
	public void close() {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	 //Recuperation des tables
	
	
	/**
	 * Permet de récupérer une liste de tous les médicaments dont le nom correspond aux informations données
	 * @param nom	
	 * 				le nom ( ou le début du nom ) du médicament
	 * @param range
	 * 				le nombre maximum de résultats souhaité
	 * @return medicaments
	 * 				la liste des médicaments concernés
	 */
		 public ArrayList<DTO_CIS_bdpm> getListeSearchingMedicaments(String nom, int range){
			 ArrayList<DTO_CIS_bdpm> medicaments = new ArrayList<DTO_CIS_bdpm>();
			 DTO_CIS_bdpm med;
				if(open()) {
					String query="select * from cis_bdpm";
					try {	
							int i=0;
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next() && i<range) {
									med = new DTO_CIS_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
									if(med.getAttributes().get("denom").substring(0,nom.length()).equalsIgnoreCase(nom)) {
										medicaments.add(med);
										i++;
									}									
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return medicaments;
		 }
		 
		 
		 
		 /**
			 * Permet de récupérer une liste triée par ordre alphabétique de tous les médicaments dont le nom 
			 * correspond aux informations données
			 * @param nom	
			 * 				le nom ( ou le début du nom ) du médicament
			 * @param range
			 * 				le nombre maximum de résultats souhaité
			 * @return medicaments
			 * 				la liste des médicaments concernés
			 */
		 public ArrayList<DTO_CIS_bdpm> getListeSortedSearchingMedicaments(String nom, int range){
			 ArrayList<DTO_CIS_bdpm> medicaments = new ArrayList<DTO_CIS_bdpm>();
			 DTO_CIS_bdpm med;
				if(open()) {
					String query="select * from cis_bdpm order by denom ASC";
					try {	
							int i=0;
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next() && i<range) {
									med = new DTO_CIS_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
									if(med.getAttributes().get("denom").substring(0,nom.length()).equalsIgnoreCase(nom)) {
										medicaments.add(med);
										i++;
									}									
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return medicaments;
		 }
		 
	 
		 /**
			 * Permet de récupérer une liste de toutes les présentations d'un médicament donné
			 * @param cis	
			 * 				le numéro cis du médicament
			 * @return presentations
			 * 				la liste des présentations du médicament
			 */
		 public ArrayList<DTO_CIS_CIP_bdpm> getPresentations(int cis){
			 ArrayList<DTO_CIS_CIP_bdpm> presentations = new ArrayList<DTO_CIS_CIP_bdpm>();
			 DTO_CIS_CIP_bdpm pres;
				if(open()) {
					String query="select * from cis_cip_bdpm where cis = "+cis;
					int i=0;  //compteur pour avoir le nombre de lignes
					try {				
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next() && i<25) {
									pres = new DTO_CIS_CIP_bdpm(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
									presentations.add(pres);
									i++;	//incr�mentation du compteur
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return presentations;
		 }
		 
		 
		 
		 /**
			 * Permet de récupérer une liste de toutes les composantes d'un médicament donné
			 * @param cis	
			 * 				le numéro cis du médicament
			 * @return compositions
			 * 				la liste des composantes du médicament
			 */
		 public ArrayList<DTO_CIS_COMPO_bdpm> getCompositions(int cis){
			 ArrayList<DTO_CIS_COMPO_bdpm> compositions = new ArrayList<DTO_CIS_COMPO_bdpm>();
			 DTO_CIS_COMPO_bdpm comp;
				if(open()) {
					String query="select * from cis_compo_bdpm where cis = "+cis;
					try {				
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
									comp = new DTO_CIS_COMPO_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),Integer.parseInt(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),Integer.parseInt(rs.getString(8)));
									compositions.add(comp);
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return compositions;
		 }
		 
		 /**
			 * Permet de récupérer une liste de toutes les composantes d'un médicament donné
			 * Avec la spécification de SA ou FT
			 * @param cis	
			 * 				le numéro cis du médicament
			 * @param nature
			 * 				la nature des composantes souhaitées
			 * @return medicaments
			 * 				la liste des composantes du médicament
			 */
		 public ArrayList<DTO_CIS_COMPO_bdpm> getSubstances(int cis,String nature){
			 ArrayList<DTO_CIS_COMPO_bdpm> compositions = new ArrayList<DTO_CIS_COMPO_bdpm>();
			 DTO_CIS_COMPO_bdpm comp;
				if(open()) {
					String query="select * from cis_compo_bdpm where cis = ? and natureComposant = ?";
					try {				
							PreparedStatement ps = conn.prepareStatement(query);
							ps.setInt(1, cis);
							ps.setString(2, nature);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
									comp = new DTO_CIS_COMPO_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),Integer.parseInt(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),Integer.parseInt(rs.getString(8)));
									compositions.add(comp);
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return compositions;
		 }
		 
		 
		 /**
			 * Permet de récupérer une liste de tous les médicaments dont le nom 
			 * correspond aux informations données et qui sont commercialisés
			 * @param nom	
			 * 				le nom ( ou le début du nom ) du médicament
			 * @param range
			 * 				le nombre maximum de résultats souhaité
			 * @return medicaments
			 * 				la liste des médicaments concernés
			 */
		 public ArrayList<DTO_CIS_bdpm> getListeSearchingMedicamentsCommercialises(String nom, int range){
			 ArrayList<DTO_CIS_bdpm> medicaments = new ArrayList<DTO_CIS_bdpm>();
			 DTO_CIS_bdpm med;
				if(open()) {
					String query="select * from cis_bdpm where etatCom = \"Commercialisée\"";
					try {	
							int i=0;
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next() && i<range) {
									med = new DTO_CIS_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
									if((med.getAttributes().get("denom").substring(0,nom.length())).equalsIgnoreCase(nom)) {
										ArrayList<DTO_CIS_GENER_bdpm> groupe = this.getGroupeByCisMedicament(Integer.parseInt(med.getAttributes().get("cis")));
										if(groupe.size() != 0) {
											med.getAttributes().put("generique", "existe");
										}
										medicaments.add(med);
										i++;
									}									
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return medicaments;
		 }
		 
		 
		 /**
			 * Permet de récupérer une liste triée par ordre alphabétique de tous les médicaments dont le nom 
			 * correspond aux informations données et qui sont commercialisés
			 * @param nom	
			 * 				le nom ( ou le début du nom ) du médicament
			 * @param range
			 * 				le nombre maximum de résultats souhaité
			 * @return medicaments
			 * 				la liste des médicaments concernés
			 */
		 public ArrayList<DTO_CIS_bdpm> getListeSortedSearchingMedicamentsCommercialises(String nom,int range){
			 ArrayList<DTO_CIS_bdpm> medicaments = new ArrayList<DTO_CIS_bdpm>();
			 DTO_CIS_bdpm med;
				if(open()) {
					String query="select * from cis_bdpm where etatCom = \"Commercialisée\" order by denom ASC";
					try {	
							int i=0;
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next() && i< range) {
									med = new DTO_CIS_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
									if(med.getAttributes().get("denom").substring(0,nom.length()).equalsIgnoreCase(nom)) {
										ArrayList<DTO_CIS_GENER_bdpm> groupe = this.getGroupeByCisMedicament(Integer.parseInt(med.getAttributes().get("cis")));
										if(groupe.size() != 0) {
											med.getAttributes().put("generique", "existe");
										}
										medicaments.add(med);
										i++;
									}									
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return medicaments;
		 }
		 
		 
		 /**
			 * Permet de récupérer une liste de tous les médicaments dont le nom 
			 * correspond aux informations données et qui ne sont plus commercialisés
			 * @param nom	
			 * 				le nom ( ou le début du nom ) du médicament
			 * @param range
			 * 				le nombre maximum de résultats souhaité
			 * @return medicaments
			 * 				la liste des médicaments concernés
			 */
		 public ArrayList<DTO_CIS_bdpm> getListeSearchingMedicamentsNonCommercialises(String nom , int range){
			 ArrayList<DTO_CIS_bdpm> medicaments = new ArrayList<DTO_CIS_bdpm>();
			 DTO_CIS_bdpm med;
				if(open()) {
					String query="select * from cis_bdpm where etatCom = \"Non commercialisée\"";
					try {	
							int i=0;
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next() && i<range) {
									med = new DTO_CIS_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
									if(med.getAttributes().get("denom").substring(0,nom.length()).equalsIgnoreCase(nom)) {
										ArrayList<DTO_CIS_GENER_bdpm> groupe = this.getGroupeByCisMedicament(Integer.parseInt(med.getAttributes().get("cis")));
										if(groupe.size() != 0) {
											med.getAttributes().put("generique", "existe");
										}
										medicaments.add(med);
										i++;
									}									
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return medicaments;
		 }
		 
		 
		 /**
			 * Permet de récupérer une liste triée par ordre alphabétique de tous les médicaments dont le nom 
			 * correspond aux informations données et qui ne sont plus commercialisés
			 * @param nom	
			 * 				le nom ( ou le début du nom ) du médicament
			 * @param range
			 * 				le nombre maximum de résultats souhaité
			 * @return medicaments
			 * 				la liste des médicaments concernés
			 */
		 public ArrayList<DTO_CIS_bdpm> getListeSortedSearchingMedicamentsNonCommercialises(String nom, int range){
			 ArrayList<DTO_CIS_bdpm> medicaments = new ArrayList<DTO_CIS_bdpm>();
			 DTO_CIS_bdpm med;
				if(open()) {
					String query="select * from cis_bdpm where etatCom = \"Non commercialisée\" order by denom ASC";
					try {	
							int i=0;
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next() && i<range) {
									med = new DTO_CIS_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
									if(med.getAttributes().get("denom").substring(0,nom.length()).equalsIgnoreCase(nom)) {
										ArrayList<DTO_CIS_GENER_bdpm> groupe = this.getGroupeByCisMedicament(Integer.parseInt(med.getAttributes().get("cis")));
										if(groupe.size() != 0) {
											med.getAttributes().put("generique", "existe");
										}
										medicaments.add(med);
										i++;
									}									
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return medicaments;
		 }
		 
		 /**
		  * Permet de récupérer les liens des pages correspondant au code de dossier donné
		  * @param nom
		  * 			le code du dossier concerné
		  * @return lien
		  * 			le lien HAS du dossier
		  */
		 public DTO_HAS_LiensPageCT_bdpm getLien(String nom){
			 DTO_HAS_LiensPageCT_bdpm lien=null;
				if(open()) {
					String query="SELECT * FROM has_lienspagect_bdpm";
					try {	
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
								if(rs.getString(1).equalsIgnoreCase(nom)) {
									lien = new DTO_HAS_LiensPageCT_bdpm(rs.getString(1),rs.getString(2));
								}
								
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return lien;
		 }
		 
		 
		 /**
		  * Permet de récupérer les avis ASMR concernant un médicament donné
		  * @param cis
		  * 			le numéro cis du médicament
		  * @return avis
		  * 			la liste des avis ASMR le concernant
		  */
		 public ArrayList<DTO_CIS_HAS_ASMR_bdpm> getAvisASMR(int cis){
			 ArrayList<DTO_CIS_HAS_ASMR_bdpm> avis = new ArrayList<DTO_CIS_HAS_ASMR_bdpm>();
			 DTO_CIS_HAS_ASMR_bdpm av;
				if(open()) {
					String query="select * from cis_has_asmr_bdpm where cis = "+cis;
					try {	
							
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
									av = new DTO_CIS_HAS_ASMR_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
									int annee = Integer.parseInt(av.getAttributes().get("date").substring(0,4));
									if(annee > 2002) {
										avis.add(av);
									}									
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return avis;
		 }
		 
		 /**
		  * Permet de récupérer les avis SMR concernant un médicament donné
		  * @param cis
		  * 			le numéro cis du médicament
		  * @return avis
		  * 			la liste des avis SMR le concernant
		  */
		 public ArrayList<DTO_CIS_HAS_SMR_bdpm> getAvisSMR(int cis){
			 ArrayList<DTO_CIS_HAS_SMR_bdpm> avis = new ArrayList<DTO_CIS_HAS_SMR_bdpm>();
			 DTO_CIS_HAS_SMR_bdpm av;
				if(open()) {
					String query="select * from cis_has_smr_bdpm where cis = "+cis;
					try {	
							
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
									av = new DTO_CIS_HAS_SMR_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
									int annee = Integer.parseInt(av.getAttributes().get("date").substring(0,4));
									if(annee > 2002) {
										avis.add(av);
									}									
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return avis;
		 }
		 
		 /**
		  * Permet de connaitre le code de dossier affecté à un médicament à partir de son numéro cis
		  * @param cis
		  * 		le numéro cis du médicament
		  * @return code
		  * 		le code de dossier du médicament
		  */
		 public String getCodeDossierHAS(int cis) {
			 ArrayList<DTO_CIS_HAS_SMR_bdpm> listeSmr = getAvisSMR(cis);
			 DTO_CIS_HAS_SMR_bdpm smr = listeSmr.get(0);
			 return smr.getAttributes().get("codeDossier");
		 }
		 
		 /**
		  * Permet de retrouver une liste de groupes génériques dont les noms correspondent aux paramètres donnés
		  * @param nom
		  * 			le nom(ou le début) du groupe générique
		  * @param range
		  * 			le nombre de résultats souhaité
		  * @return groupe
		  * 			la liste des groupes génériques concernés
		  */
		 public ArrayList<DTO_CIS_GENER_bdpm> getListeSearchingGroupeGenerique(String nom, int range){
			 ArrayList<DTO_CIS_GENER_bdpm> groupe = new ArrayList<DTO_CIS_GENER_bdpm>();
			 DTO_CIS_GENER_bdpm grp;
				if(open()) {
					String query="select * from cis_gener_bdpm";
					try {	
							int i=0;
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next() && i<range) {
									grp = new DTO_CIS_GENER_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)));
									if((grp.getAttributes().get("libelleGroup").toLowerCase()).contains(nom.toLowerCase())) {
										groupe.add(grp);
										i++;
									}
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return groupe;
		 }
		 
		 /**
		  * Permet de retrouver une liste triée dans l'ordre alphabétique de groupes génériques dont les noms 
		  * correspondent aux paramètres donnés
		  * @param nom
		  * 			le nom(ou le début) du groupe générique
		  * @param range
		  * 			le nombre de résultats souhaité
		  * @return groupe
		  * 			la liste des groupes génériques concernés
		  */
		 public ArrayList<DTO_CIS_GENER_bdpm> getListeSortedSearchingGroupeGenerique(String nom, int range){
			 ArrayList<DTO_CIS_GENER_bdpm> groupe = new ArrayList<DTO_CIS_GENER_bdpm>();
			 DTO_CIS_GENER_bdpm grp;
				if(open()) {
					String query="select * from cis_gener_bdpm order by libelleGroup ASC";
					try {	
							int i=0;
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next() && i<range) {
									grp = new DTO_CIS_GENER_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)));
									if((grp.getAttributes().get("libelleGroup").toLowerCase()).contains(nom.toLowerCase())) {
										groupe.add(grp);
										i++;
									}
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return groupe;
		 }
		 
		 /**
		  * Permet de retrouver une liste de médicaments appartenant à un groupe générique donné
		  * 			l'id du groupe générique
		  * @return groupe
		  * 			la liste des médicaments concernés
		  */
		 public ArrayList<DTO_CIS_bdpm> getListeSearchByIdGroupeGenerique(int id){
			 ArrayList<DTO_CIS_bdpm> medicaments = new ArrayList<DTO_CIS_bdpm>();
			 DTO_CIS_GENER_bdpm grp;
			 DTO_CIS_bdpm med=null;
				if(open()) {
					String query="select * from cis_gener_bdpm ";
					try {	
							
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
								grp = new DTO_CIS_GENER_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)));
								if(Integer.parseInt(grp.getAttributes().get("idGroup"))==id) {
									med = this.getSearchByCisMedicament(Integer.parseInt(grp.getAttributes().get("cis")));
									if(med!=null) {
										medicaments.add(med);
									}									
								}								
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return medicaments;
		 }
		 
		 /**
		  * Permet de retrouver un médicament à partir de son numéro cis
		  * @param cis
		  * 			le numéro cis du médicamnt
		  * @return med
		  * 			le médicament concerné
		  */
		 public DTO_CIS_bdpm getSearchByCisMedicament(int cis){
			 DTO_CIS_bdpm med=null;
				if(open()) {
					String query="select * from cis_bdpm ";
					try {	
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
								if(Integer.parseInt(rs.getString(1))==cis)
									med = new DTO_CIS_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return med;
		 }
		 
		 /**
		  * Permet de recupérer les groupes génériques auxquels appartient un médicament donné
		  * @param cis
		  * 			le numéro cis du médicament
		  * @return groupe
		  * 			la liste des groupes génériques du médicament
		  */
		 public ArrayList<DTO_CIS_GENER_bdpm> getGroupeByCisMedicament(int cis){
			 ArrayList<DTO_CIS_GENER_bdpm> groupes = new ArrayList<DTO_CIS_GENER_bdpm>();
			 DTO_CIS_GENER_bdpm grp;
				if(open()) {
					String query="select * from cis_gener_bdpm where cis = "+cis;
					try {				
							PreparedStatement ps = conn.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
									grp = new DTO_CIS_GENER_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)));
									if(this.getSearchByCisMedicament(rs.getInt(3))!=null) {
										groupes.add(grp);
									}									
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return groupes;
		 }
		 
		 
		 /**
		  * Permet de recupérer les conditions de prescritpion et de délivrance d'un médicament donné
		  * @param cis
		  * 			le numéro cis du médicament
		  * @return conditions
		  * 			la liste des conditions du médicament
		  */
		 public ArrayList<DTO_CIS_CPD_bdpm> getConditionsPrescriptionDelivrance(int cis){
			 ArrayList<DTO_CIS_CPD_bdpm> conditions = new ArrayList<DTO_CIS_CPD_bdpm>();
			 DTO_CIS_CPD_bdpm cdt;
				if(open()) {
					String query="select * from cis_cpd_bdpm where cis = ?";
					try {	
							
							PreparedStatement ps = conn.prepareStatement(query);
							ps.setInt(1, cis);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
								cdt = new DTO_CIS_CPD_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2));
									conditions.add(cdt);
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return conditions;
		 }
		 
		 /**
		  * Permet de recupérer les infos importantes sur un médicament donné
		  * @param cis
		  * 			le numéro cis du médicament
		  * @return conditions
		  * 			la liste des infos du médicament
		  */
		 public ArrayList<DTO_CIS_INFOS_bdpm> getListeInfoImportantes(int cis){
			 ArrayList<DTO_CIS_INFOS_bdpm> infosImp = new ArrayList<DTO_CIS_INFOS_bdpm>();
			 DTO_CIS_INFOS_bdpm inf;
				if(open()) {
					String query="select * from cis_infos_bdpm where cis = ?";
					try {	
							
							PreparedStatement ps = conn.prepareStatement(query);
							ps.setInt(1, cis);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
								inf = new DTO_CIS_INFOS_bdpm(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4));
									infosImp.add(inf);
							}				
					}catch(SQLException sqle) {
						sqle.printStackTrace();
					}finally {
						close();
					}
				}
			 return infosImp;
		 }
		 
		 
		/**
		 * Permet de récupérer tous les détails d'un médicament à parir de son numéro cis
		 * @param cis
		 * 				le numéro cis du médicament
		 * @return all
		 * 				une HashMap où sont stockés tous les détails du médicament comme suit:
		 * 
		 *all.put("specialites", specialites);
		 *all.put("presentations", presentations);
		 *all.put("compositions", compositions);
		 *all.put("smr", smr);
		 *all.put("asmr", asmr);
		 *all.put("generique", generique);
		 *all.put("conditions", conditions);
		 *all.put("infos", infos);
		 *all.put("liens", liens);
		 */
		 public HashMap<String,Object> getAbout(int cis){
			 HashMap<String, Object> all = new HashMap<String, Object>();
			 DTO_CIS_bdpm specialites = this.getSearchByCisMedicament(cis);
			 ArrayList<DTO_CIS_CIP_bdpm> presentations = this.getPresentations(cis);
			 ArrayList<DTO_CIS_COMPO_bdpm> compositions = this.getCompositions(cis);
			 ArrayList<DTO_CIS_HAS_SMR_bdpm> smr = this.getAvisSMR(cis);
			 ArrayList<DTO_CIS_HAS_ASMR_bdpm> asmr = this.getAvisASMR(cis);
			 ArrayList<DTO_CIS_GENER_bdpm> generique = this.getGroupeByCisMedicament(cis);
			 ArrayList<DTO_CIS_CPD_bdpm> conditions = this.getConditionsPrescriptionDelivrance(cis);
			 ArrayList<DTO_CIS_INFOS_bdpm> infos = this.getListeInfoImportantes(cis);
			 
			 all.put("specialites", specialites);
			 all.put("presentations", presentations);
			 all.put("compositions", compositions);
			 all.put("smr", smr);
			 all.put("asmr", asmr);
			 all.put("generique", generique);
			 all.put("conditions", conditions);
			 all.put("infos", infos);
			 
			 if(smr.size()!=0) {
				 String codeDossier = smr.get(0).getAttributes().get("codeDossier");
				 DTO_HAS_LiensPageCT_bdpm liens = this.getLien(codeDossier);
				 all.put("liens", liens);
			 }
			 
			 
			 return all;
		 }
		 
		 
		 
	 
	 //Main pour tester la classe
	 public static void main(String[] args) {
		 DAOfactory dao = new DAOfactory();
		 //ArrayList<DTO_CIS_CPD_bdpm> medicaments = dao.getConditionsPrescriptionDelivrance(61438967);
		 //System.out.println(medicaments.get(0).toString());
		 //for(DTO_CIS_CPD_bdpm m : medicaments)
			//System.out.println(m.getAttributes().get("cpd"));
		 DTO_CIS_bdpm med = dao.getSearchByCisMedicament(61438967);
		 System.out.println(med.getAttributes().get("etatCom"));
		 //DTO_HAS_LiensPageCT_bdpm lien = dao.getLien("CT-18194");
		 //System.out.print(lien.getAttributes().get("lien"));
		 /*ArrayList<DTO_CIS_INFOS_bdpm> comp = dao.getListeInfoImportantes(60000318);
		 for(DTO_CIS_INFOS_bdpm e : comp) {
			 System.out.println(e.getAttributes().get("lienText"));
		 }*/
	 }


}


	