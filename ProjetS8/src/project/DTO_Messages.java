package project;

import java.util.HashMap;

/**
 * Data Transfer Object pour le stockage et la recherche des messages postés
 * @author Groupe 4
 *
 */
public class DTO_Messages {

	private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe DTO_Messages
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes
	 * @param text
	 * @param provenance
	 */
	public DTO_Messages (String text, String provenance) {
		attributes = new HashMap<String, String>();
		attributes.put("texte", text);
		attributes.put("provenance", provenance);
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
		return "Message : "+attributes.get("texte")+"\nDe : "+ attributes.get("provenance");
	}
}
