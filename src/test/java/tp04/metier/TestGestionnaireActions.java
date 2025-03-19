package tp04.metier;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class TestGestionnaireActions {
    public static void main(String[] args) {
        // Création du gestionnaire
        GestionnaireActions gestionnaire = new GestionnaireActions();

        // Création d'une action simple
        ActionSimple apple = new ActionSimple("APPLE");

        // Ajout de l'action
        gestionnaire.ajouterAction(apple);

        // Création d'une date
        Jour jour1 = new Jour(2025, 75);  // 75e jour de 2025

        // Ajout et mise à jour du prix
        gestionnaire.mettreAJourPrix("APPLE", jour1, 150.0f);
        gestionnaire.mettreAJourPrix("APPLE", jour1, 155.0f); // Mise à jour

        // Vérification du prix
        gestionnaire.afficherPrix("APPLE", jour1);
    }
}

