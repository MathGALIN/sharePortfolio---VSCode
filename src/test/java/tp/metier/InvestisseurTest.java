package tp.metier;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

class InvestisseurTest {
    private Investisseur investisseur;
    private ActionSimple apple;
    private ActionSimple tesla;
    private Jour jour;

    @BeforeEach
    void setUp() {
        investisseur = new Investisseur(1, "Alice", 10000.0); // 初始资金 10000€
        apple = new ActionSimple("apple");
        tesla = new ActionSimple("tesla");
        jour = new Jour(2024, 100); // 假设 2024 年第 100 天

        apple.enrgCours(jour, 150);
        tesla.enrgCours(jour, 200);
    }

    @Test
    void testAcheterAction_Success() {
        investisseur.acheterAction(apple, 10, apple.valeur(jour));
        investisseur.acheterAction(tesla, 5, tesla.valeur(jour));

        Map<Action, Integer> actions = investisseur.getPortefeuille().consulterActions();

        assertEquals(2, actions.size()); // 组合中应该有两只股票
        assertEquals(10, actions.get(apple)); // Apple 应该有 10 股
        assertEquals(5, actions.get(tesla)); // Tesla 应该有 5 股
    }

    @Test
    void testAfficherPortefeuille_AvecActions() {
    // 先买入股票
        investisseur.acheterAction(apple, 10, apple.valeur(jour));
        investisseur.acheterAction(tesla, 5, tesla.valeur(jour));

        // 捕获 `System.out.println()` 的输出
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String resultat = investisseur.afficherPortefeuille(jour);

        // 确保格式正确
        assertTrue(resultat.contains("apple : 10 actions (Valeur unitaire: 150,00)"));
        assertTrue(resultat.contains("tesla : 5 actions (Valeur unitaire: 200,00)"));

        System.setOut(System.out);
}


    @Test
    void testAfficherPortefeuille_Vide() {
        // 捕获 `System.out.println()` 的输出
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        investisseur.afficherPortefeuille(jour);

        String output = outputStream.toString();
        assertTrue(output.contains("Votre portefeuille est vide.")); // 应该显示"投资组合为空"

        // 恢复标准输出流
        System.setOut(System.out);
    }

    @Test
    void testAcheterAction_FondsInsuffisants() {
        boolean result = investisseur.acheterAction(apple, 100, 150.0f); // 需要 15000€，但只有 10000€
        assertFalse(result);
        assertEquals(10000.0, investisseur.getSolde()); // 资金不变
    }


}