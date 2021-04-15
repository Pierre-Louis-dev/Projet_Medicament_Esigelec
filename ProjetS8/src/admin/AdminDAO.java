package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author Groupe 4
 * Classe AdminDAO
 * Permet de faire la liaison entre la BDD et le client lourd
 *
 */
public class AdminDAO {
	private final String url = "jdbc:mysql://localhost/medicaments2";
	private final String user = "root";
	private final String password = "root";
	private final String location = "com.mysql.jdbc.Driver";
	private Connection conn = null;
	
	public AdminDAO() {
		try {
			Class.forName(location);
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	public boolean open() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
		return true;

	}
	
	public void close() {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	
	/**
	 * Permet à un administrateur de pouvoir se connecter au client lourd avec son login et son mot de passe
	 * @param log	
	 * 				le login de l'utilisateur
	 * @param mdp
	 * 				sont mot de passe
	 * @return accept
	 * 				un boolean qui est true s'il se connecte et false sinon.
	 */
	public boolean Connexion(String log,String mdp) {
		boolean accept = false;
		if(open()) {
			String query="select * from admin";
			try {	
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						if(rs.getString("mdp_admin").contentEquals(mdp) && rs.getString("login_admin").contentEquals(log)) {
							accept=true;
						}
					}				
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return accept;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdminDAO dao = new AdminDAO();
		System.out.println(dao.Connexion("admin", "admin"));

	}

}
