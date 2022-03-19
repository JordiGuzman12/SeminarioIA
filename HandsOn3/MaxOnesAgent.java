ackage codigoHO3;

import jade.core.Agent;
import jade.core.behaviours.*;
import java.util.Arrays;

/**
 *
 * @author Jordi Guzmán
 */
public class MaxOnesAgent extends Agent {


    protected void setup() {
        System.out.println("Agente " + getLocalName() + " ha iniciado.");
        addBehaviour(new algoritmoMaxOnes());

    }

    private class algoritmoMaxOnes extends OneShotBehaviour {
        
        public void imprimir(Poblacion p) {
            System.out.println("--------------------------------");
            for (int i = 0; i < p.getIndividuos().length; i++) {
                System.out.println("Cromosoma # " + i + " :" +
                        Arrays.toString(p.getIndividuos()[i].getCromosoma()) +
                        " | Fitness: " + p.getIndividuos()[i].getFitness());
            }
        }

        public void action() {
            Poblacion poblacion= new Poblacion(Algoritmo.MAX_POBLACION).inicializar();
            Algoritmo algoritmo= new  Algoritmo();
            System.out.println("------------------------------------");
            System.out.println("Generacion 0 " + " | Valor Mejor Cromosoma: " + 
                    poblacion.getIndividuos()[0].getFitness());
            imprimir(poblacion);
            int numGeneracion=0;
            while (poblacion.getIndividuos()[0].getFitness() < Algoritmo.OBJETIVO.length) {                
                numGeneracion++;
                poblacion=algoritmo.siguienteGeneracion(poblacion);
                poblacion.ordenarPorFitness();
                System.out.println("Generacion # " + numGeneracion + " | Valor Mejor Cromosoma: " + 
                    poblacion.getIndividuos()[0].getFitness());
            imprimir(poblacion);
                
            }
            
        }

        public int onEnd() {
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}
