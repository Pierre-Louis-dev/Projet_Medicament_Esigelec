package project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MessagesDAO {

	private final String url = "jdbc:mysql://localhost/medicaments2";
	private final String user = "root";
	private final String password = "root";
	private final String location = "com.mysql.jdbc.Driver";
	private Connection conn = null;
	
	public MessagesDAO() {
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
	
	
	public boolean envoyerMessage(String message, String provenance) {
		boolean enregistre = false;
		if(open()) {			 
			String query="insert into messages(texte,provenance) values ( ? , ?)";
			try {
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, message);
					ps.setString(2, provenance);
					ps.executeUpdate();
					enregistre = true;
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return enregistre;
	}
	
	public ArrayList<DTO_Messages> getMessages(){
		ArrayList<DTO_Messages> messages = null;
		if(open()) {
			String query = "select * from messages";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				messages = new ArrayList<DTO_Messages>();
				while(rs.next()) {
					messages.add(new DTO_Messages(rs.getString(1),rs.getString(2)));
				}
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}finally {
				close();
			}
		}
		return messages;
	}
	
	public static void main(String[] args) {
		MessagesDAO dao = new MessagesDAO();
		//System.out.println(dao.envoyerMessage("Deuxième test", "login@login"));
		/*ArrayList<DTO_Messages> messages = dao.getMessages();
		for(DTO_Messages m : messages) {
			System.out.println(m.toString());
		}*/
		

	}

}
