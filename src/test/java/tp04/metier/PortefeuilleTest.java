package tp04.metier;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PortefeuilleTest {

    @Test
    void testAcheterNouvelleAction() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple apple = new ActionSimple("Apple");

        portefeuille.acheter(apple, 10);

        Map<Action, Integer> actions = portefeuille.consulterActions();
        assertEquals(1, actions.size());
        assertEquals(10, actions.get(apple));
    }

    @Test
    void testAcheterActionExistante_AjouteQuantite() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple apple = new ActionSimple("Apple");

        portefeuille.acheter(apple, 5);
        portefeuille.acheter(apple, 7);

        Map<Action, Integer> actions = portefeuille.consulterActions();
        assertEquals(12, actions.get(apple));
    }

    @Test
    void testVendreAction_SuccessPartiel() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple apple = new ActionSimple("Apple");

        portefeuille.acheter(apple, 10);
        float montant = portefeuille.vendre(apple, 4, 100.0f);

        assertEquals(400.0f, montant);
        assertEquals(6, portefeuille.consulterActions().get(apple));
    }

    @Test
    void testVendreAction_Tout() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple apple = new ActionSimple("Apple");

        portefeuille.acheter(apple, 5);
        float montant = portefeuille.vendre(apple, 5, 120.0f);

        assertEquals(600.0f, montant);
        assertFalse(portefeuille.consulterActions().containsKey(apple));
    }

    @Test
    void testVendreAction_QuantiteInsuffisante() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple apple = new ActionSimple("Apple");

        portefeuille.acheter(apple, 3);
        float montant = portefeuille.vendre(apple, 5, 100.0f);

        assertEquals(0.0f, montant); // 销售失败
        assertEquals(3, portefeuille.consulterActions().get(apple));
    }

    @Test
    void testVendreAction_NonExistante() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple apple = new ActionSimple("Apple");

        float montant = portefeuille.vendre(apple, 2, 90.0f);
        assertEquals(0.0f, montant);
    }

    @Test
    void testConsulterActions() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple apple = new ActionSimple("Apple");
        ActionSimple tesla = new ActionSimple("Tesla");

        portefeuille.acheter(apple, 10);
        portefeuille.acheter(tesla, 20);

        Map<Action, Integer> actions = portefeuille.consulterActions();
        assertEquals(2, actions.size());
        assertEquals(10, actions.get(apple));
        assertEquals(20, actions.get(tesla));
    }

    @Test
    void testValeurTotaleDuPortefeuille() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple apple = new ActionSimple("Apple");
        ActionSimple tesla = new ActionSimple("Tesla");
        Jour jour = new Jour(2024, 100);

        portefeuille.acheter(apple, 2);  // En supposant que valeur() renvoie une valeur fixe, par exemple 150,0
        portefeuille.acheter(tesla, 3);  // En supposant que valeur() renvoie une valeur fixe, par exemple 200,0

        // Vous pouvez modifier ActionSimple::valeur pour qu'il renvoie des valeurs fixes à des fins de test.
        float expected = (2 * apple.valeur(jour)) + (3 * tesla.valeur(jour));
        float actual = portefeuille.valeur(jour);

        assertEquals(expected, actual, 0.001);
    }

    @Test
    void testAfficherPortefeuille_Vide() {
        Portefeuille portefeuille = new Portefeuille();
        Jour jour = new Jour(2024, 100);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        portefeuille.afficherPortefeuille(jour);

        String output = outContent.toString().trim();
        System.setOut(System.out); // Rétablir la sortie standard

        assertTrue(output.contains("Votre portefeuille est vide."));
    }

    @Test
    void testAfficherPortefeuille_AvecActions() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple apple = new ActionSimple("Apple");
        Jour jour = new Jour(2024, 100);

        portefeuille.acheter(apple, 5); // En supposant que apple.valeur(jour) dispose d'une implémentation de

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        portefeuille.afficherPortefeuille(jour);

        String output = outContent.toString();
        System.setOut(System.out); // Rétablir la sortie standard

        assertTrue(output.contains("Votre portefeuille contient"));
        assertTrue(output.contains("Apple : 5 actions")); // Les prix ne sont pas toujours disponibles, car les formats sont susceptibles d'être modifiés.
    }
}

