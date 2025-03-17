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
 *
 * @author perussel
 */
public class ActionSimple extends Action {

    // attribut lien
    private Map<Jour, Cours> mapCours;

    // constructeur
    public ActionSimple(String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap<>();
    }

    // enrg possible si pas de cours pour ce jour
    public void enrgCours(Jour j, float v) {

        this.mapCours.computeIfAbsent(j, key -> new Cours(key, v));
    
    }

    @Override
    public float valeur(Jour j) {
        if (this.mapCours.containsKey(j))
            return this.mapCours.get(j).getValeur();
        else
            return 0; // definition d'une constante possible
    }

    // encapsulation de la définition de la classe Cours
    private class Cours {

        private Jour jour;

        private float valeur;

        public float getValeur() {
            return valeur;
        }

        public Jour getJour() {
            return jour;
        }

        public Cours(Jour jour, float valeur) {
            this.jour = jour;
            this.valeur = valeur;
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((mapCours == null) ? 0 : mapCours.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ActionSimple other = (ActionSimple) obj;
        if (mapCours == null) {
            if (other.mapCours != null)
                return false;
        } else if (!mapCours.equals(other.mapCours))
            return false;
        return true;
    }


    
}
