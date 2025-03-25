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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

public class UtilisateurTest {

    @Test
    public void testSetSolde() {
        // Créer un utilisateur avec un solde initial
        Utilisateur utilisateur = new Utilisateur("John Doe", 1000f);
        
        // Modifier le solde de l'utilisateur
        utilisateur.setSolde(1500f);
        
        // Vérifier que le solde a bien été mis à jour
        assertEquals(1500f, utilisateur.getSolde(), 0.01f);
    }

    @Test
    public void testSetPortefeuille() {
        Utilisateur utilisateur = new Utilisateur("John Doe", 1000f);
        Portefeuille nouveauPortefeuille = new Portefeuille();
        utilisateur.setPortefeuille(nouveauPortefeuille);
        Assertions.assertEquals(nouveauPortefeuille, utilisateur.getPortefeuille());
    }
    @Test
    public void testGetNom() {
    
        Utilisateur utilisateur = new Utilisateur("John Doe", 1000f);
        Assertions.assertEquals("John Doe", utilisateur.getNom());
    }

    @Test
    public void testVendreActions_Fail_InsufficientShares() {
        // Créer un utilisateur et une action
        Utilisateur utilisateur = new Utilisateur("John Doe", 1000f);
        Action action = new ActionBoursiere("AAPL", 150f);  // Action Apple, prix 150€
        Portefeuille portefeuille = utilisateur.getPortefeuille();

        // L'utilisateur possède seulement 5 actions AAPL
        portefeuille.acheter(action, 5);

        // L'utilisateur tente de vendre 10 actions AAPL
        String result = utilisateur.vendreActions(action, 10, 150f);
        
        // Vérifier que le message d'erreur est renvoyé
        assertEquals("Vous n'avez pas assez d'actions à vendre.", result);
    }

    @Test
    public void testAcheterActions_Success() {
        // Créer un utilisateur et une action
        Utilisateur utilisateur = new Utilisateur("John Doe", 1000f);
        Action action = new ActionBoursiere("AAPL", 150f);  // Action Apple, prix 150€
        Portefeuille portefeuille = utilisateur.getPortefeuille();

        // L'utilisateur possède déjà 10 actions AAPL
        portefeuille.acheter(action, 10);

        // L'utilisateur a 1000€, il achète 5 actions AAPL à 150€ chacune
        String result = utilisateur.acheterActions(action, 5, 150f);
        
        // Vérifier que l'achat a réussi et que le solde est correct
        assertEquals("5 actions de AAPL achetées pour 750.0€.", result);
        assertEquals(250f, utilisateur.getSolde(), 0.01f);  // Le solde doit être 1000 - 750
        assertEquals(15, portefeuille.getMapLignes().get(action).getQte());  // L'utilisateur possède maintenant 15 actions
    }

    @Test
    public void testAcheterActions_Fail_InsufficientFunds() {
        // Créer un utilisateur et une action
        Utilisateur utilisateur = new Utilisateur("John Doe", 1000f);
        Action action = new ActionBoursiere("AAPL", 150f);  // Action Apple, prix 150€
        Portefeuille portefeuille = utilisateur.getPortefeuille();

        // L'utilisateur a seulement 1000€, il tente d'acheter 7 actions AAPL à 150€ chacune (il faut 1050€)
        String result = utilisateur.acheterActions(action, 7, 150f);
        
        // Vérifier que le message d'erreur est renvoyé
        assertEquals("Fonds insuffisants pour l'achat.", result);
    }

    @Test
    public void testConsulterPortefeuille() {
        // Créer un utilisateur et une action
        Utilisateur utilisateur = new Utilisateur("John Doe", 1000f);
        Action action = new ActionBoursiere("AAPL", 150f);  // Action Apple, prix 150€
        Portefeuille portefeuille = utilisateur.getPortefeuille();

        // L'utilisateur possède 10 actions AAPL
        portefeuille.acheter(action, 10);

        // L'utilisateur consulte son portefeuille et son solde
        utilisateur.consulterPortefeuille();
        
        // Ce test ne nécessite pas de vérification, car c'est une opération d'affichage
        // L'objectif est de vérifier qu'aucune exception n'est levée
    }
}

// Classe ActionBoursiere définie comme non publique
class ActionBoursiere extends Action {
    private float prixActuel; 

    public ActionBoursiere(String libelle, float prixActuel) {
        super(libelle);  
        this.prixActuel = prixActuel;
    }

    @Override
    public float valeur(Jour j) {
        return prixActuel;
    }
}
