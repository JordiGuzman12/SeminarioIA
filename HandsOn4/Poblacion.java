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
public class Poblacion {

    private Individuo[] individuos;

    public Poblacion(int dimension) {
        individuos = new Individuo[dimension];
    }

    public Poblacion inicializar() {
        for (int i = 0; i < individuos.length; i++) {
            individuos[i] = new Individuo(Algoritmo.OBJETIVO.length, Algoritmo.OBJETIVO[0].length).inicializar();
        }
        ordenarPorFitness();
        return this;
    }

    public Individuo[] getIndividuos() {
        return individuos;
    }

    public void ordenarPorFitness() {
        Arrays.sort(individuos, (individuo1, individuo2) -> {
            int bandera = 0;
            if (individuo1.getFitness() > individuo2.getFitness()) {
                bandera = -1;
            } else if (individuo1.getFitness() > individuo2.getFitness()) {
                bandera = 1;
            }
            return bandera;
        });
    }
}
