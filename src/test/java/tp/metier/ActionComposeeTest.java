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

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

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

        // On définit une composition : 40 % d'action1 et 60 % d'action2
        actionComposee.enrgComposition(action1, 0.4f);
        actionComposee.enrgComposition(action2, 0.6f);

        // Calcul attendu : (100 * 0.4) + (200 * 0.6) = 40 + 120 = 160
        float valeurAttendue = 160.0f;

        // Récupération de la valeur calculée pour le jour
        float valeurCalculee = actionComposee.valeur(dateTest);

        // Vérification que la valeur calculée correspond à la valeur attendue
        assertEquals(valeurAttendue, valeurCalculee, 0.001f,
                "La valeur de l'action composée devrait être calculée correctement.");
    }

    @Test
    void testValeurActionComposeeAvecCoursNonDefini() {
        // Création d'un jour pour le test
        Jour dateTest = new Jour(2025, 4);

        // Création de deux actions simples
        ActionSimple action1 = new ActionSimple("Tesla");
        ActionSimple action2 = new ActionSimple("Amazon");

        // Enregistrement d'un cours pour une seule des actions simples pour le jour
        // donné
        action1.enrgCours(dateTest, 100.0f); // cours de 100 pour action1
        // action2 n'a pas de cours défini pour dateTest

        // Création d'une action composée et définition de sa composition
        ActionComposee actionComposee = new ActionComposee("Action Compose 1");

        // On définit une composition : 50 % d'action1 et 50 % d'action2
        actionComposee.enrgComposition(action1, 0.5f);
        actionComposee.enrgComposition(action2, 0.5f);

        // Calcul attendu : (100 * 0.5) + (0 * 0.5) = 50 + 0 = 50
        float valeurAttendue = 50.0f;

        // Récupération de la valeur calculée pour le jour
        float valeurCalculee = actionComposee.valeur(dateTest);

        // Vérification que la valeur calculée correspond à la valeur attendue
        assertEquals(valeurAttendue, valeurCalculee, 0.001f,
                "La valeur de l'action composée devrait être calculée correctement même si une action n'a pas de cours défini.");
    }

    @Test
    void testValeurActionComposeeVide() {
        // Création d'un jour pour le test
        Jour dateTest = new Jour(2025, 4);

        // Création d'une action composée sans actions simples
        ActionComposee actionComposee = new ActionComposee("Action Compose Vide");

        // Calcul attendu : 0.0 car aucune action simple n'est enregistrée
        float valeurAttendue = 0.0f;

        // Récupération de la valeur calculée pour le jour
        float valeurCalculee = actionComposee.valeur(dateTest);

        // Vérification que la valeur calculée correspond à la valeur attendue
        assertEquals(valeurAttendue, valeurCalculee, 0.001f,
                "La valeur de l'action composée vide devrait être 0.0.");
    }

    @Test
    void testValeurActionComposeeMultipleActions() {
        // Création d'un jour pour le test
        Jour dateTest = new Jour(2025, 4);

        // Création de trois actions simples
        ActionSimple action1 = new ActionSimple("Tesla");
        ActionSimple action2 = new ActionSimple("Amazon");
        ActionSimple action3 = new ActionSimple("Google");

        // Enregistrement d'un cours pour chacune des actions simples pour le jour donné
        action1.enrgCours(dateTest, 100.0f); // cours de 100 pour action1
        action2.enrgCours(dateTest, 200.0f); // cours de 200 pour action2
        action3.enrgCours(dateTest, 300.0f); // cours de 300 pour action3

        // Création d'une action composée et définition de sa composition
        ActionComposee actionComposee = new ActionComposee("Action Compose 2");

        // Composition des actions avec des pourcentages différents
        actionComposee.enrgComposition(action1, 0.3f); // 30 % de Tesla
        actionComposee.enrgComposition(action2, 0.4f); // 40 % d'Amazon
        actionComposee.enrgComposition(action3, 0.3f); // 30 % de Google

        // = 30 + 80 + 90 = 200
        float valeurAttendue = 200.0f;

        // Récupération de la valeur calculée pour le jour donné
        float valeurCalculee = actionComposee.valeur(dateTest);

        // Vérification que la valeur calculée correspond à la valeur attendue
        assertEquals(valeurAttendue, valeurCalculee, 0.001f,
                "La valeur de l'action composée avec plusieurs actions devrait être calculée correctement.");
    }

    @Test
    void testEquals() {
        // Création de deux actions simples
        ActionSimple action1 = new ActionSimple("Tesla");
        ActionSimple action2 = new ActionSimple("Amazon");

        // Création de deux actions composés
        ActionComposee actionComposee1 = new ActionComposee("Action Compose 1");
        ActionComposee actionComposee2 = new ActionComposee("Action Compose 1");

        // Enregistre des cours pour les actions composés
        Jour dateTest = new Jour(2025, 4);
        action1.enrgCours(dateTest, 100.0f);
        action2.enrgCours(dateTest, 200.0f);

        actionComposee1.enrgComposition(action1, 0.4f);
        actionComposee1.enrgComposition(action2, 0.6f);
        actionComposee2.enrgComposition(action1, 0.4f);
        actionComposee2.enrgComposition(action2, 0.6f);

        // On teste que les deux actions composés sont les mêmes
        assertEquals(actionComposee1, actionComposee2, "Les objets ActionComposee devraient être égaux.");
    }

    @Test
    void testEqualsDifferent() {

        // Création de deux actions simples
        ActionSimple action1 = new ActionSimple("Tesla");
        ActionSimple action2 = new ActionSimple("Amazon");

        // Création de deux actions composés
        ActionComposee actionComposee1 = new ActionComposee("Action Compose 1");
        ActionComposee actionComposee2 = new ActionComposee("Action Compose 2");

        Jour dateTest = new Jour(2025, 4);
        action1.enrgCours(dateTest, 100.0f);
        action2.enrgCours(dateTest, 200.0f);

        actionComposee1.enrgComposition(action1, 0.4f);
        actionComposee1.enrgComposition(action2, 0.6f);
        actionComposee2.enrgComposition(action2, 0.5f);
        actionComposee2.enrgComposition(action1, 0.5f);

        // Test ce que ce ne sont pas les mêmes
        assertNotEquals(actionComposee1, actionComposee2, "Les objets ActionComposee devraient être différents.");
    }

    @Test
    void testHashCode() {
        // Création d'action simple
        ActionSimple action1 = new ActionSimple("Tesla");
        ActionSimple action2 = new ActionSimple("Amazon");

        // Création d'action composés
        ActionComposee actionComposee1 = new ActionComposee("Action Compose 1");
        ActionComposee actionComposee2 = new ActionComposee("Action Compose 1");

        // Même composition pour les deux
        Jour dateTest = new Jour(2025, 4);
        action1.enrgCours(dateTest, 100.0f);
        action2.enrgCours(dateTest, 200.0f);

        actionComposee1.enrgComposition(action1, 0.4f);
        actionComposee1.enrgComposition(action2, 0.6f);
        actionComposee2.enrgComposition(action1, 0.4f);
        actionComposee2.enrgComposition(action2, 0.6f);

        // Test que le hash code est le même pour les deux
        assertEquals(actionComposee1.hashCode(), actionComposee2.hashCode(),
                "Les hash codes des objets ActionComposee égaux devraient être identiques.");
    }

    @Test
    void testHashCodeDifferent() {
        // Création de deux actions simples
        ActionSimple action1 = new ActionSimple("Tesla");
        ActionSimple action2 = new ActionSimple("Amazon");

        // Création d'action composés différente
        ActionComposee actionComposee1 = new ActionComposee("Action Compose 1");
        ActionComposee actionComposee2 = new ActionComposee("Action Compose 2");

        Jour dateTest = new Jour(2025, 4);
        action1.enrgCours(dateTest, 100.0f);
        action2.enrgCours(dateTest, 200.0f);

        actionComposee1.enrgComposition(action1, 0.4f);
        actionComposee1.enrgComposition(action2, 0.6f);
        actionComposee2.enrgComposition(action2, 0.5f);
        actionComposee2.enrgComposition(action1, 0.5f);

        // Test que les hashcode ne sont pas les mêmes
        assertNotEquals(actionComposee1.hashCode(), actionComposee2.hashCode(),
                "Les hash codes des objets ActionComposee différents ne devraient pas être identiques.");
    }
}