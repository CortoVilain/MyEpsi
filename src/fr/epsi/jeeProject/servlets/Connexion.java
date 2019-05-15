package fr.epsi.jeeProject.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.UtilisateurDao;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(Connexion.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurDao userDao = new UtilisateurDao();
		
		// Connexion
		String userEmail = request.getParameter("con_email");
		String userPwd = request.getParameter("con_pwd");

		// Inscription
		String insEmail = request.getParameter("ins_email");
		String insName = request.getParameter("ins_name");
		String insPwd1 = request.getParameter("ins_pwd1");
		String insPwd2 = request.getParameter("ins_pwd2");
		
		// Connexion
		if(userEmail != null && userPwd != null) {
			Utilisateur user = userDao.getUtilisateur(userEmail);
			
			if(user != null) {
				if(user.getPassord().equals(userPwd)) {
					HttpSession session = request.getSession();
					session.setAttribute("loggedUser", user);
					logger.info("L'utilisateur " + user.getNom() + " est connecté.");
					request.getRequestDispatcher("/Accueil").forward(request, response);
					return;
				} else {
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} // Inscription
		else if(insEmail != null && insPwd1 != null && insPwd2 != null && insName != null) {
			if(insPwd1.equals(insPwd2)) {
				try {
					Utilisateur user = new Utilisateur();
					user.setEmail(insEmail);
					user.setNom(insName);
					user.setPassord(insPwd1);
					user.setAdmin(false);
					userDao.createUtilisateur(user);
					logger.info("L'utilisateur " + insName + " a été crée.");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				} catch (Exception e){
					logger.error("Erreur requête doPost inscription : ", e);
				}
			}
			else {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
	}

}