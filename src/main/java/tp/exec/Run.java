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
package tp.exec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import tp.metier.*;

public class Run {

    public static void main(String[] args) {
        
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action1 = new ActionSimple("Action1");
        Jour jour = new Jour(2025, 73);

        action1.enrgCours(jour, 10.0f);

        portefeuille.acheter(action1, 5);

        // Capture the output using a print stream to verify afficherPortefeuille()
        // This will print information about the portfolio
        portefeuille.afficherPortefeuille(jour);

    }

}
