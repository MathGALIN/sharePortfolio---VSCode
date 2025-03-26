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
 *
 * @author perussel
 */
public class Portefeuille {

    Map<Action, LignePortefeuille> mapLignes;

    public static class LignePortefeuille {

        private Action action;

        private int qte;

        public int getQte() {
            return qte;
        }

        public void setQte(int qte) {
            this.qte = qte;
        }

        public Action getAction() {
            return this.action;
        }

        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }

        public String toString() {
            return Integer.toString(qte);
        }
    }

    public Portefeuille() {
        this.mapLignes = new HashMap();
    }

    public Map<Action, LignePortefeuille> getMapLignes() {
        return this.mapLignes;
    }
    

    public void acheter(Action a, int q) {
        if (this.mapLignes.containsKey(a) == false) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
        }
    }

    public float vendre(Action a, int q, float prixUnitaire) {
        if (this.mapLignes.containsKey(a)) {
            int qteActuelle = this.mapLignes.get(a).getQte();
            if (qteActuelle >= q) {
                this.mapLignes.get(a).setQte(qteActuelle - q);
                if (this.mapLignes.get(a).getQte() == 0) {
                    this.mapLignes.remove(a);
                }
                return q * prixUnitaire;  // ✅ 返回卖出金额
            }
        }
        return 0;  // 如果股票不存在或卖出失败，返回 0
    }
    

    public Map<Action, Integer> consulterActions() {
        Map<Action, Integer> result = new HashMap<>();
        for (Map.Entry<Action, LignePortefeuille> entry : mapLignes.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getQte());
        }
        return result;
    }

    
    public void afficherPortefeuille(Jour j) {
        if (mapLignes.isEmpty()) {
            System.out.println("Votre portefeuille est vide.");
        } else {
            System.out.println("Votre portefeuille contient :");
            for (Map.Entry<Action, LignePortefeuille> entry : mapLignes.entrySet()) {
                Action action = entry.getKey();
                int quantite = entry.getValue().getQte();
                float prix = action.valeur(j);
    
                // ✅ 统一格式，避免格式匹配错误
                System.out.printf("%s : %d actions (Valeur unitaire: %.1f€)\n", 
                    action.getLibelle(), quantite, prix);
            }
        }
    }
    
    public String toString() {
        return this.mapLignes.toString();
    }

    public float valeur(Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }
}
