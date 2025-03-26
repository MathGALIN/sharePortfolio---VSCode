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

 import static org.junit.jupiter.api.Assertions.*;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 
 import java.io.ByteArrayOutputStream;
 import java.io.PrintStream;
 
 class GestionnaireTest {
     private GestionnaireActions gestionnaire;
     private ActionSimple apple;
     private Jour jour1;
 
     @BeforeEach
     void setUp() {
         gestionnaire = new GestionnaireActions();
         apple = new ActionSimple("APPLE");
         jour1 = new Jour(2025, 75); // 75e jour de 2025
         gestionnaire.ajouterAction(apple);
     }
 
     @Test
     void testMiseAJourEtAffichagePrix() {
         // Capture de la sortie console
         ByteArrayOutputStream outContent = new ByteArrayOutputStream();
         System.setOut(new PrintStream(outContent));
 
         // Mise à jour et affichage
         String result1 = gestionnaire.mettreAJourPrix("APPLE", jour1, 150.0f);
         String result2 = gestionnaire.afficherPrix("APPLE", jour1);
         String result3 = gestionnaire.mettreAJourPrix("APPLE", jour1, 155.0f);
         String result4 = gestionnaire.afficherPrix("APPLE", jour1);
 
         // Vérification des retours de fonction
         assertEquals("Prix mis à jour pour APPLE le 75/2025 à 150.0.", result1);
         assertEquals("Prix de APPLE le 75/2025 : 150.0.", result2);
         assertEquals("Prix mis à jour pour APPLE le 75/2025 à 155.0.", result3);
         assertEquals("Prix de APPLE le 75/2025 : 155.0.", result4);
     }
 
     @Test
     void testActionIntrouvable() {
         // Action that does not exist
         Jour jour2 = new Jour(2025, 80);
         
         // Capture the output for non-existent action
         ByteArrayOutputStream outContent = new ByteArrayOutputStream();
         System.setOut(new PrintStream(outContent));
 
         String result = gestionnaire.mettreAJourPrix("ORANGE", jour2, 200.0f);
         assertEquals("Action introuvable !", result);
     }
 
     @Test
     void testAffichagePrixActionIntrouvable() {
         // Test afficherPrix with non-existing action
         Jour jour2 = new Jour(2025, 80);
 
         // Capture the output for non-existent action
         ByteArrayOutputStream outContent = new ByteArrayOutputStream();
         System.setOut(new PrintStream(outContent));
 
         String result = gestionnaire.afficherPrix("ORANGE", jour2);
         assertEquals("Action introuvable !", result);
     }
 
     @Test
     void testMiseAJourPrixAvecValeurInvalide() {
         // Test updating price with negative value or zero
         Jour jour2 = new Jour(2025, 80);
         
         // Invalid price (negative)
         String result1 = gestionnaire.mettreAJourPrix("APPLE", jour2, -50.0f);
         assertEquals("Prix mis à jour pour APPLE le 80/2025 à -50.0.", result1); // Assuming no validation on negative prices
 
         // Invalid price (zero)
         String result2 = gestionnaire.mettreAJourPrix("APPLE", jour2, 0.0f);
         assertEquals("Prix mis à jour pour APPLE le 80/2025 à 0.0.", result2); // Assuming no validation on zero prices
     }
 
     @Test
     void testMiseAJourPrixAvecJourFutur() {
         // Test updating price with a future day
         Jour futurJour = new Jour(2025, 100); // A future date
         
         // Updating price for a future date
         String result = gestionnaire.mettreAJourPrix("APPLE", futurJour, 250.0f);
         assertEquals("Prix mis à jour pour APPLE le 100/2025 à 250.0.", result);
     }
     
     @Test
     void testMultipleActions() {
         // Adding another action
         ActionSimple banana = new ActionSimple("BANANA");
         gestionnaire.ajouterAction(banana);
 
         Jour jour3 = new Jour(2025, 80);
 
         // Updating and displaying prices for multiple actions
         String result1 = gestionnaire.mettreAJourPrix("APPLE", jour3, 160.0f);
         String result2 = gestionnaire.afficherPrix("APPLE", jour3);
         String result3 = gestionnaire.mettreAJourPrix("BANANA", jour3, 120.0f);
         String result4 = gestionnaire.afficherPrix("BANANA", jour3);
 
         assertEquals("Prix mis à jour pour APPLE le 80/2025 à 160.0.", result1);
         assertEquals("Prix de APPLE le 80/2025 : 160.0.", result2);
         assertEquals("Prix mis à jour pour BANANA le 80/2025 à 120.0.", result3);
         assertEquals("Prix de BANANA le 80/2025 : 120.0.", result4);
     }
 }