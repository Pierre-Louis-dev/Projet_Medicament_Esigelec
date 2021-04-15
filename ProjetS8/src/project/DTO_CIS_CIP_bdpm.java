package project;
import java.util.HashMap;

/**
 * Data Transfer Object pour le fichier des présentations
 * @author Groupe 4
 *
 */
public class DTO_CIS_CIP_bdpm {
	
private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes
	 * @param cis
	 * @param cip7
	 * @param libelle
	 * @param statut
	 * @param etatCom
	 * @param dateDeclaCom
	 * @param cip13
	 * @param agrement
	 * @param taux
	 * @param prix
	 * @param indications
 */
	public DTO_CIS_CIP_bdpm(int cis, int cip7, String libelle,String statut,String etatCom,String dateDeclaCom,String cip13,
			String agrement,String taux,String prix,String indications) {
		
		attributes = new HashMap<String,String>();
		attributes.put("cis",Integer.toString(cis));
		attributes.put("cip7",Integer.toString(cip7));
		attributes.put("libelle",libelle);
		attributes.put("statut",statut);
		attributes.put("etatCom",etatCom);
		attributes.put("dateDeclaCom",dateDeclaCom);
		attributes.put("cip13",cip13);
		attributes.put("agrement",agrement);
		attributes.put("taux",taux);
		attributes.put("prix",prix);
		attributes.put("indications", indications);

	}
	
	/**
	 * La methode permet de recuperer la liste attributes
	 * @return attributes
	 * */
	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	
	/**
	 * La methode permet d'afficher
	 * @return le toString
	 * */
	public String toString() {
		return "cis : "+attributes.get("cis")+ "\ncip7 : "+attributes.get("cip7")+ "\nlibelle : "+attributes.get("libelle")+ "\nstatut : "+attributes.get("statut")+ "\netatCom : "+attributes.get("etatCom")+ "\ndateDeclaCom : "+attributes.get("dateDeclaCom")+ "\ncip13 : "+attributes.get("cip13")+ "\nagrement : "+attributes.get("agrement")+ "\ntaux : "+attributes.get("taux")+ "\nprix : "+attributes.get("prix")+ "\nindications : "+attributes.get("indications");
	}

}
