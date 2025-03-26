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
 * La classe \`ActionComposee\` représente une action composée de plusieurs actions simples.
 * Elle permet de calculer la valeur de l'action composée en fonction des valeurs des actions simples qui la composent.
 * Chaque action simple est associée à un pourcentage représentant sa part dans l'action composée.
 *
 * \@author perussel
 */
public class ActionComposee extends Action {

  /**
   * Map associant chaque \`ActionSimple\` à son pourcentage dans l'\`ActionComposee\`.
   */
  Map<ActionSimple, Float> mapPanier;

  /**
   * Constructeur de la classe \`ActionComposee\`.
   *
   * \@param libelle Le libellé de l'action composée.
   */
  public ActionComposee(String libelle) {
      super(libelle);
      this.mapPanier = new HashMap<>();
  }

    /**
     * Enregistre une action simple dans l'action composée avec un pourcentage donné.
     *
     * \@param as L'action simple à ajouter.
     * \@param pourcentage Le pourcentage de l'action simple dans l'action composée.
     */
    public void enrgComposition(ActionSimple as, float pourcentage) {
        this.mapPanier.put(as, pourcentage);
    }


    /**
     * Calcule la valeur de l'action composée pour un jour donné.
     *
     * \@param j Le jour pour lequel calculer la valeur.
     * \@return La valeur de l'action composée pour le jour donné.
     */
    @Override
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


    /**
     * Vérifie si cette action composée est égale à un autre objet.
     *
     * \@param obj L'objet à comparer.
     * \@return \`true\` si les objets sont égaux, \`false\` sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActionComposee)) {
            return false;
        }
        ActionComposee other = (ActionComposee) obj;
        return super.equals(other) && this.mapPanier.equals(other.mapPanier);
    }


    /**
     * Calcule le hash code de l'action composée.
     *
     * \@return Le hash code de l'action composée.
     */
    @Override
    public int hashCode() {
        return super.hashCode() * 31 + mapPanier.hashCode();
    }

}
