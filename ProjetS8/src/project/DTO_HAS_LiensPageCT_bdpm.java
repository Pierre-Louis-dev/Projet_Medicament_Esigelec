package project;
import java.util.HashMap;

/**
 * Data Transfer Object pour le fichier des liens
 * @author Groupe 4
 *
 */
public class DTO_HAS_LiensPageCT_bdpm {

private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe DTO_HAS_LiensPageCT_bdpm
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes
	 * @param codeDossier
	 * @param lien
	 */
	public DTO_HAS_LiensPageCT_bdpm(String codeDossier, String lien) {
		attributes = new HashMap<String,String>();
		attributes.put("codeDossier",codeDossier);
		attributes.put("lien", lien);
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
		return "codeDossier : "+attributes.get("codeDossier")+ "\nlien : "+attributes.get("lien");
	}
}