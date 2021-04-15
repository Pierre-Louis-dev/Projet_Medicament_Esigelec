package project;
import java.util.HashMap;

/**
 * Data Transfer Object pour le fichier des groupes génériques
 * @author Groupe 4
 *
 */
public class DTO_CIS_GENER_bdpm {

private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe DTO_CIS_GENER_bdpm
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes
	 * @param idGroup
	 * @param libelleGroup
	 * @param cis
	 * @param typeGener
	 * @param numeroTri
	 */
	public DTO_CIS_GENER_bdpm(int idGroup, String libelleGroup,int cis,int typeGener,int numeroTri) {
		attributes = new HashMap<String,String>();
		attributes.put("idGroup",Integer.toString(idGroup));
		attributes.put("libelleGroup", libelleGroup);
		attributes.put("cis",Integer.toString(cis));
		attributes.put("typeGener", Integer.toString(typeGener));
		attributes.put("numeroTri",Integer.toString(numeroTri));
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
		return "idGroup : "+attributes.get("idGroup")+ "\nlibelleGroup : "+attributes.get("libelleGroup")+ "\ncis : "+attributes.get("cis")+ "\typeGener : "+attributes.get("typeGener")+ "\nnumeroTri : "+attributes.get("numeroTri");
	}
}