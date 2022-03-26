/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoHO4;

import java.util.Arrays;

/**
 *
 * @author Jordi Guzmán
 */
public class Individuo {

    private int[][] cromosoma;
    private int fitness = 0;
    boolean fitnessCambio = true;

    public Individuo(int filas, int columnas) {
        cromosoma = new int[filas][columnas];
    }

    public Individuo inicializar() {
        for (int fila = 0; fila < cromosoma.length; fila++) {
            for (int columna = 0; columna < cromosoma[fila].length; columna++) {
                if (Math.random() > 0.5) {
                    cromosoma[fila][columna] = 1;
                } else {
                    cromosoma[fila][columna] = 0;
                }
            }
        }

        return this;
    }

    public String toString() {
        return Arrays.deepToString(this.cromosoma);
    }

    public int[][] getCromosoma() {
        fitnessCambio = true;
        return cromosoma;
    }

    public int getFitness() {
        if (fitnessCambio) {
            fitness = recalcularFitness();
            fitnessCambio = false;
        }
        return fitness;
    }

    public int recalcularFitness() {
        int nuevoValor = 0;
        for (int fila = 0; fila < cromosoma.length; fila++) {
            for (int columna = 0; columna < cromosoma[fila].length; columna++) {
                if (this.cromosoma[fila][columna] == Algoritmo.OBJETIVO[fila][columna]) {
                    nuevoValor++;
                }
            }
        }
        return nuevoValor;
    }

}
