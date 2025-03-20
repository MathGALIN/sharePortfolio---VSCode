package tp.metier;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        String result1 = gestionnaire.mettreAJourPrix("APPLE", jour1, 150.0f);
        String result2 = gestionnaire.afficherPrix("APPLE", jour1);
        String result3 = gestionnaire.mettreAJourPrix("APPLE", jour1, 155.0f);
        String result4 = gestionnaire.afficherPrix("APPLE", jour1);

        // Vérification des retours de fonction
        assertEquals("Prix mis à jour pour APPLE le 75/2025 à 150.0.", result1);
        assertEquals("Prix de APPLE le 75/2025 : 150.0.", result2);
        assertEquals("Prix mis à jour pour APPLE le 75/2025 à 155.0.", result3);
        assertEquals("Prix de APPLE le 75/2025 : 155.0.", result4);
    }
}