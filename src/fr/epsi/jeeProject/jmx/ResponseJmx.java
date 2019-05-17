package fr.epsi.jeeProject.jmx;

import java.util.Date;

public class ResponseJmx implements ResponseJmxMBean {

    private static String commentaire = "commentaireResponse";
    private static Date date = new Date();

    public ResponseJmx() {
    }


    @Override
    public String getCommentaire() {
        return commentaire;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public synchronized void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
