package tp.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NewJourTest {
    @Test
    void testJours() {
        Jour j = new Jour(1, 1);
        Assertions.assertEquals(1, j.getAnnee());
    }
}
