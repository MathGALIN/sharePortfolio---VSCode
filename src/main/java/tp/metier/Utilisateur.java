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

public class Utilisateur {
    private String nom;            // Le nom de l'utilisateur
    private Portefeuille portefeuille;  // Le portefeuille de l'utilisateur
    private float solde;           // Le solde du compte de l'utilisateur

    // Constructeur, initialise le nom et le solde de l'utilisateur
    public Utilisateur(String nom, float solde) {
        this.nom = nom;
        this.solde = solde;
        this.portefeuille = new Portefeuille();  // Crée un objet Portefeuille pour l'utilisateur
    }

    // Récupère le nom de l'utilisateur
    public String getNom() {
        return nom;
    }

    // Récupère le solde de l'utilisateur
    public float getSolde() {
        return solde;
    }

    // Récupère le portefeuille de l'utilisateur
    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    // Définit le solde de l'utilisateur
    public void setSolde(float solde) {
        this.solde = solde;
    }

    // Définit le portefeuille de l'utilisateur
    public void setPortefeuille(Portefeuille portefeuille) {
        this.portefeuille = portefeuille;
    }

    // Méthode pour vendre une quantité spécifiée d'actions (US22)
    public String vendreActions(Action action, int quantite, float prix) {
        // Vérifie si l'utilisateur possède suffisamment d'actions
        if (portefeuille.getMapLignes().containsKey(action) && 
            portefeuille.getMapLignes().get(action).getQte() >= quantite) {
            // Appelle la méthode vendre du portefeuille pour effectuer la vente d'actions
            portefeuille.vendre(action, quantite);

            // Calcule le montant de la vente et met à jour le solde
            float montantVente = quantite * prix;
            solde += montantVente;

            return quantite + " actions de " + action.getLibelle() + " vendues pour " + montantVente + "€.";
        } else {
            // Si l'utilisateur n'a pas assez d'actions, retourne un message d'erreur
            return "Vous n'avez pas assez d'actions à vendre.";
        }
    }

    // Méthode pour acheter une quantité spécifiée d'actions (US23)
    public String acheterActions(Action action, int quantite, float prix) {
        // Calcule le montant total nécessaire pour l'achat
        float montantAchat = quantite * prix;

        // Vérifie si l'utilisateur a suffisamment de fonds pour effectuer l'achat
        if (solde >= montantAchat) {
            // Appelle la méthode acheter du portefeuille pour effectuer l'achat des actions
            portefeuille.acheter(action, quantite);

            // Déduit le montant de l'achat du solde
            solde -= montantAchat;

            return quantite + " actions de " + action.getLibelle() + " achetées pour " + montantAchat + "€.";
        } else {
            // Si l'utilisateur n'a pas suffisamment de fonds, retourne un message d'erreur
            return "Fonds insuffisants pour l'achat.";
        }
    }

    // Méthode pour consulter le portefeuille et le solde de l'utilisateur
    public void consulterPortefeuille() {
        // Affiche le portefeuille et le solde de l'utilisateur
        System.out.println("Portefeuille de " + nom + ": " + portefeuille.toString());
        System.out.println("Solde disponible: " + solde + "€");
    }
}
