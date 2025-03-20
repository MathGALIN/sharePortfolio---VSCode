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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import tp.metier.*;

public class Run {

    public static void main(String[] args) {
        
        Investisseur investisseur = new Investisseur(1, "Alice", 10000.0); // 初始资金 10000€
        ActionSimple apple = new ActionSimple("apple");
        ActionSimple tesla = new ActionSimple("tesla");
        Jour jour = new Jour(2024, 100); // 假设 2024 年第 100 天

        apple.enrgCours(jour, 150);
        tesla.enrgCours(jour, 200);

        investisseur.acheterAction(apple, 10, apple.valeur(jour));
        investisseur.acheterAction(tesla, 5, tesla.valeur(jour));



        // 捕获 `System.out.println()` 的输出
        System.out.println("Affichage : \n \n");
        investisseur.afficherPortefeuille(jour);

    }

}
