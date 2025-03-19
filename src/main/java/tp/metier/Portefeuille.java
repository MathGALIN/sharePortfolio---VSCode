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
public class Portefeuille {

    //Attribut des lignes d'action du portefeuille de l'utilisateur
    Map<Action, LignePortefeuille> mapLignes;

    //Classe interne correspondant au ligne du portefeuille
    public class LignePortefeuille {

        //Attribut, action, quantite
        private Action action;
        private int qte;

        //Retourne la quantite
        public int getQte() {
            return qte;
        }

        //Définit la quantite
        public void setQte(int qte) {
            this.qte = qte;
        }

        //Renvoie une action
        public Action getAction() {
            return this.action;
        }

        //Crée la ligne du portefeuille
        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }

        //Renvoie la ligne au format string
        public String toString() {
            return Integer.toString(qte);
        }
    }

    //Constructeur du portefeuille
    public Portefeuille() {
        this.mapLignes = new HashMap<>();
    }

    //Permet d'acheter une quantite action
    public void acheter(Action a, int q) {
        if (!this.mapLignes.containsKey(a)) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
        }
    }

    //Permet de vendre une quantite d'action
    public void vendre(Action a, int q) {
        if (this.mapLignes.containsKey(a)) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
            } else if (this.mapLignes.get(a).getQte() == q) {
                this.mapLignes.remove(a);
            }
        }
    }

    //Renvoie le portefeuille au format string
    public String toString() {
        return this.mapLignes.toString();
    }

    //Renvoie la valeur du jour j
    public float valeur(Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }

    //Récupéraion des informations des lignes
    public Map<Action, LignePortefeuille> getMapLignes() {
        return mapLignes;
    }

}
