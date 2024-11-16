package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.aleatorio.ProbabilidadInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AleatorioTest {
    @Test
    void test01UnAletarioNoPuedeIstanciarseConCeroDeProbabilidad() {
        // Arrange / Act / Assert
        assertThrows(ProbabilidadInvalidaException.class, () -> {new Aleatorio(0);});
    }
}

