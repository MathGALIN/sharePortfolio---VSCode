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
     * Met à jour le prix d'une action à une date donnée et retourne un message.
     *
     * @param libelle Le nom de l'action.
     * @param jour    Le jour de mise à jour.
     * @param prix    Le nouveau prix.
     * @return Message confirmant la mise à jour du prix.
     */
    public String mettreAJourPrix(String libelle, Jour jour, float prix) {
        ActionSimple action = actions.get(libelle);
        if (action != null) {
            action.mettreAJourCours(jour, prix);
            String message = "Prix mis à jour pour " + libelle + " le " + jour.getNoJour() + "/" + jour.getAnnee() + " à " + prix + ".";
            System.out.println(message);
            return message;
        } else {
            String message = "Action introuvable !";
            System.out.println(message);
            return message;
        }
    }

    /**
     * Affiche et retourne le prix d'une action donnée.
     * @param libelle Le nom de l'action.
     * @param jour Le jour demandé.
     * @return Message affichant le prix de l'action.
     */
    public String afficherPrix(String libelle, Jour jour) {
        ActionSimple action = actions.get(libelle);
        if (action != null) {
            String message = "Prix de " + libelle + " le " + jour.getNoJour() + "/" + jour.getAnnee() + " : " + action.valeur(jour) + ".";
            System.out.println(message);
            return message;
        } else {
            String message = "Action introuvable !";
            System.out.println(message);
            return message;
        }
    }
}