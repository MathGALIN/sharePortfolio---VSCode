package tp.metier;

import org.junit.jupiter.api.Test;

class testJour {

    @Test
    void testJours() {
        Jour j = new Jour(1,1);
        Assertions.assertEqu als(1, j.getAnnee());
    }

}
