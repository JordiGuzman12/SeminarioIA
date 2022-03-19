package codigoHO3;

import java.util.Arrays;

/**
 *
 * @author Jordi Guzmán
 */
public class Poblacion {
    
    private Individuo[] individuos;
    public Poblacion(int dimension) {
        individuos= new Individuo[dimension];
    }
    public  Poblacion inicializar(){
        for (int i = 0; i < individuos.length; i++) {
            individuos[i] = new Individuo(Algoritmo.OBJETIVO.length).inicializar();
        }
         ordenarPorFitness();
         return this;
    }

    public Individuo[] getIndividuos() {
        return individuos;
    }
    
    public void ordenarPorFitness(){
        Arrays.sort(individuos, (individuo1, individuo2) ->{
            int bandera=0;
            if(individuo1.getFitness()> individuo2.getFitness()) bandera=-1;
            else if (individuo1.getFitness()> individuo2.getFitness()) bandera=1;
            return bandera;
        });
    }
}
