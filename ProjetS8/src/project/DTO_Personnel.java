package project;
import java.util.HashMap;

/**
 * Data Transfer Object pour les personnels de santé
 * @author Groupe 4
 *
 */
public class DTO_Personnel {
	private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe FoodDTO
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param metier
	 * @param statut
	 */
	public DTO_Personnel(int id, String nom,String prenom,String login,String metier,String statut) {
		
		attributes = new HashMap<String,String>();
		attributes.put("id",Integer.toString(id));
		attributes.put("nom",nom);
		attributes.put("prenom",prenom);
		attributes.put("login",login);
		attributes.put("metier",metier);
		attributes.put("statut",statut);
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
		return "id : "+attributes.get("id")+ "\nnom : "+attributes.get("nom")+ "\nprenom : "+attributes.get("prenom")+ "\nlogin : "+attributes.get("login")+ "\nmetier : "+attributes.get("metier")+ "\nstatut : "+attributes.get("statut");
	}

}
