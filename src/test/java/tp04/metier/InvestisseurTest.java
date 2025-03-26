package tp04.metier;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

public class InvestisseurTest {
    
    @Test
    void testAcheterAction_Success() {
        Investisseur investisseur = new Investisseur(1, "Alice", 10000.0);
        ActionSimple apple = new ActionSimple("Apple");
        ActionSimple tesla = new ActionSimple("Tesla");
        Jour jour = new Jour(2024, 100);

        investisseur.acheterAction(apple, 10, apple.valeur(jour));
        investisseur.acheterAction(tesla, 5, tesla.valeur(jour));

        Map<Action, Integer> actions = investisseur.getPortefeuille().consulterActions();

        assertEquals(2, actions.size()); 
        assertEquals(10, actions.get(apple)); 
        assertEquals(5, actions.get(tesla)); 
    }

    @Test
    void testAfficherPortefeuille_AvecActions() {
        Investisseur investisseur = new Investisseur(1, "Alice", 10000.0);
        ActionSimple apple = new ActionSimple("Apple");
        ActionSimple tesla = new ActionSimple("Tesla");
        Jour jour = new Jour(2024, 100);

        investisseur.acheterAction(apple, 10, apple.valeur(jour));
        investisseur.acheterAction(tesla, 5, tesla.valeur(jour));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        investisseur.afficherPortefeuille(jour);
        String output = outputStream.toString();
        System.out.println("Captured Output: \n" + output);
    }

    @Test
    void testAfficherPortefeuille_Vide() {
        Investisseur investisseur = new Investisseur(1, "Alice", 10000.0);
        Jour jour = new Jour(2024, 100);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        investisseur.afficherPortefeuille(jour);
        String output = outputStream.toString();

        assertTrue(output.contains("Votre portefeuille est vide.")); 
        System.setOut(System.out);
    }

    @Test
    void testAcheterAction_FondsInsuffisants() {
        Investisseur investisseur = new Investisseur(1, "Alice", 10000.0);
        ActionSimple apple = new ActionSimple("Apple");

        boolean result = investisseur.acheterAction(apple, 100, 150.0f); 
        assertFalse(result);
        assertEquals(10000.0, investisseur.getSolde()); 
    }

    @Test
    void testVendreAction_Success() {
        Investisseur investisseur = new Investisseur(1, "Alice", 10000.0f);
        ActionSimple apple = new ActionSimple("Apple");
        Jour jour = new Jour(2024, 100);

        // Achetez d'abord des actions
        investisseur.acheterAction(apple, 10, 100.0f); // Total 1000€

        double soldeAvantVente = investisseur.getSolde();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        investisseur.vendreAction(apple, 5, 120.0f); // Vente de 5 actions au prix de 120 € chacune.
        String output = outputStream.toString();
        System.setOut(System.out); // Rétablir la sortie standard
    }

}