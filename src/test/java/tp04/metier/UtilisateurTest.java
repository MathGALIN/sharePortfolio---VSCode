/*
 * Copyright 2025 LYU JITIAN
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {

    private Utilisateur utilisateur;
    private Portefeuille portefeuille;
    private Action action;

    @BeforeEach
    void setUp() {
        // Création d'un utilisateur avec un solde initial de 1000€
        portefeuille = new Portefeuille();  
        action = new Action("AAPL") {  
            @Override
            public float valeur(Jour j) {
                return 100.0f;  
            }
        };
        utilisateur = new Utilisateur("John Doe", 1000);  
        utilisateur.setPortefeuille(portefeuille);  // Associer le portefeuille à l'utilisateur
    }

    @Test
    void testAcheterActions() {
        // L'utilisateur achète 10 actions AAPL à 50€ chacune
        String result = utilisateur.acheterActions(action, 10, 50);

        // Vérification du résultat
        assertEquals("10 actions de AAPL achetées pour 500.0€.", result);
        assertEquals(500, utilisateur.getSolde());  // Le solde de l'utilisateur doit être réduit de 500€
    }

    @Test
    void testAcheterActions_FondsInsuffisants() {
        // L'utilisateur essaie d'acheter 30 actions à 50€ chacune (le total est 1500€)
        String result = utilisateur.acheterActions(action, 30, 50);

        // Vérification du résultat
        assertEquals("Fonds insuffisants pour l'achat.", result);
        assertEquals(1000, utilisateur.getSolde());  // Le solde de l'utilisateur ne doit pas changer
    }

    @Test
    void testVendreActions() {
        // L'utilisateur achète 20 actions AAPL, puis en vend 10
        portefeuille.acheter(action, 20);  // Acheter 20 actions AAPL
        String result = utilisateur.vendreActions(action, 10, 50);  // Vendre 10 actions à 50€ chacune

        // Vérification du résultat
        assertEquals("10 actions de AAPL vendues pour 500.0€.", result);
        assertEquals(1500, utilisateur.getSolde());  // Le solde de l'utilisateur doit être augmenté de 500€
    }

    @Test
    void testVendreActions_StocksInsuffisants() {
        // L'utilisateur possède seulement 5 actions AAPL et essaie d'en vendre 10
        portefeuille.acheter(action, 5);  // Acheter 5 actions AAPL
        String result = utilisateur.vendreActions(action, 10, 50);  // Essayer de vendre 10 actions

        // Vérification du résultat
        assertEquals("Vous n'avez pas assez d'actions à vendre.", result);
        assertEquals(1000, utilisateur.getSolde());  // Le solde de l'utilisateur ne doit pas changer
    }

    @Test
    void testConsulterPortefeuille() {
        // L'utilisateur possède 20 actions AAPL
        portefeuille.acheter(action, 20);

        // Appel de la méthode pour consulter le portefeuille de l'utilisateur
        utilisateur.consulterPortefeuille();  // Cette méthode affiche directement le portefeuille et le solde
    }
}
