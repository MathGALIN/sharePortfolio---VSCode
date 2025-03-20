package tp.metier;

public abstract class Acteur {
    protected int id;
    protected String nom;

    protected Acteur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Acteur: " + nom + " (ID: " + id + ")";
    }
}