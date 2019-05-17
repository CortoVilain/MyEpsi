package fr.epsi.jeeProject.jmx;

public class BlogJMX implements BlogJMXMBean{

    private static String titre = "TitreBlog";
    private static String description = "TitreBlog";

    public BlogJMX() {
    }

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public String getDescription() {
        return description;
    }




}
