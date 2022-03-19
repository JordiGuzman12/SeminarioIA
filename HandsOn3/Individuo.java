package codigoHO3;

import java.util.Arrays;

/**
 *
 * @author Jordi Guzmán
 */
public class Individuo {

    private int[] cromosoma;
    private int fitness = 0;
    boolean fitnessCambio=true;
    
    public Individuo( int dimension) {
        cromosoma=new int[dimension];
    }
    
    public Individuo inicializar(){
        for (int i = 0; i < cromosoma.length; i++) {
            if (Math.random()>0.5) {
                cromosoma[i]=1;
            }
            else cromosoma[i]=0;
        }
        return this;
    }
    
    public String toString(){
        return  Arrays.toString(this.cromosoma);
    }
    
    public int[] getCromosoma() {
        fitnessCambio=true;
        return cromosoma;        
    }

    public int getFitness() {
        if (fitnessCambio) {
            fitness=recalcularFitness();
            fitnessCambio= false;
        }
        return fitness;
    }
    
    public int recalcularFitness() {
        int nuevoValor = 0;
        for (int i = 0; i <this.cromosoma.length; i++) {
            if (this.cromosoma[i]==Algoritmo.OBJETIVO[i])
                nuevoValor++;
        }
        return nuevoValor;
    }

}
