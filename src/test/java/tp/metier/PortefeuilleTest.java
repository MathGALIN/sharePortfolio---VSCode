package tp.metier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class PortefeuilleTest {
    
@Test
    public void testValeurTotalePortefeuilleEtCouvertureAcheterVendre() {
        // Création des objets nécessaires
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action1 = new ActionSimple("Action1");
        ActionSimple action2 = new ActionSimple("Action2");
        Jour jour = new Jour(2025, 73);

        // Ajout de cours pour le jour donné
        action1.enrgCours(jour, 10.0f);  // Action1 vaut 10,00 €
        action2.enrgCours(jour, 20.0f);  // Action2 vaut 20,00 €

        // Couverture de acheter : Branche 1 (nouvelle action)
        portefeuille.acheter(action1, 5);  // Ajoute 5 unités de Action1
        assertEquals(5, portefeuille.getMapLignes().get(action1).getQte());

        // Couverture de acheter : Branche 2 (mise à jour quantité)
        portefeuille.acheter(action1, 3);  // Ajoute 3 unités à Action1 (5 + 3 = 8)
        assertEquals(8, portefeuille.getMapLignes().get(action1).getQte());

        // Ajout d'une deuxième action
        portefeuille.acheter(action2, 3);  // Ajoute 3 unités de Action2

        // Vérification de la valeur totale avant ventes
        float valeurTotale = portefeuille.valeur(jour);
        assertEquals(140.0f, valeurTotale, 0.01f); // (8 * 10,00) + (3 * 20,00) = 80 + 60 = 140

        // Couverture de vendre : Branche 2.1 (quantité > q)
        portefeuille.vendre(action1, 2);  // Vend 2 unités (8 - 2 = 6)
        assertEquals(6, portefeuille.getMapLignes().get(action1).getQte());

        // Couverture de vendre : Branche 2.2 (quantité == q)
        portefeuille.vendre(action2, 3);  // Vend 3 unités (3 - 3 = 0, suppression)
        assertFalse(portefeuille.getMapLignes().containsKey(action2));

        // Couverture de vendre : Branche implicite 2.3 (quantité < q)
        portefeuille.vendre(action1, 10);  // Essaie de vendre 10 alors qu'il reste 6 (rien ne change)
        assertEquals(6, portefeuille.getMapLignes().get(action1).getQte());

        // Couverture de vendre : Branche 1 (action non présente)
        portefeuille.vendre(action2, 1);  // Action2 déjà supprimée, ne fait rien
        assertFalse(portefeuille.getMapLignes().containsKey(action2));

        // Vérification finale de la valeur totale
        valeurTotale = portefeuille.valeur(jour);
        assertEquals(60.0f, valeurTotale, 0.01f); // (6 * 10,00) = 60,00
    }
}
