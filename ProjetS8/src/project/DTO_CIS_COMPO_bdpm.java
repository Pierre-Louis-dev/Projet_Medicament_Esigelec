package project;
import java.util.HashMap;

/**
 * Data Transfer Object pour le fichier des compositions
 * @author Groupe 4
 *
 */
public class DTO_CIS_COMPO_bdpm {

private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes
	 * @param cis
	 * @param designation
	 * @param codeSubstance
	 * @param denomination
	 * @param dosage
	 * @param reference
	 * @param natureComposant
	 * @param numeroLiaison
	 */
	public DTO_CIS_COMPO_bdpm(int cis, String designation,int codeSubstance,String denomination,String dosage,
			String reference,String natureComposant,int numeroLiaison) {
		
		attributes = new HashMap<String,String>();
		attributes.put("cis",Integer.toString(cis));
		attributes.put("designation", designation);
		attributes.put("codeSubstance",Integer.toString(codeSubstance));
		attributes.put("denomination", denomination);
		attributes.put("dosage",dosage);
		attributes.put("reference",reference);
		attributes.put("natureComposant",natureComposant);
		attributes.put("numeroLiaison",Integer.toString(numeroLiaison));
		
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
		return "cis : "+attributes.get("cis")+ "\ndesignation : "+attributes.get("designation")+ "\ncodeSubstance : "+attributes.get("codeSubstance")+ "\ndenomination : "+attributes.get("denomination")+ "\ndosage : "+attributes.get("dosage")+ "\nreference : "+attributes.get("reference")+ "\nnatureComposant : "+attributes.get("natureComposant")+ "\nnumeroLiaison : "+attributes.get("numeroLiaison");
	}
}
