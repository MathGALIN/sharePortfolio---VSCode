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

import java.util.Map;

import tp04.metier.Portefeuille.LignePortefeuille;

public class Investisseur extends Acteur{
    private double solde;
    private Portefeuille portefeuille;

    public Investisseur(int id, String nom, double solde) {
        super(id, nom);
        this.solde = solde;
        this.portefeuille = new Portefeuille();
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public boolean acheterAction(Action action, int quantite, double prixUnitaire) {
        double prixTotal = quantite * prixUnitaire;
        if (solde >= prixTotal) {
            portefeuille.acheter(action, quantite);
            solde -= prixTotal;
            System.out.println(nom + " a acheté " + quantite + " actions de " + action.getLibelle() + " pour " + prixTotal + "€");
            return true;
        } else {
            System.out.println("Fonds insuffisants !");
            return false;
        }
    }

    public void vendreAction(Action action, int quantite, float prixUnitaire) {
        float montantVente = portefeuille.vendre(action, quantite, prixUnitaire);
        if (montantVente > 0) {
            this.solde += montantVente;  
            System.out.printf("%s a vendu %d actions de %s pour %.2f€\n", nom, quantite, action.getLibelle(), montantVente);
        } else {
            System.out.println("Vente échouée : vous ne possédez pas cette action ou quantité insuffisante.");
        }
    }
    
    
    public void afficherPortefeuille(Jour j) {
        System.out.println("\n=== Portefeuille de " + nom + " ===");
    
        Map<Action, LignePortefeuille> actions = portefeuille.getMapLignes();  
    
        if (actions.isEmpty()) {
            System.out.println("Votre portefeuille est vide.");
        } else {
            for (Map.Entry<Action, LignePortefeuille> entry : actions.entrySet()) {
                Action action = entry.getKey();
                int quantite = entry.getValue().getQte();
                float prix = action.valeur(j);
    
                System.out.printf("%s : %d actions (Valeur unitaire: %.2f)\n", 
                    action.getLibelle(), quantite, prix);
            }
        }
    }


    @Override
    public String toString() {
        return super.toString() + ", Solde: " + solde + "€, Portefeuille: " + portefeuille.consulterActions();
    }
}
