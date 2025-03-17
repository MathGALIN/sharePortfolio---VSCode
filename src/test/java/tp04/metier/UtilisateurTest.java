package tp04.metier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {

    private Utilisateur utilisateur;
    private Action actionTesla;
    private Action actionApple;

    @BeforeEach
    void setUp() {
        utilisateur = new Utilisateur("Alice", 2000.0f); // Solde initial de 2000€
        actionTesla = new ActionSimple("Tesla");
        actionApple = new ActionSimple("Apple");

        utilisateur.acheterActions(actionTesla, 5, 200.0f);
        utilisateur.acheterActions(actionApple, 10, 100.0f);
    }

    @Test
    void testVendreActions() {
        String result = utilisateur.vendreActions(actionTesla, 3, 250.0f);
        assertEquals("3 actions de Tesla vendues pour 750.0€.", result);
        assertEquals(1750.0f, utilisateur.getSolde());
        assertEquals(2, utilisateur.getPortefeuille().getMapLignes().get(actionTesla).getQte());

        String errorResult = utilisateur.vendreActions(actionTesla, 3, 250.0f);
        assertEquals("Vous n'avez pas assez d'actions à vendre.", errorResult);
    }

    @Test
    void testAcheterActions() {
        String result = utilisateur.acheterActions(actionTesla, 2, 200.0f);
        assertEquals("2 actions de Tesla achetées pour 400.0€.", result);
        assertEquals(1350.0f, utilisateur.getSolde());
        assertEquals(7, utilisateur.getPortefeuille().getMapLignes().get(actionTesla).getQte());

        String errorResult = utilisateur.acheterActions(actionTesla, 4, 300.0f);
        assertEquals("Fonds insuffisants pour l'achat.", errorResult);
    }

    @Test
    void testConsulterPortefeuille() {
        final PrintStream originalOut = System.out;
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        utilisateur.consulterPortefeuille();

        assertTrue(baos.toString().contains("Portefeuille de Alice"));
        assertTrue(baos.toString().contains("Solde disponible: 1350.0€"));

        System.setOut(originalOut);
    }
}
