package codigoHO4;

import jade.core.Agent;
import jade.core.behaviours.*;
import java.util.Arrays;

/**
 *
 * @author Jordi Guzmán
 */
public class BetasAgent extends Agent {


    protected void setup() {
        System.out.println("Agente " + getLocalName() + " ha iniciado.");
        addBehaviour(new encontrarBetas());

    }

    private class encontrarBetas extends OneShotBehaviour {
        
        public void imprimir(Poblacion p) {
            System.out.println("Objetivo: " + Arrays.deepToString(Algoritmo.OBJETIVO));
            System.out.println("\n--------------------------------");
            for (int i = 0; i < p.getIndividuos().length; i++) {
                System.out.println("Cromosoma # " + i + " :" +
                        Arrays.deepToString(p.getIndividuos()[i].getCromosoma()) +
                        " | Fitness: " + p.getIndividuos()[i].getFitness());
            }
             System.out.println("--------------------------------\n");
        }
        
        public void binToDec(Poblacion p) {
            String beta0="", beta1="";
            int valorB0=0, valorB1=0;
            for (int columna = 0; columna < Algoritmo.OBJETIVO[0].length; columna++) {
                beta0+=Integer.toString(p.getIndividuos()[0].getCromosoma()[0][columna]);
            }
            
            for (int columna = 0; columna < Algoritmo.OBJETIVO[0].length; columna++) {
                beta1+=Integer.toString(p.getIndividuos()[0].getCromosoma()[1][columna]);
            }
            
            valorB0= Integer.parseInt(beta0, 2);
            valorB1= Integer.parseInt(beta1, 2);
            System.out.println("VALORES CALCULADOS PARA BETA0 Y BETA1\n");
            System.out.println("B0: " + valorB0 + "\n");
            System.out.println("B1: " + valorB1 + "\n");
        }

        public void action() {
            Poblacion poblacion= new Poblacion(Algoritmo.MAX_POBLACION).inicializar();
            Algoritmo algoritmo= new  Algoritmo();
            System.out.println("------------------------------------");
            System.out.println("Generacion 0 " + " | Valor Mejor Cromosoma: " + 
                    poblacion.getIndividuos()[0].getFitness());
            imprimir(poblacion);
            int numGeneracion=0;
            while (poblacion.getIndividuos()[0].getFitness() < 16) {                
                numGeneracion++;
                poblacion=algoritmo.siguienteGeneracion(poblacion);
                poblacion.ordenarPorFitness();
                System.out.println("Generacion # " + numGeneracion + " | Valor Mejor Cromosoma: " + 
                    poblacion.getIndividuos()[0].getFitness());
            imprimir(poblacion);
                
            }
            binToDec(poblacion);
            
        }

        public int onEnd() {
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}
