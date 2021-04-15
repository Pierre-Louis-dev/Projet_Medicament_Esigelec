package controllers;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.*;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/Chat")
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = request.getParameter("url").substring(31);
		session.setAttribute("messages", null);
		MessagesDAO mdao = new MessagesDAO();
		String provenance = null;
		if(session.getAttribute("actif")==null) {
			provenance = "Malade";
		}else {
			DTO_Personnel personne = (DTO_Personnel)session.getAttribute("PersonneConnectee");
			provenance = personne.getAttributes().get("login");
		}
		mdao.envoyerMessage(request.getParameter("chatText"), provenance);
		ArrayList<DTO_Messages> messages = mdao.getMessages();
		session.setAttribute("messages", messages);
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = request.getParameter("url").substring(31);
		session.setAttribute("messages", null);
		MessagesDAO mdao = new MessagesDAO();
		ArrayList<DTO_Messages> messages = mdao.getMessages();
		session.setAttribute("messages", messages);
		response.sendRedirect(url);
	}

}
