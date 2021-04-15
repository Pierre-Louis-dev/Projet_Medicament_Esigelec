package controllers;

import project.*;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Servlet implementation class NouveauMdp
 */
@WebServlet("/NouveauMdp")
public class NouveauMdp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouveauMdp() {
        super();
        // TODO Auto-generated constructor stub
    }

	
      
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * permet de changer le mot de passe d'un utilisateur déconnecter
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Initialisations
				HttpSession session = request.getSession();
				session.setAttribute("modif", null);
				session.setAttribute("MauvaisM", null);
				session.setAttribute("MdpNoConform", null);
				PersonnelDAO dao = new PersonnelDAO();
				
				//Fichier de logs
				Enregistrement record = new Enregistrement();
				DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
				String date = dateFormat.format(new Date());
				String ip = (String)InetAddress.getLocalHost().getHostAddress();
				
			
			    String amdp = request.getParameter("apassword");
				String mdp = request.getParameter("password");
				String cmdp = request.getParameter("cpassword");
				
				
				 project.DTO_Personnel p = (project.DTO_Personnel) session.getAttribute("PersonneConnectee");
				 int Id = Integer.parseInt(p.getAttributes().get("id"));
				    String log =p.getAttributes().get("login");
				if(dao.Connexion(log, amdp)){
			
				
					if(cmdp.contentEquals(mdp)) {
					String crypt = BCrypt.hashpw(mdp, BCrypt.gensalt());
					if(dao.ChangeMdp(Id, crypt)) {
						
						session.setAttribute("modif", 1);
						record.ajouterLigne(date,p.getAttributes().get("login") , ip, "Mot de passe réinitialiser");
						response.sendRedirect("NouveauMdp.jsp");
					}else {
						session.setAttribute("MdpNoConform", 1);
						response.sendRedirect("NouveauMdp.jsp");
					}
					
					
				}
				
				}else {
					session.setAttribute("MauvaisM", 1);
					response.sendRedirect("NouveauMdp.jsp");
				}	}

}