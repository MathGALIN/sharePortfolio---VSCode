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

 package tp04.metier;

 import static org.junit.jupiter.api.Assertions.*;
 import org.junit.jupiter.api.Test;
 
 public class TestGestionnaireActions {
 
     @Test
     void testAjoutEtMiseAJourPrix() {
         // Création du gestionnaire et d'une action simple
         GestionnaireActions gestionnaire = new GestionnaireActions();
         ActionSimple apple = new ActionSimple("APPLE");
         gestionnaire.ajouterAction(apple);
         Jour jour1 = new Jour(2025, 75);
 
         // Ajout et mise à jour du prix
         gestionnaire.mettreAJourPrix("APPLE", jour1, 150.0f);
         gestionnaire.mettreAJourPrix("APPLE", jour1, 155.0f);
 
         // Vérification du prix mis à jour
         assertEquals(155.0f, apple.valeur(jour1), 0.01);
     }
 
     @Test
     void testMettreAJourPrixActionInexistante() {
         GestionnaireActions gestionnaire = new GestionnaireActions();
         Jour jour1 = new Jour(2025, 75);
 
         Exception exception = assertThrows(IllegalArgumentException.class, () -> {
             gestionnaire.mettreAJourPrix("GOOGLE", jour1, 200.0f);
         });
         assertEquals("Action introuvable: GOOGLE", exception.getMessage());
     }
 
     @Test
     void testMettreAJourPrixNegatif() {
         GestionnaireActions gestionnaire = new GestionnaireActions();
         ActionSimple apple = new ActionSimple("APPLE");
         gestionnaire.ajouterAction(apple);
         Jour jour1 = new Jour(2025, 75);
 
         Exception exception = assertThrows(IllegalArgumentException.class, () -> {
             gestionnaire.mettreAJourPrix("APPLE", jour1, -50.0f);
         });
         assertEquals("Le prix d'une action ne peut pas être négatif.", exception.getMessage());
     }
 
     @Test
    void testAfficherPrix() {
    GestionnaireActions gestionnaire = new GestionnaireActions();
    ActionSimple apple = new ActionSimple("APPLE");
    gestionnaire.ajouterAction(apple);
    Jour jour1 = new Jour(2025, 75);
    gestionnaire.mettreAJourPrix("APPLE", jour1, 160.0f);

    String result = gestionnaire.afficherPrix("APPLE", jour1);

    assertTrue(result.contains("160.0"));
    assertTrue(result.contains("APPLE"));
}


 }
 
 