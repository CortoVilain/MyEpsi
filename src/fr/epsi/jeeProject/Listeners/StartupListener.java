package fr.epsi.jeeProject.Listeners;

import fr.epsi.jeeProject.jmx.LogJmx;
import fr.epsi.jeeProject.jmx.ResponseJmx;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener()
public class StartupListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
	
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	
    // Public constructor is required by servlet spec
    public StartupListener() {
    }
    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
            logger.info("Connexion à la base de données réussi !");
            con.close();
        }
        catch (SQLException e) {
            logger.error("Échec de la connexion de la base de données", e);
        }
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName nameResponse = null;
        ObjectName nameLog = null;
        try {
            nameResponse = new ObjectName("fr.epsi.jeeProject.jmx:type=ResponseJmxMBean");
            nameLog = new ObjectName("fr.epsi.jeeProject.jmx:type=LogJmxMBean");

            ResponseJmx mbeanResponse = new ResponseJmx();
            LogJmx mbeanLog = new LogJmx();

            mbs.registerMBean(mbeanResponse, nameResponse);
            mbs.registerMBean(mbeanLog, nameLog);

            mbeanResponse.getCommentaire();

            Configurator config = null;
            config.setRootLevel(Level.ERROR);
            mbeanLog.setLevelLogger(config);

        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }
    }
}
