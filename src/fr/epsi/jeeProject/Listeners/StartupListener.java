package fr.epsi.jeeProject.Listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class StartupListener
 *
 */
@WebListener
public class StartupListener implements ServletContextListener {

	
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
    /**
     * Default constructor. 
     */
    public StartupListener() {
        // TODO Auto-generated constructor stub
    }
    
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
         logger.debug("Démarrage");
         
         try {
        	 Class.forName("org.hsqldb.jdbcDriver");
        	 Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
        	 logger.info("Connexion ok sur localhost");
        	 con.close();
         }
         catch (ClassNotFoundException e) {
        	 logger.error("Driver not available");
         }
         catch (SQLException e) {
        	 logger.error("Error while connecting to db", e);
         }
    }
	
}
