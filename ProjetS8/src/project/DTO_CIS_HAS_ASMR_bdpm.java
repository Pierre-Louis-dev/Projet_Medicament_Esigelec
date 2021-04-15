package project;
import java.util.HashMap;

/**
 * Data Transfer Object pour le fichier des avis ASMR de la HAS
 * @author Groupe 4
 *
 */
public class DTO_CIS_HAS_ASMR_bdpm  {
	private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe  DTO_CIS_HAS_ASMR_bdpm 
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes
	 * @param cis
	 * @param codeDossier
	 * @param motif
	 * @param date
	 * @param valeurASMR
	 * @param libelleASMR
	 */
	public DTO_CIS_HAS_ASMR_bdpm(int cis, String codeDossier,String motif,String date,String valeurASMR,String libelleASMR) {
		
		attributes = new HashMap<String,String>();
		attributes.put("cis",Integer.toString(cis));
		attributes.put("codeDossier",codeDossier);
		attributes.put("motif",motif);
		attributes.put("date",date);
		attributes.put("valeurASMR",valeurASMR);
		attributes.put("libelleASMR",libelleASMR);
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
		return "Cis : "+attributes.get("cis")+ "\nCode du dossier : "+attributes.get("codeDossier")+ "\nMotif : "+attributes.get("motif")+ "\nDate avis de commission : "+attributes.get("date")+ "\nValeur de l'ASMR : "+attributes.get("valeursASMR")+ "\nLibelle de l'ASMR : "+attributes.get("libelleASMR");
	}

}
