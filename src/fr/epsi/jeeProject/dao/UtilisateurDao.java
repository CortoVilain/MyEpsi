package fr.epsi.jeeProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.jeeProject.Listeners.StartupListener;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IUtilisateurDao;

public class UtilisateurDao implements IUtilisateurDao {
	
	private static final Logger logger = LogManager.getLogger(UtilisateurDao.class);
	
	@Override
	public Utilisateur getUtilisateur(String email) {
		Utilisateur myUser = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM USERS WHERE email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				myUser = new Utilisateur();
				myUser.setEmail(rs.getString(1));
				myUser.setNom(rs.getString(2));
				myUser.setDateCreation(rs.getDate(3));
				myUser.setPassord(rs.getString(4));	
				myUser.setAdmin(rs.getBoolean(5));
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			myUser = null;
			logger.error("Error while getting user : "+ email, e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
		
		return myUser;
	}

	@Override
	public void createUtilisateur(Utilisateur utilisateur) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("INSERT INTO USERS(email, nom, password, date_creation, is_admin) VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, utilisateur.getEmail());
			ps.setString(2, utilisateur.getNom());
			ps.setString(3, utilisateur.getPassord());
			java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			ps.setDate(4, sqlDate);
			ps.setBoolean(5, utilisateur.getAdmin());
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error while creating user : "+ utilisateur.getEmail(), e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("UPDATE USERS SET email = ?, nom = ?, password = ?, is_admin = ? WHERE email = ?");
			ps.setString(1, utilisateur.getEmail());
			ps.setString(2, utilisateur.getNom());
			ps.setString(3, utilisateur.getPassord());
			ps.setString(4, utilisateur.getEmail());
			ps.setBoolean(5, utilisateur.getAdmin());
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error while updating user : "+ utilisateur.getEmail(), e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("DELETE FROM USERS WHERE email = ?");
			ps.setString(1, utilisateur.getEmail());
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error while deleting user : "+ utilisateur.getEmail(), e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
	}

	public List<Utilisateur> getListOfUtilisateur() {
		Connection conn = null;
		List<Utilisateur> listUser = new ArrayList<Utilisateur>();
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM USERS");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				try {
					Utilisateur myUser = new Utilisateur();
					myUser.setEmail(rs.getString(1));
					myUser.setNom(rs.getString(2));
					myUser.setDateCreation(rs.getDate(3));	
					myUser.setPassord(rs.getString(4));
					myUser.setAdmin(rs.getBoolean(5));
					listUser.add(myUser);
				} catch (SQLException e){
					logger.error("Error while getting user : "+ rs.getString(1), e);
				}
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error while getting users", e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
		
		return listUser;
	}
}
