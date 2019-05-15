package fr.epsi.jeeProject.servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.BlogDao;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(Accueil.class);   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String titreArt = request.getParameter("titre");
		
		if(titreArt == null) {
			request.getRequestDispatcher("/accueil.jsp").forward(request, response);
		} else {
			String labelleArt = request.getParameter("labelle");
			BlogDao blogDao = new BlogDao();
			Blog newBlog = new Blog();
			Statut statut = new Statut();
			statut.setId(1);
			HttpSession session = request.getSession();
			Utilisateur user = (Utilisateur) session.getAttribute("loggedUser");
			newBlog.setCreateur(user);
			newBlog.setStatut(statut);
			newBlog.setTitre(titreArt);
			newBlog.setDescription(labelleArt);
			java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			newBlog.setDateCreation(sqlDate);
			newBlog.setDateModification(sqlDate);
			try {
				blogDao.createBlog(newBlog);
				logger.info("Création du post : " + newBlog.getTitre());
				request.getRequestDispatcher("/accueil.jsp").forward(request, response);
			}
			catch (Exception e){
				logger.error("Echec de la création du post : " + newBlog.getTitre(), e);
				request.getRequestDispatcher("/accueil.jsp").forward(request, response);
			}
		}
	}

}
