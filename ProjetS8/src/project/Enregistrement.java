package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Permet l'enregistrement dans un fichier des connexions et tentatives ainsi que des inscriptions des professionels de santé
 * @author Groupe 4
 *
 */
public class Enregistrement {
	//private static final String HEADER = " Adresse IP \t Login \t Action";
	private static final String SEPARATEUR = "\n";
	private static final String DELIMITEUR =" \t ";
	private FileWriter writer;
	
	public Enregistrement() {
		writer=null;
		
	}
	
	private boolean open() {
		try {
			writer = new FileWriter("FichierLog.csv",true);
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void close() {
		if(writer != null) {
			try {
				writer.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Permet d'ajouter une ligne au fichier des logs en prenant les paramètres nécéssaires
	 * @param date
	 * @param login
	 * @param ip
	 * @param action
	 * @return true si la ligne est ajoutée et false sinon
	 */
	public boolean ajouterLigne(String date, String login, String ip, String action) {
		if(open()) {
			try {
				writer.append(SEPARATEUR);
				writer.append(date);
				writer.append(DELIMITEUR);
				writer.append(login);
				writer.append(DELIMITEUR);
				writer.append(ip);
				writer.append(DELIMITEUR);
				writer.append(action);
				return true;
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				close();
			}
		}
		return false;
	}
	
	/**
	 * Permet de récuperer toutes les lignes du fichier de logs sous forme d'ArrayList
	 * @return historique
	 * 					la liste des connexions et inscriptions
	 */
	public ArrayList<String> getHistorique(){
		ArrayList<String> historique = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("FichierLog.csv"));
			String line;
			while ((line = reader.readLine()) != null)
			{
   			  historique.add(line);
			}
			reader.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return historique;
	}
	
	public static void main(String[] args) {
		Enregistrement test = new Enregistrement();
		Date now = new Date();
		DateFormat date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		//test.ajouterLigne("Date","Login","Adresse IP", "Action");
		//test.ajouterLigne(date.format(now),"login@lg","192.183.213.183","Connexion");
		//System.out.println(date.format(now));
		//System.out.println(test.getHistorique());
	}

}
