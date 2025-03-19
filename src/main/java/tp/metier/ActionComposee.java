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
public class ActionComposee extends Action {
    // attribut lien
    Map<ActionSimple, Float> mapPanier;

    //Constructeur pour créer une action composé
    public ActionComposee(String libelle) {
        super(libelle);
        this.mapPanier = new HashMap<>();
    }

    //Fonction pour enregistrer une action dans l'action composé
    public void enrgComposition(ActionSimple as, float pourcentage) {
        this.mapPanier.put(as, pourcentage);
    }

    @Override
    //Permet d'obtenir la valeur de l'action composé pour un jour j
    public float valeur(Jour j) {
        float valeur;

        valeur = 0;

        for (Map.Entry<ActionSimple, Float> entry : this.mapPanier.entrySet()) {
            ActionSimple as = entry.getKey();
            Float mapValue = entry.getValue();
            valeur = valeur + (as.valeur(j) * mapValue);
        }         

        return valeur;
    }

    @Override
    //Permet de savoir si deux actions composés sont les mêmes
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActionComposee)) {
            return false;
        }
        ActionComposee other = (ActionComposee) obj;
        return super.equals(other) && 
               this.mapPanier.equals(other.mapPanier);
    }
    
    @Override
    //Permet d'obtenir le hash d'une action composé
    public int hashCode() {
        return super.hashCode() * 31 + mapPanier.hashCode();
    }
    

}
