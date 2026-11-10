package estrategia;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import modelo.Vehiculo;

// Estrategia de precio para Cliente Frecuente
public class CalculadorPrecioClienteFrecuente implements ICalculadorPrecio {

    @Override
    public int calcularPrecioAlquiler(LocalDate fechaInicio, LocalDate fechaFin, Vehiculo vehiculo) {
        // Días totales de alquiler (incluye el día de inicio y de fin)
        int dias = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
        
        // Precio base diario del vehículo
        int precioBase = vehiculo.getPrecioBase();
       
        // Separación por tramos: máx 3 días para el 1er tramo, el resto para el 2º
        int diasPrimeros = Math.min(dias, 3);
        int diasRestantes = Math.max(0, dias - 3);
       
        // Aplicación de descuentos: 20% los primeros 3 días (0.80) y 40% el resto (0.60)
        double total = (diasPrimeros * precioBase * 0.80) + (diasRestantes * precioBase * 0.60);
        
        // Redondeo al entero más cercano
        return (int) Math.round(total);
    }
}
