package tp.metier;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant à l'administrateur de gérer les prix des actions.
 */
public class GestionnaireActions {

    private Map<String, ActionSimple> actions;

    public GestionnaireActions() {
        this.actions = new HashMap<>();
    }

    /**
     * Ajoute une action si elle n'existe pas déjà.
     * @param action L'action à ajouter.
     */
    public void ajouterAction(ActionSimple action) {
        this.actions.putIfAbsent(action.getLibelle(), action);
    }

    /**
     * Met à jour le prix d'une action à une date donnée.
     *
     * @param libelle Le nom de l'action.
     * @param jour    Le jour de mise à jour.
     * @param prix    Le nouveau prix.
     */
    public void mettreAJourPrix(String libelle, Jour jour, float prix) {
        ActionSimple action = actions.get(libelle);
        if (action != null) {
            action.mettreAJourCours(jour, prix);
            System.out.println("Prix mis à jour pour " + libelle + " le " + jour.getNoJour() + "/" + jour.getAnnee() + " à " + prix + ".");
        } else {
            System.out.println("Action introuvable !");
        }
    }

    /**
     * Affiche les prix d'une action donnée.
     * @param libelle Le nom de l'action.
     * @param jour Le jour demandé.
     */
    public void afficherPrix(String libelle, Jour jour) {
        ActionSimple action = actions.get(libelle);
        if (action != null) {
            System.out.println("Prix de " + libelle + " le " + jour.getNoJour() + "/" + jour.getAnnee() + " : " + action.valeur(jour) + ".");
        } else {
            System.out.println("Action introuvable !");
        }
    }
}