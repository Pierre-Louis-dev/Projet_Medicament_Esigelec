package project;
import java.util.HashMap;

/**
 * Data Transfer Object pour le fichier des infos importantes
 * @author Groupe 4
 *
 */
public class DTO_CIS_INFOS_bdpm  {
	private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe  DTO_CIS_INFO_bdpm 
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes
	 * @param cis
	 * @param lienTextDebut
	 * @param lienTextFin
	 * @param lienText
	 */
	public DTO_CIS_INFOS_bdpm(int cis, String lienTextDebut,String lienTextFin,String lienText) {
		
		attributes = new HashMap<String,String>();
		attributes.put("cis",Integer.toString(cis));
		attributes.put("dateDebut",lienTextDebut);
		attributes.put("dateFin",lienTextFin);
		attributes.put("lienText",lienText);
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
		return "Cis : "+attributes.get("cis")+ "\nCode du dossier : "+attributes.get("lienTextDebut")+ "\nlienTextFin : "+attributes.get("lienTextFin");
	}

}
