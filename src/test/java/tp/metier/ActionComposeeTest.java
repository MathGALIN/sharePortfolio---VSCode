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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
class ActionComposeeTest {

    @Test
    void testValeurActionComposee() {
        // Création d'un jour pour le test
        Jour dateTest = new Jour(2025, 4);

        // Création de deux actions simples
        ActionSimple action1 = new ActionSimple("Tesla");
        ActionSimple action2 = new ActionSimple("Amazon");

        // Enregistrement d'un cours pour chacune des actions simples pour le jour donné
        action1.enrgCours(dateTest, 100.0f); // cours de 100 pour action1
        action2.enrgCours(dateTest, 200.0f); // cours de 200 pour action2

        // Création d'une action composée et définition de sa composition
        ActionComposee actionComposee = new ActionComposee("Action Compose 1");

        // On définit par exemple une composition : 40 % d'action1 et 60 % d'action2
        actionComposee.enrgComposition(action1, 0.4f);
        actionComposee.enrgComposition(action2, 0.6f);

        // Calcul attendu : (100 * 0.4) + (200 * 0.6) = 40 + 120 = 160
        float valeurAttendue = 160.0f;

        // Récupération de la valeur calculée pour le jour donné
        float valeurCalculee = actionComposee.valeur(dateTest);

        // Vérification que la valeur calculée correspond à la valeur attendue
        assertEquals(valeurAttendue, valeurCalculee, 0.001f,
                "La valeur de l'action composée devrait être calculée correctement.");
    }

}
