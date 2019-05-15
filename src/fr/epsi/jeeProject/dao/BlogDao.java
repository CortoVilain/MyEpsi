package fr.epsi.jeeProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.jeeProject.Listeners.StartupListener;
import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.IStatutDao;
import fr.epsi.jeeProject.dao.IUtilisateurDao;

public class BlogDao implements IBlogDao {

	private IUtilisateurDao utilisateurDao = new UtilisateurDao();
	private IStatutDao statutDao = new StatutDao();
	private static final Logger logger = LogManager.getLogger(Blog.class);

	@Override
	public Blog getBlog(Integer id) {
		Blog blog = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BLOG WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				blog = new Blog();
				blog.setId(rs.getInt(1));
				blog.setTitre(rs.getString(2));
				blog.setDescription(rs.getString(3));	
				blog.setCreateur(utilisateurDao.getUtilisateur(rs.getString(4)));
				blog.setDateCreation(rs.getDate(5));
				blog.setDateModification(rs.getDate(6));
				blog.setStatut(statutDao.getStatut(rs.getInt(7)));
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			blog = null;
			logger.error("Error while getting blog : "+ id, e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
		
		return blog;
	}

	@Override
	public List<Blog> getBlogs(Utilisateur utilisateur) {
		List<Blog> myBlogs = new ArrayList<Blog>();	
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BLOG WHERE email = ?");
			ps.setString(1, utilisateur.getEmail());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				try {
					Blog blog = new Blog();
					blog.setId(rs.getInt(1));
					blog.setTitre(rs.getString(2));
					blog.setDescription(rs.getString(3));	
					blog.setCreateur(utilisateur);
					blog.setDateCreation(rs.getDate(5));
					blog.setDateModification(rs.getDate(6));
					blog.setStatut(statutDao.getStatut(rs.getInt(7)));
					myBlogs.add(blog);
				} catch (SQLException e){
					logger.error("Error while getting blog : "+ rs.getString(2), e);
				}
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error while getting blogs", e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
		
		return myBlogs;
	}

	@Override
	public void createBlog(Blog blog) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("INSERT INTO BLOG(titre, description, email, date_creation, date_modification, statut) VALUES(?, ?, ?, ?, ?, ?)");
			ps.setString(1, blog.getTitre());
			ps.setString(2, blog.getDescription());
			ps.setString(3, blog.getCreateur().getEmail());
			ps.setDate(4, blog.getDateCreation());
			ps.setDate(5, blog.getDateModification());
			ps.setString(6, blog.getStatut().getDescription());
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error while creating blog : "+ blog.getTitre(), e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
	}

	@Override
	public void updateBlog(Blog blog) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("UPDATE BLOG SET titre = ?, description = ?, email = ?, date_creation = ?, date_modification = ?, statut = ? WHERE id = ?");
			ps.setString(1, blog.getTitre());
			ps.setString(2, blog.getDescription());
			ps.setString(3, blog.getCreateur().getEmail());
			ps.setDate(4, blog.getDateCreation());
			ps.setDate(5, blog.getDateModification());
			ps.setString(6, blog.getStatut().getDescription());
			ps.setInt(7, blog.getId());
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error while updating blog : "+ blog.getTitre(), e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
	}

	@Override
	public void deleteBlog(Blog blog) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("DELETE FROM BLOG WHERE id = ?");
			ps.setInt(1, blog.getId());
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error while deleting blog : "+ blog.getTitre(), e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
	}

	@Override
	public void addReponse(Blog blog, Reponse reponse) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("INSERT INTO BLOG_COMMENTAIRES(id, commentaire, email, date_creation) VALUES(?, ?, ?, ?)");
			ps.setInt(1, blog.getId());
			ps.setString(2, reponse.getCommentaire());
			ps.setString(3, blog.getCreateur().getEmail());
			java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			ps.setDate(4, sqlDate);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error while creating response : "+ reponse.getCommentaire(), e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
	}
	
	public List<Blog> getAllBlogs(Utilisateur user) {
		Connection conn = null;
		List<Blog> listBlog = new ArrayList<Blog>();
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BLOG WHERE email != ?");
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				try {
					Blog blog = new Blog();
					blog.setId(rs.getInt(1));
					blog.setTitre(rs.getString(2));
					blog.setDescription(rs.getString(3));	
					blog.setCreateur(utilisateurDao.getUtilisateur(rs.getString(4)));
					blog.setDateCreation(rs.getDate(5));
					blog.setDateModification(rs.getDate(6));
					blog.setStatut(statutDao.getStatut(rs.getInt(7)));
					listBlog.add(blog);
				} catch (SQLException e){
					logger.error("Error while getting blog : "+ rs.getString(2), e);
				}
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error while getting blogs", e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
		
		return listBlog;
	}

}
