package tp04.metier;

import java.util.Map;

public class Investisseur extends Acteur{
    private double solde;
    private Portefeuille portefeuille;

    public Investisseur(int id, String nom, double solde) {
        super(id, nom);
        this.solde = solde;
        this.portefeuille = new Portefeuille();
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public boolean acheterAction(Action action, int quantite, double prixUnitaire) {
        double prixTotal = quantite * prixUnitaire;
        if (solde >= prixTotal) {
            portefeuille.acheter(action, quantite);
            solde -= prixTotal;
            System.out.println(nom + " a acheté " + quantite + " actions de " + action.getLibelle() + " pour " + prixTotal + "€");
            return true;
        } else {
            System.out.println("Fonds insuffisants !");
            return false;
        }
    }

    public void vendreAction(Action action, int quantite, float prixUnitaire) {
        if (portefeuille.consulterActions().containsKey(action)) {
            int quantiteActuelle = portefeuille.consulterActions().get(action);
            if (quantiteActuelle >= quantite) {
                portefeuille.vendre(action, quantite);
                solde += quantite * prixUnitaire;  // ✅ 资金增加
                System.out.printf("%s a vendu %d actions de %s pour %.1f€\n",
                    nom, quantite, action.getLibelle(), (quantite * prixUnitaire));
            } else {
                System.out.println("Quantité insuffisante pour vendre !");
            }
        } else {
            System.out.println("Aucune action à vendre !");
        }
    }
    


    public void afficherPortefeuille(Jour jour) {
        System.out.println("\n=== Portefeuille de " + nom + " ===");
    
        if (portefeuille.consulterActions().isEmpty()) {
            System.out.println("Votre portefeuille est vide.");
        } else {
            for (Map.Entry<Action, Portefeuille.LignePortefeuille> entry : portefeuille.getMapLignes().entrySet()) {
                Action action = entry.getKey();
                int quantite = entry.getValue().getQte();
                float prix = action.valeur(jour);
    
                System.out.printf("%s : %d actions (Valeur unitaire: %.1f€)\n", action.getLibelle(), quantite, prix);
            }
        }
        System.out.printf("Solde actuel : %.1f€\n", solde);
    }    
    


    @Override
    public String toString() {
        return super.toString() + ", Solde: " + solde + "€, Portefeuille: " + portefeuille.consulterActions();
    }
}
