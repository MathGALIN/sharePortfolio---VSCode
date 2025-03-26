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

import java.util.Map;

import tp.metier.Portefeuille.LignePortefeuille;

public class Investisseur extends Acteur {
    
    //Attribut
    private double solde;
    private Portefeuille portefeuille;

    //Constructeur
    public Investisseur(int id, String nom, double solde) {
        super(id, nom);
        this.solde = solde;
        this.portefeuille = new Portefeuille();
    }

    //Getter et setter
    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    //Fonction pour acheter une action
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

    //Fonction pour vendre une action
    public void vendreAction(Action action, int quantite, float prixUnitaire) {
        float montantVente = portefeuille.vendreUnitaire(action, quantite, prixUnitaire);
        if (montantVente > 0) {
            this.solde += montantVente; 
            System.out.printf("%s a vendu %d actions de %s pour %.2f€\n", 
                nom, quantite, action.getLibelle(), montantVente);
        } else {
            System.out.println("Vente échouée : vous ne possédez pas cette action ou quantité insuffisante.");
        }
    }


    //Fonction pour afficher le portefeuille pour un jour j
    public String afficherPortefeuille(Jour j) {
        StringBuilder output = new StringBuilder();
        output.append("\n=== Portefeuille de ").append(nom).append(" ===\n");
        
        Map<Action, LignePortefeuille> actions = portefeuille.getMapLignes();
        float somme_prix = 0;
        
        if (actions.isEmpty()) {
            output.append("Votre portefeuille est vide.\n");
        } else {
            for (Map.Entry<Action, LignePortefeuille> entry : actions.entrySet()) {
                Action action = entry.getKey();
                int quantite = entry.getValue().getQte();
                float prix = action.valeur(j);
                
                output.append(String.format("%s : %d actions (Valeur unitaire: %.2f)\n", 
                    action.getLibelle(), quantite, prix));
                somme_prix += prix;
            }
            output.append(String.format("Solde actuel : %.2f\n", somme_prix));
        }
        
        String result = output.toString();
        System.out.print(result);
        return result;
    }





    @Override
    //Renvoie un string
    public String toString() {
        return super.toString() + ", Solde: " + solde + "€, Portefeuille: " + portefeuille.consulterActions();
    }
}