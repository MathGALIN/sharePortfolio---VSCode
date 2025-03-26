/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 package tp.metier;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertFalse;
 import static org.junit.jupiter.api.Assertions.assertTrue;
 import org.junit.jupiter.api.Test;
 
 class PortefeuilleTest {
 
     @Test
     void testValeurTotalePortefeuilleEtCouvertureAcheterVendre() {
         // Création des objets nécessaires
         Portefeuille portefeuille = new Portefeuille();
         ActionSimple action1 = new ActionSimple("Action1");
         ActionSimple action2 = new ActionSimple("Action2");
         Jour jour = new Jour(2025, 73);
 
         // Ajout de cours pour le jour donné
         action1.enrgCours(jour, 10.0f);  // Action1 vaut 10,00 €
         action2.enrgCours(jour, 20.0f);  // Action2 vaut 20,00 €
 
         // Ajout des actions
         portefeuille.acheter(action1, 5);  // Ajoute 5 unités de Action1
         portefeuille.acheter(action1, 3);  // Ajoute 3 unités à Action1 (5 + 3 = 8)
         portefeuille.acheter(action2, 3);  // Ajoute 3 unités de Action2
 
         // Vérification de la valeur totale avant ventes
         float valeurTotale = portefeuille.valeur(jour);
         assertEquals(140.0f, valeurTotale, 0.01f); // (8 * 10,00) + (3 * 20,00) = 80 + 60 = 140
 
         // Test de vente unitaire
         float montantVente = portefeuille.vendreUnitaire(action1, 2, 10.0f);
         assertEquals(20.0f, montantVente, 0.01f); // 2 * 10 = 20
 
         // Test de vente avec quantité insuffisante
         montantVente = portefeuille.vendreUnitaire(action1, 10, 10.0f); 
         assertEquals(0.0f, montantVente, 0.01f); // La vente échoue car la quantité disponible est 6
 
         // Vérification de la valeur totale après ventes
         valeurTotale = portefeuille.valeur(jour);
         assertEquals(120.0f, valeurTotale, 0.01f); // (6 * 10,00) + (3 * 20,00) = 60 + 60 = 120
 
         // Test de suppression d'action quand quantité == 0
         portefeuille.vendre(action2, 3);  // Vend 3 unités, Action2 devrait être supprimée
         assertFalse(portefeuille.getMapLignes().containsKey(action2)); // Action2 supprimée
 
         // Test de la méthode toString()
         String portefeuilleStr = portefeuille.toString();
         assertTrue(portefeuilleStr.contains("Action1"));
 
         // Test de la méthode afficherPortefeuille()
         String texte_afficher = portefeuille.afficherPortefeuille(jour);
        assertTrue(texte_afficher.contains("Action1 : 6 actions (Valeur unitaire: 10"));
        }
 
     @Test
     void testToString() {
         Portefeuille portefeuille = new Portefeuille();
         ActionSimple action1 = new ActionSimple("Action1");
         ActionSimple action2 = new ActionSimple("Action2");
         Jour jour = new Jour(2025, 73);
 
         action1.enrgCours(jour, 10.0f);
         action2.enrgCours(jour, 20.0f);
 
         portefeuille.acheter(action1, 5);
         portefeuille.acheter(action2, 3);
 
         // Test that the toString method correctly represents the portfolio
         String portefeuilleStr = portefeuille.toString();
         assertTrue(portefeuilleStr.contains("Action1"));
         assertTrue(portefeuilleStr.contains("Action2"));
         assertTrue(portefeuilleStr.contains("5"));
         assertTrue(portefeuilleStr.contains("3"));
     }
 
     @Test
     void testVendreUnitaire() {
         Portefeuille portefeuille = new Portefeuille();
         ActionSimple action1 = new ActionSimple("Action1");
         Jour jour = new Jour(2025, 73);
 
         action1.enrgCours(jour, 10.0f);
 
         portefeuille.acheter(action1, 5);
 
         // Sell 3 units, which should return 30 (3 * 10.0f)
         float montantVente = portefeuille.vendreUnitaire(action1, 3, 10.0f);
         assertEquals(30.0f, montantVente, 0.01f);
 
         // Verify the remaining quantity of Action1 is 2
         assertEquals(2, portefeuille.getMapLignes().get(action1).getQte());
 
         // Test selling more than available, should return 0
         montantVente = portefeuille.vendreUnitaire(action1, 10, 10.0f);
         assertEquals(0.0f, montantVente, 0.01f); // No units left to sell
     }
 
     @Test
     void testAfficherPortefeuille() {
         Portefeuille portefeuille = new Portefeuille();
         ActionSimple action1 = new ActionSimple("Action1");
         Jour jour = new Jour(2025, 73);
 
         action1.enrgCours(jour, 10.0f);
 
         portefeuille.acheter(action1, 5);
 
         // Capture the output using a print stream to verify afficherPortefeuille()
         // This will print information about the portfolio
         String texte_afficher = portefeuille.afficherPortefeuille(jour);
        assertTrue(texte_afficher.contains("Action1 : 5 actions (Valeur unitaire: 10"));
    }
 } 