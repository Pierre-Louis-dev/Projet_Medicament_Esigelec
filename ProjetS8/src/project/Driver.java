package project;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.security.crypto.bcrypt.BCrypt;
public class Driver {

	public static void main(String[] args) {
		String mdp="bonjour";
		/*String mdp2=BCrypt.hashpw(mdp,BCrypt.gensalt(7));
		System.out.println(mdp2);
		System.out.println(BCrypt.checkpw(mdp, mdp2));
		*/
		
		String nomHote ;
        String adresseIPLocale ;

        try{
           InetAddress inetadr = InetAddress.getLocalHost();
           //nom de machine
           nomHote = (String) inetadr.getHostName();
           System.out.println("Nom de la machine = "+nomHote );
           //adresse ip sur le réseau
           adresseIPLocale = (String) inetadr.getHostAddress();
           System.out.println("Adresse IP locale = "+adresseIPLocale );
   
        } catch (UnknownHostException e) {
               e.printStackTrace();
        }
	}

}
