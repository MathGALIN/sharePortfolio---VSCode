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

class JourTest {

    @Test
    //test constructeur et getter pour un jour
    void testConstructeurGetter() {
        Jour jour = new Jour(2025, 120);
        assertEquals(2025, jour.getAnnee());
        assertEquals(120, jour.getNoJour());
    }


    @Test
    //Test le hashcode pour un jour
    void testHashCode() {
        Jour jour1 = new Jour(2025, 120);
        Jour jour2 = new Jour(2025, 120);
        Jour jour3 = new Jour(2024, 120);

        assertEquals(jour1.hashCode(), jour2.hashCode());
        assertNotEquals(jour1.hashCode(), jour3.hashCode());
    }

    @Test
    //Test que deux jours soient égaux ou non
    void testEquals() {
        Jour jour1 = new Jour(2025, 120);
        Jour jour2 = new Jour(2025, 120);
        Jour jour3 = new Jour(2024, 125);


        assertTrue(jour1.equals(jour2));
        assertFalse(jour1.equals(jour3));
    }
}