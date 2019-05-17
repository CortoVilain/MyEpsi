package fr.epsi.jeeProject.jmx;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

public class LogJmx implements LogJmxBean {
    private Configurator config;

    public LogJmx() {
        config.setRootLevel(Level.INFO);
    }

    @Override
    public void setLevelLogger(Configurator config) {
        this.config = config;
    }
}
