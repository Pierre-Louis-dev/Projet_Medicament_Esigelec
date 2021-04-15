package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PersonnelDAO {
	
	private final String url = "jdbc:mysql://localhost/medicaments2";
	private final String user = "root";
	private final String password = "root";
	private final String location = "com.mysql.jdbc.Driver";
	private Connection conn = null;
	
	public PersonnelDAO() {
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
	
	
	public boolean Connexion(String log,String mdp) {
		boolean accept = false;
		if(open()) {
			String query="select * from pro_sante";
			try {	
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						if(rs.getString("login_pro").contentEquals(log) && BCrypt.checkpw(mdp, rs.getString("mdp_pro"))) {
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
	
	
	public DTO_Personnel getUtilisateur(String log) {
		DTO_Personnel personne = null;
		if(open()) {
			String query="select * from pro_sante";
			try {	
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						if(rs.getString("login_pro").contentEquals(log)) {
							personne = new DTO_Personnel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7));
						}
					}				
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return personne;
	}
	
	public ArrayList<DTO_Personnel> getPersonnels(){
		ArrayList<DTO_Personnel> personnels=new ArrayList<DTO_Personnel>();
		DTO_Personnel personne = null;
		if(open()) {
			String query="select * from pro_sante";
			try {	
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						personne = new DTO_Personnel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7));
						personnels.add(personne);
					}				
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return personnels;
	}
	
	public boolean RefuseUserCredentials(String login) {
		boolean accept = false;
		if(open()) {
			String query="update pro_sante set statut_compte_pro = \"refuse\" where login_pro = ?";
			try {	
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, login);
					int rs = ps.executeUpdate();
					if(rs == 1) {
						accept = true;
					}								
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return accept;
	}
	
	public boolean deletePersonnel(String login) {
		if(open()) {
			String query = "delete from pro_sante where login_pro=?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, login);
				ps.executeUpdate();
				return true;
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return false;
	}
	
	public boolean ValidateUserCredentials(String login) {
		boolean accept = false;
		if(open()) {
			String query="update pro_sante set statut_compte_pro = \"autorise\" where login_pro = ?";
			try {	
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, login);
					int rs = ps.executeUpdate();
					if(rs == 1) {
						accept = true;
					}								
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return accept;
	}
	
	public boolean LoginValide(String login) {
		boolean accept = true;
		if(open()) {
			String query="select * from pro_sante where login_pro = ?";
			try {	
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, login);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						accept=false;
					}			
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return accept;
	}
	
	public boolean Inscription(String nom, String prenom, String login, String mdp, String metier) {
		boolean inscrit = false;
		if(open()) {			 
			String query="insert into pro_sante(nom_pro,prenom_pro,login_pro,mdp_pro,metier_pro,statut_compte_pro) values ( ? , ? , ? , ? , ? , ?)";
			try {	
				if(this.LoginValide(login)) {
					if(open()) {
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1, nom);
						ps.setString(2, prenom);
						ps.setString(3, login);
						ps.setString(4, mdp);
						ps.setString(5, metier);
						ps.setString(6, "en attente");
						ps.executeUpdate();
						inscrit=true;
					}					
				}
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return inscrit;
	}
	
	public DTO_Personnel getId(int ID) {
		DTO_Personnel personne = null;
		if(open()) {
			String query="select * from pro_sante";
			try {	
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						if(rs.getString(1).contentEquals(Integer.toString(ID))) {
							personne = new DTO_Personnel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7));
						}
					}				
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return personne;
	}
public boolean IdValide(int ID) {
		boolean accept = false;
		if(open()) {
			String query="select * from pro_sante where id_pro = ?";
			try {	
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1, ID);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						accept=true;
					}			
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return accept;
	}
	
	
	public boolean ChangeMdp(int ID,String mdp) {
		boolean accept = false;
		if(open()) {
			String query="update pro_sante set mdp_pro = ? where id_pro = ?";
			try {	
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, mdp);
					ps.setInt(2, ID);
					int rs = ps.executeUpdate();
					if(rs == 1) {
						accept = true;
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
		//PersonnelDAO dao= new PersonnelDAO();
		//System.out.println(dao.deletePersonnel("rgmailcom"));
		//System.out.println(dao.ValidateUserCredentials("login@login"));
		//System.out.println(dao.LoginValide("rgmail.com"));
		//System.out.println(dao.Inscription("test2", "test2", "t@t", "aze", "essai"));
	}

}
