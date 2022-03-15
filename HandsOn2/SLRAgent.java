/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoHO2;
import jade.core.Agent;
import jade.core.behaviours.*;

/**
 *
 * @author Jordi Guzmán
 */
public class SLRAgent extends Agent {
    Interfaz gui=new Interfaz();

    protected void setup() {
        System.out.println("Agente " + getLocalName() + " ha iniciado.");
        addBehaviour(new calcularRegresion());

    }

    private class calcularRegresion extends OneShotBehaviour {

        public void action() {
            gui.setVisible(true);
        }
        public int onEnd() {
            myAgent.doDelete();   
            return super.onEnd();
        }
    }
}
