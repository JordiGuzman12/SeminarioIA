/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoHO2;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.Arrays.asList;

/**
 *
 * @author Jordi Guzmán
 */
public class Algoritmo {
    private static final List<Integer> advertising = asList(23, 26, 30, 34, 43, 48, 52, 57, 58); // Advertising
    private static final List<Integer> ventas = asList(651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518); // Ventas
    double pendiente=0, intercepto=0, prediccion=0;
    String ecuacion="";
    
    public void predecirValor(int valor) {
        if (advertising.size() != ventas.size()) {
            throw new IllegalStateException("Los datos deben ser pares ordenados");
        }

        Integer cantidadDatos = advertising.size();

        List<Double> xCuadrada = advertising
                .stream()
                .map(position -> Math.pow(position, 2))
                .collect(Collectors.toList());

        List<Integer> productoXY = IntStream.range(0, cantidadDatos)
                .map(i -> advertising.get(i) * ventas.get(i))
                .boxed()
                .collect(Collectors.toList());

        Integer sumX = advertising
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        Integer sumY = ventas
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        Double sumXCuadrada = xCuadrada
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        Integer sumXY = productoXY
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        int pendienteNom = cantidadDatos * sumXY - sumY * sumX;
        double pendienteDen = cantidadDatos * sumXCuadrada - Math.pow(sumX, 2);
        this.pendiente = pendienteNom / pendienteDen;
        String textoPendiente=String.format("%,.5f", pendiente);

        double intercepNom = sumY - pendiente * sumX;
        double intercepDen = cantidadDatos;
        this.intercepto = intercepNom / intercepDen;
        String textoIntercepto=String.format("%,.5f", intercepto);
        
        prediccion=(pendiente * valor) + intercepto;
        String textoPrediccion=String.format("%,.5f", prediccion);

        System.out.println("El valor de Y estimado con X= " + valor + " es: " + prediccion );
        System.out.println("Pendiente (b1): " + pendiente);
        System.out.println("Intercepto (b0): " + intercepto);
        this.ecuacion=("(" + textoPendiente + ")( " + valor + " ) + " + textoIntercepto + " = " + textoPrediccion);
    }

}
