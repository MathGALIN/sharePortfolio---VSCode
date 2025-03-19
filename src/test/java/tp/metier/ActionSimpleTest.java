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
class ActionSimpleTest {

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
        
        assertEquals(true, action1.equals(action2));
    }

}