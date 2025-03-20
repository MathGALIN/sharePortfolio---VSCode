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
     * Met à jour le prix d'une action à une date donnée et retourne un message.
     *
     * @param libelle Le nom de l'action.
     * @param jour    Le jour de mise à jour.
     * @param prix    Le nouveau prix.
     * @return Message confirmant la mise à jour du prix.
     */
    public String mettreAJourPrix(String libelle, Jour jour, float prix) {
        ActionSimple action = actions.get(libelle);
        if (action != null) {
            action.mettreAJourCours(jour, prix);
            String message = "Prix mis à jour pour " + libelle + " le " + jour.getNoJour() + "/" + jour.getAnnee() + " à " + prix + ".";
            System.out.println(message);
            return message;
        } else {
            String message = "Action introuvable !";
            System.out.println(message);
            return message;
        }
    }

    /**
     * Affiche et retourne le prix d'une action donnée.
     * @param libelle Le nom de l'action.
     * @param jour Le jour demandé.
     * @return Message affichant le prix de l'action.
     */
    public String afficherPrix(String libelle, Jour jour) {
        ActionSimple action = actions.get(libelle);
        if (action != null) {
            String message = "Prix de " + libelle + " le " + jour.getNoJour() + "/" + jour.getAnnee() + " : " + action.valeur(jour) + ".";
            System.out.println(message);
            return message;
        } else {
            String message = "Action introuvable !";
            System.out.println(message);
            return message;
        }
    }
}