package fr.epsi.jeeProject.dao.mockImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IUtilisateurDao;

public class MockUtilisateurDao implements IUtilisateurDao {

	private static List<Utilisateur> listOfUtilisateurs;
	
	@Override
	public Utilisateur getUtilisateur(String email) {
		for (Utilisateur u : getListOfUtilisateur()) {
			if (u.getEmail().equals(email))
			return u;
		}
		return null;
	}

	@Override
	public void createUtilisateur(Utilisateur utilisateur) throws SQLException {
		getListOfUtilisateur().add(utilisateur);
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
		for (Utilisateur u : getListOfUtilisateur()) {
			if (u.getEmail().equals(utilisateur.getEmail())) {
				u.setAdmin(utilisateur.getAdmin());
				u.setNom(utilisateur.getNom());
			}
		}
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) throws SQLException {
		for (Utilisateur u : getListOfUtilisateur()) {
			if (u.getEmail().equals(utilisateur.getEmail())) {
				getListOfUtilisateur().remove(u);
				return;
			}
		}
	}

	private List<Utilisateur> getListOfUtilisateur() {
		if (listOfUtilisateurs == null) {
			listOfUtilisateurs = new ArrayList<Utilisateur>();
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setEmail("contact@aquasys.fr");
			utilisateur.setNom("ADMIN");
			utilisateur.setAdmin(true);
			utilisateur.setDateCreation(new java.sql.Date(new Date().getTime()));
			listOfUtilisateurs.add(utilisateur);
			
			utilisateur = new Utilisateur();
			utilisateur.setEmail("test@aquasys.fr");
			utilisateur.setNom("TEST");
			utilisateur.setAdmin(false);
			utilisateur.setDateCreation(new java.sql.Date(new Date().getTime()));
			listOfUtilisateurs.add(utilisateur);
		}
		return listOfUtilisateurs;
	}
}
