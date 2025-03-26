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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActionSimpleTest {

    @Test
    void testEnrgCours_AjoutePremierCours() {
        ActionSimple action = new ActionSimple("Apple");
        Jour jour = new Jour(2024, 101);

        action.enrgCours(jour, 150.0f);  // Ajout
        float valeur = action.valeur(jour);

        assertEquals(150.0f, valeur, 0.001, "Le cours ajouté devrait être 150.0€");
    }

    @Test
    void testEnrgCours_IgnorerSiDejaExistant() {
        ActionSimple action = new ActionSimple("Apple");
        Jour jour = new Jour(2024, 101);

        action.enrgCours(jour, 150.0f);  // Ajout initial
        action.enrgCours(jour, 999.9f);  // Devrait être ignoré

        float valeur = action.valeur(jour);
        assertEquals(150.0f, valeur, 0.001, "Le cours ne doit pas être mis à jour par enrgCours()");
    }

    @Test
    void testEnrgCours_ValeurInexistanteRetourneZero() {
        ActionSimple action = new ActionSimple("Apple");
        Jour jour = new Jour(2024, 200);  // jamais enregistré

        float valeur = action.valeur(jour);
        assertEquals(0.0f, valeur, 0.001, "Si aucun cours, la valeur doit être 0");
    }
}
