package fr.epsi.jeeProject.tests;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.UtilisateurDao;

@RunWith(JUnit4.class)
class TestUtilisateurDao {

	@Test
	void testGetUtilisateur() {
		Utilisateur userTest = new Utilisateur();
		UtilisateurDao userDao = new UtilisateurDao();
		userTest.setNom("Le prof");
		Utilisateur userTest2 = userDao.getUtilisateur("contact@aquasys.fr");
		
		assertEquals(userTest.getNom(), userTest2.getNom());
	}

	@Test
	void testGetListOfUtilisateur() {
		Utilisateur userTest = new Utilisateur();
		UtilisateurDao userDao = new UtilisateurDao();
		userTest.setNom("Le prof");
		List<Utilisateur> listUsers = userDao.getListOfUtilisateur();
		
		assertEquals(userTest.getNom(), listUsers.get(0).getNom());
	}
}
