package edu.laboratorio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {
    @Test
    void pruebaFactorialDeCero() {
        assertEquals(1, Factorial.calcular(0));
    }

    @Test
    void pruebaFactorialDeUno() {
        assertEquals(1, Factorial.calcular(1));
    }

    @Test
    void pruebaFactorialDeTres() {
        assertEquals(6, Factorial.calcular(3));
    }

    @Test
    void pruebaFactorialDeCinco() {
        assertEquals(120, Factorial.calcular(5));
    }

    @Test
    void pruebaFactorialDeSiete() {
        assertEquals(5040, Factorial.calcular(7));
    }

    @Test
    void pruebaNumeroNegativo() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.calcular(-3));
    }
}
