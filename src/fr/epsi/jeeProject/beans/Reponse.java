package fr.epsi.jeeProject.beans;

import java.sql.Date;

public class Reponse {

	private Blog blog;
	private Utilisateur blogger;
	private String commentaire;
	private Date publication;
	
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public Utilisateur getBlogger() {
		return blogger;
	}
	public void setBlogger(Utilisateur blogger) {
		this.blogger = blogger;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getPublication() {
		return publication;
	}
	public void setPublication(Date publication) {
		this.publication = publication;
	}
	
	
}
