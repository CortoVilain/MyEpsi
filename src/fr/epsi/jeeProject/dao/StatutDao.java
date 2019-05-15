package fr.epsi.jeeProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.jeeProject.Listeners.StartupListener;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.dao.IStatutDao;

public class StatutDao implements IStatutDao {

	private static List<Statut> listOfStatuts;
	private static final Logger logger = LogManager.getLogger(StatutDao.class);
	
	@Override
	public Statut getStatut(Integer id) {
		Statut statut = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM STATUT WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				statut = new Statut();
				statut.setId(rs.getInt(1));
				statut.setDescription(rs.getString(2));
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			statut = null;
			logger.error("Error while getting statut : "+ id, e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {}
		}
		
		return statut;
	}
	@Override
	public List<Statut> getListOfStatuts() {
		return getPrivateListOfStatuts();
	}

	private List<Statut> getPrivateListOfStatuts() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM STATUT");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				try {
					Statut statut = new Statut();
					statut.setId(rs.getInt(1));
					statut.setDescription(rs.getString(2));
					listOfStatuts.add(statut);
				} catch (SQLException e){
					logger.error("Error while getting statut : "+ rs.getString(2), e);
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
		
		return listOfStatuts;
	}
}
