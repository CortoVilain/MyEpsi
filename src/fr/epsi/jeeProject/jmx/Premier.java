package fr.epsi.jeeProject.jmx;

public class Premier implements PremierMBean {

    private static String nom = "PremierMBean";
    private int valeur = 100;

    public String getNom() {
        return nom;
    }

    public int getValeur() {
        return valeur;
    }

    public synchronized void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void rafraichir() {
        System.out.println("Rafraichir les donnees");
    }

}
