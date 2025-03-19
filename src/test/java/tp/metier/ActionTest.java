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

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
class ActionTest {

    @Test
    void testGetLibelle() {
        final Action action = new ActionImpl();
        Assertions.assertNotNull(action.getLibelle());
    }

    @Test
    void testValeurAvecCoursExistant() {
        ActionSimple action = new ActionSimple("ActionTest");
        Jour jour = new Jour(2025, 79); // 19 mars ≈ 79e jour de l'année (non bissextile)
        action.enrgCours(jour, 100.0f);

        float valeur = action.valeur(jour);
        assertEquals(100.0f, valeur, 0.001f);
    }

    public class ActionImpl extends Action {

        public ActionImpl() {
            super("");
        }

        public float valeur(Jour j) {
            return 0.0F;
        }
    }

}
