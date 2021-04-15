package project;
import java.util.HashMap;

/**
 * Data Transfer Object pour le dossier des spécialités
 * @author Groupe 4
 *
 */
public class DTO_CIS_bdpm {
	private HashMap<String,String> attributes;
	
	/**
	 * Constructeur de la classe DTO_CIS_bdpm
	 * Ajoute a partir de leurs references les parametres de la BDD dans la liste attributes
	 * @param cis
	 * @param denom
	 * @param forme
	 * @param voies
	 * @param statAam
	 * @param typeProcedAam
	 * @param etatCom
	 * @param dateAam
	 * @param statutBdm
	 * @param numAutEur
	 * @param titulaires
	 * @param surveillance
	 * */
	public DTO_CIS_bdpm(int cis, String denom,String forme,String voies,String statAam,String typeProcedAam,
			String etatCom,String dateAam,String statutBdm,String numAutEur,String titulaires,String surveillance) {
		
		attributes = new HashMap<String,String>();
		attributes.put("cis",Integer.toString(cis));
		attributes.put("denom",denom);
		attributes.put("forme",forme);
		attributes.put("voies",voies);
		attributes.put("statAam",statAam);
		attributes.put("typeProcedAam",typeProcedAam);
		attributes.put("etatCom",etatCom);
		attributes.put("dateAam",dateAam);
		attributes.put("statutBdm",statutBdm);
		attributes.put("numAutEur",numAutEur);
		attributes.put("titulaires",titulaires);
		attributes.put("surveillance",surveillance);
		attributes.put("generique",null);
	}
	
	/**
	 * La methode permet de recuperer la liste attributes
	 * @return attributes
	 * */
	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	
	/**
	 * La methode permet d'afficher un objet de type DTO_CIS_bdpm
	 * @return le toString
	 * */
	public String toString() {
		return "cis : "+attributes.get("cis")+ "\ndenom : "+attributes.get("denom")+ "\nforme : "+attributes.get("forme")+ "\nvoies : "+attributes.get("voies")+ "\nstatAam : "+attributes.get("statAam")+ "\ntypeProcedAam : "+attributes.get("typeProcedAam")+ "\netatCom : "+attributes.get("etatCom")+ "\ndateAam : "+attributes.get("dateAam")+ "\nstatutBdm : "+attributes.get("statutBdm")+ "\nnumAutEur : "+attributes.get("numAutEur")+ "\ntitulaires : "+attributes.get("titulaires")+ "\nsurveillance : "+attributes.get("surveillance");
	}

}
