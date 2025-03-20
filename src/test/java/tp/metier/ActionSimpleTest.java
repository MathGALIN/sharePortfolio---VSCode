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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
class ActionSimpleTest {

    @Test
    void testActionSimple() {
        // Création d'un jour pour le test
        Jour dateTest = new Jour(2025, 4);

        // Création de deux actions simples
        ActionSimple action1 = new ActionSimple("Tesla");
        ActionSimple action2 = new ActionSimple("Amazon");

        // Enregistrement d'un cours pour chacune des actions simples pour le jour donné
        action1.enrgCours(dateTest, 100.0f); // cours de 100 pour action1
        action2.enrgCours(dateTest, 200.0f); // cours de 200 pour action2
        
        assertEquals(false, action1.equals(action2));
    }


    @Test
    void testHashCode() {
        // Création de deux actions simples avec le même nom
        ActionSimple action1 = new ActionSimple("Tesla");
        ActionSimple action2 = new ActionSimple("Tesla");

        // On vérifie que deux actions avec le même nom ont le même hashCode
        assertEquals(action1.hashCode(), action2.hashCode());

        // Création d'une autre action avec un nom différent
        ActionSimple action3 = new ActionSimple("Amazon");

        // On vérifie que deux actions avec des noms différents ont des hashCode différents
        assertNotEquals(action1.hashCode(), action3.hashCode());
    }

    @Test
    void testEquals() {
        // Création de deux actions simples avec le même nom
        ActionSimple action1 = new ActionSimple("Tesla");
        ActionSimple action2 = new ActionSimple("Tesla");

        // On vérifie que deux actions avec le même nom sont égales
        assertTrue(action1.equals(action2));

        // Création d'une autre action avec un nom différent
        ActionSimple action3 = new ActionSimple("Amazon");

        // On vérifie que deux actions avec des noms différents ne sont pas égales
        assertFalse(action1.equals(action3));

        // Test de l'égalité avec null (doit renvoyer false)
        assertFalse(action1.equals(null));

        // Test de l'égalité avec un objet d'un type différent (doit renvoyer false)
        assertFalse(action1.equals(new Object()));
    }

}