package factoria;

import estrategia.*;
import modelo.PoliticasDePrecios;

// Factoría para instanciar el calculador de precio según la política 
public class FactoriaCalculadorPrecio {

    // Devuelve la estrategia de precio correspondiente a la política recibida
    public static ICalculadorPrecio getCalculador(PoliticasDePrecios politica) {
        switch (politica) {
            case Normal:
                return new CalculadorPrecioNormal();
            case TemporadaAlta:
                return new CalculadorPrecioTemporadaAlta();
            case Oferton:
                return new CalculadorPrecioOferton();
            case ClienteFrecuente:
                return new CalculadorPrecioClienteFrecuente();
            default:
                // Estrategia por defecto en caso de no coincidencia
                return new CalculadorPrecioNormal();
        }
    }
}
