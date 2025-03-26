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

import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant à l'administrateur de gérer les prix des actions.
 */
public class GestionnaireActions {

    private Map<String, ActionSimple> actions;

    public GestionnaireActions() {
        this.actions = new HashMap<>();
    }

    /**
     * Ajoute une action si elle n'existe pas déjà.
     * @param action L'action à ajouter.
     */
    public void ajouterAction(ActionSimple action) {
        this.actions.putIfAbsent(action.getLibelle(), action);
    }

    /**
     * Met à jour le prix d'une action à une date donnée.
     *
     * @param libelle Le nom de l'action.
     * @param jour    Le jour de mise à jour.
     * @param prix    Le nouveau prix.
     */
    public void mettreAJourPrix(String libelle, Jour jour, float prix) {
        if (prix < 0) {
            throw new IllegalArgumentException("Le prix d'une action ne peut pas être négatif.");
        }
    
        ActionSimple action = actions.get(libelle);
        if (action == null) {
            throw new IllegalArgumentException("Action introuvable: " + libelle);
        }
    
        action.mettreAJourCours(jour, prix);
        System.out.println("Prix mis à jour pour " + libelle + " le " + jour.getNoJour() + "/" + jour.getAnnee() + " à " + prix + "€.");
    }
    

    /**
     * Affiche les prix d'une action donnée.
     * @param libelle Le nom de l'action.
     * @param jour Le jour demandé.
     */
    public String afficherPrix(String libelle, Jour jour) {
        ActionSimple action = actions.get(libelle);
        if (action != null) {
            return "Prix de " + libelle + " le " + jour.getNoJour() + "/" + jour.getAnnee()
                   + " : " + action.valeur(jour) + "€.";
        } else {
            return "Action introuvable !";
        }
    }
}
    


