package project;
import java.util.HashMap;

/**
 * Data Transfer Object pour le fichier des avis SMR de la HAS
 * @author Groupe 4
 *
 */
public class DTO_CIS_HAS_SMR_bdpm  {
	private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe  DTO_CIS_HAS_SMR_bdpm 
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes
	 * @param cis
	 * @param codeDossier
	 * @param motif
	 * @param date
	 * @param valeurSMR
	 * @param libelleSMR
	 */
	public DTO_CIS_HAS_SMR_bdpm(int cis, String codeDossier,String motif,String date,String valeurSMR,String libelleSMR) {
		
		attributes = new HashMap<String,String>();
		attributes.put("cis",Integer.toString(cis));
		attributes.put("codeDossier",codeDossier);
		attributes.put("motif",motif);
		attributes.put("date",date);
		attributes.put("valeurSMR",valeurSMR);
		attributes.put("libelleSMR",libelleSMR);
	}
		
		
	
	/**
	 * La methode permet de recuperer la liste attributes
	 * @return attributes
	 * */
	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	
	/**
	 * La methode permet d'afficher un objet 
	 * @return le toString
	 * */
	public String toString() {
		return "Cis : "+attributes.get("cis")+ "\nCode du dossier : "+attributes.get("codeDossier")+ "\nMotif : "+attributes.get("motif")+ "\nDate avis de commission : "+attributes.get("date")+ "\nValeur du SMR : "+attributes.get("valeursSMR")+ "\nLibelle du SMR : "+attributes.get("libelleSMR");
	}

}
