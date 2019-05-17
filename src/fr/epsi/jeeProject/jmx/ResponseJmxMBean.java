package fr.epsi.jeeProject.jmx;

import java.util.Date;

public interface ResponseJmxMBean {
    public String getCommentaire();
    public Date getDate();
    public void setCommentaire(String valeur);
}
