package project;
import java.util.HashMap;

/**
 * Data Transfer Object pour le fichier des conditions
 * @author Groupe 4
 *
 */
public class DTO_CIS_CPD_bdpm {

private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe DTO_CIS_CPD_bdpm
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes 
	 * @param cis
	 * @param cpd
	 */
	public DTO_CIS_CPD_bdpm(int cis, String cpd) {
		attributes = new HashMap<String,String>();
		attributes.put("cis",Integer.toString(cis));
		attributes.put("cpd", cpd);
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
		return "cis : "+attributes.get("cis")+ "\ncpd : "+attributes.get("cpd");
	}
}