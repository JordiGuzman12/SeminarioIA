package codigoHO3;

/**
 *
 * @author Jordi Guzmán
 */
public class Algoritmo {
      public static final int[] OBJETIVO= {1,1,1,1,1,1,1,1,1,1};
       public final double RECOMBINACION = 0.5;
       public final double MUTACION =0.25;
       public static final int MAX_POBLACION = 8;
       public static final int TAM_SELECCION_TORNEO=4;
      
      public Poblacion siguienteGeneracion(Poblacion p){
          return mutarPoblacion(recombinarPoblacion(p));
      }
      
       private Poblacion recombinarPoblacion( Poblacion p){
           Poblacion recombinada= new Poblacion(p.getIndividuos().length);
           for (int i = 0; i < p.getIndividuos().length; i++) {
               Individuo individuo1=elegirPoblacionTorneo(p).getIndividuos()[0];
               Individuo individuo2=elegirPoblacionTorneo(p).getIndividuos()[0];
               recombinada.getIndividuos()[i]=recombinarIndividuo(individuo1, individuo2, RECOMBINACION);
           }
           return recombinada;
      }
       
      private Poblacion mutarPoblacion( Poblacion p){
          Poblacion mutada= new Poblacion(p.getIndividuos().length);
          for (int i = 0; i < p.getIndividuos().length; i++){
              mutada.getIndividuos()[i]=mutarIndividuo(p.getIndividuos()[i]);
          }
           return mutada;
      }
      
      private Individuo recombinarIndividuo( Individuo padre1, Individuo padre2, double facRecom){
          Individuo hijo= new Individuo(OBJETIVO.length);
          for (int i = 0; i < padre1.getCromosoma().length; i++) {
              if (facRecom> Math.random()) {
                  hijo.getCromosoma()[i]=padre1.getCromosoma()[i];
              }
              else{
                 hijo.getCromosoma()[i]=padre2.getCromosoma()[i]; 
              }   
          }
          return hijo;
      }
      
      private Individuo mutarIndividuo(Individuo i){
          Individuo individuoMutado= new Individuo(i.getCromosoma().length);
          for (int j = 0; j < i.getCromosoma().length; j++) {
              if (MUTACION <Math.random()) {
                  if (Math.random() <0.5) {
                      individuoMutado.getCromosoma()[j]=0;
                  } else
                   individuoMutado.getCromosoma()[j]=1;   
              } else
                  individuoMutado.getCromosoma()[j]=i.getCromosoma()[j];
          }
          return individuoMutado;
      }
      
      private  Poblacion elegirPoblacionTorneo(Poblacion p){
          Poblacion poblacionTorneo = new Poblacion(TAM_SELECCION_TORNEO);
          for (int i = 0; i < TAM_SELECCION_TORNEO; i++) {
              poblacionTorneo.getIndividuos()[i]=
                      p.getIndividuos()[(int)(Math.random()*p.getIndividuos().length)];
          }
          poblacionTorneo.ordenarPorFitness();
          return poblacionTorneo;
      }
}
