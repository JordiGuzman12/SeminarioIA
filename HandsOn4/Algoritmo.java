/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoHO4;

/**
 *
 * @author Jordi Guzmán
 */
public class Algoritmo {

    public static final int[][] OBJETIVO = {{1, 0, 1, 0, 1, 0, 0, 0},
    {0, 0, 0, 1, 0, 1, 1, 1}};
    public final double RECOMBINACION = 0.5;
    public final double MUTACION = 0.25;
    public static final int MAX_POBLACION = 8;
    public static final int TAM_SELECCION_TORNEO = 4;

    public Poblacion siguienteGeneracion(Poblacion p) {
        return mutarPoblacion(recombinarPoblacion(p));
    }

    private Poblacion recombinarPoblacion(Poblacion p) {
        Poblacion recombinada = new Poblacion(p.getIndividuos().length);
        for (int i = 0; i < p.getIndividuos().length; i++) {
            Individuo individuo1 = elegirPoblacionTorneo(p).getIndividuos()[0];
            Individuo individuo2 = elegirPoblacionTorneo(p).getIndividuos()[0];
            recombinada.getIndividuos()[i] = recombinarIndividuo(individuo1, individuo2, RECOMBINACION);
        }
        return recombinada;
    }

    private Poblacion mutarPoblacion(Poblacion p) {
        Poblacion mutada = new Poblacion(p.getIndividuos().length);
        for (int i = 0; i < p.getIndividuos().length; i++) {
            mutada.getIndividuos()[i] = mutarIndividuo(p.getIndividuos()[i]);
        }
        return mutada;
    }

    private Individuo recombinarIndividuo(Individuo padre1, Individuo padre2, double facRecom) {
        Individuo hijo = new Individuo(OBJETIVO.length, OBJETIVO[0].length);
        for (int filas = 0; filas < padre1.getCromosoma().length; filas++) {
            for (int columnas = 0; columnas < OBJETIVO[0].length; columnas++) {
                if (facRecom > Math.random()) {
                    hijo.getCromosoma()[filas][columnas] = padre1.getCromosoma()[filas][columnas];
                } else {
                    hijo.getCromosoma()[filas][columnas] = padre2.getCromosoma()[filas][columnas];
                }
            }
        }
        return hijo;
    }

    private Individuo mutarIndividuo(Individuo i) {
        Individuo individuoMutado = new Individuo(i.getCromosoma().length, OBJETIVO[0].length);
        for (int filas = 0; filas < i.getCromosoma().length; filas++) {
            for (int columnas = 0; columnas < OBJETIVO[0].length; columnas++) {
                if (MUTACION < Math.random()) {
                    if (Math.random() < 0.5) {
                        individuoMutado.getCromosoma()[filas][columnas] = 0;
                    } else {
                        individuoMutado.getCromosoma()[filas][columnas] = 1;
                    }
                } else {
                    individuoMutado.getCromosoma()[filas][columnas] = i.getCromosoma()[filas][columnas];
                }
            }

        }
        return individuoMutado;
    }

    private Poblacion elegirPoblacionTorneo(Poblacion p) {
        Poblacion poblacionTorneo = new Poblacion(TAM_SELECCION_TORNEO);
        for (int i = 0; i < TAM_SELECCION_TORNEO; i++) {
            poblacionTorneo.getIndividuos()[i]
                    = p.getIndividuos()[(int) (Math.random() * p.getIndividuos().length)];
        }
        poblacionTorneo.ordenarPorFitness();
        return poblacionTorneo;
    }
}
