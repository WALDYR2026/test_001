package edu.laboratorio;

/** Calcula factoriales exactos para enteros entre 0 y 20. */
public class Factorial {
    public static long calcular(int numero) {
        if (numero < 0 || numero > 20) {
            throw new IllegalArgumentException("El número debe estar entre 0 y 20");
        }
        long resultado = 1;
        for (int i = 2; i <= numero; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static void main(String[] args) {
        int numero = 5;
        System.out.println("Factorial de " + numero + " = " + calcular(numero));
    }
}
