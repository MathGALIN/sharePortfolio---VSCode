package tp.metier;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;


class GestionnaireTest {
    private GestionnaireActions gestionnaire;
    private ActionSimple apple;
    private Jour jour1;

    @BeforeEach
    void setUp() {
        gestionnaire = new GestionnaireActions();
        apple = new ActionSimple("APPLE");
        jour1 = new Jour(2025, 75); // 75e jour de 2025
        gestionnaire.ajouterAction(apple);
    }

    @Test
    void testMiseAJourEtAffichagePrix() {
        // Capture de la sortie console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Mise à jour et affichage
        gestionnaire.mettreAJourPrix("APPLE", jour1, 150.0f);
        gestionnaire.afficherPrix("APPLE", jour1);

        gestionnaire.mettreAJourPrix("APPLE", jour1, 155.0f);
        gestionnaire.afficherPrix("APPLE", jour1);

        // Vérification des sorties attendues
        String expectedOutput =
                "Prix mis à jour pour APPLE le 75/2025 à 150.0.\n" +
                "Prix de APPLE le 75/2025 : 150.0.\n" +
                "Prix mis à jour pour APPLE le 75/2025 à 155.0.\n" +
                "Prix de APPLE le 75/2025 : 155.0.\n";

        assertEquals(expectedOutput.replace("\r\n", "\n"), outContent.toString().replace("\r\n", "\n"));

        // Restauration de la sortie standard
        System.setOut(System.out);
    }

}