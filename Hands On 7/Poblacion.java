/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handsOn7;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
/**
 *
 * @author Jordi Guzmán
 */
public class Poblacion {
    private Individuo[] poblacion;
    private double adaptPoblacion = -1;

    public Poblacion(int tamPoblacion) {
        this.poblacion = new Individuo[tamPoblacion];
    }

    public Poblacion(int tamPoblacion, int longitudCromosoma) {
        this.poblacion = new Individuo[tamPoblacion];

        for(int numeroIndividuos = 0; numeroIndividuos < tamPoblacion; numeroIndividuos++) {            
            Individuo individuo = new Individuo(longitudCromosoma);                
            this.poblacion[numeroIndividuos] = individuo;
        }
    }

    public Individuo[] getIndividuos() {
        return this.poblacion;
    }

    public Individuo getMejor(int offset) {
        Arrays.sort(this.poblacion, new Comparator<Individuo>(){
            @Override
            public int compare(Individuo o1, Individuo o2) {
                if (o1.getAdaptabilidad() > o2.getAdaptabilidad()) {
                    return -1;
                } else if (o1.getAdaptabilidad() > o2.getAdaptabilidad()){
                    return 1;
                }
                return 0;
            }
        });

        return this.poblacion[offset];
    }

    public void setAdaptPoblacion(double adaptabilidad) {
        this.adaptPoblacion = adaptabilidad;
    }

    public double getAdaptPoblacion() {
        return this.adaptPoblacion;
    }

    public int longitud() {
        return this.poblacion.length;
    }

    public void setIndividuo(int offset, Individuo individuo) {
        this.poblacion[offset] = individuo;
    }

    public Individuo getIndividuo(int offset) {
        return this.poblacion[offset];
    }

    public void cambiar() {
        Random rnd = new Random();
        for (int i = poblacion.length; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Individuo a = poblacion[index];
            poblacion[index] = poblacion[i];
            poblacion[i] = a;
        }
    }
}

