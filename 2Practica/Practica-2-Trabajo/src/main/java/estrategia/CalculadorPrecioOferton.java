package estrategia;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import modelo.Vehiculo;

// Estrategia de precio Ofertón (Patrón Strategy)
public class CalculadorPrecioOferton implements ICalculadorPrecio {

    @Override
    public int calcularPrecioAlquiler(LocalDate fechaInicio, LocalDate fechaFin, Vehiculo vehiculo) {
        // Días totales de alquiler (incluye el día de inicio y de fin)
        int dias = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
        
        // Precio base diario con un 30% de descuento aplicado (0.70)
        double precioBaseDiarioDescuento = vehiculo.getPrecioBase() * 0.70;
        
        // Subtotal acumulado por el total de días
        double total = dias * precioBaseDiarioDescuento;
        
        // Descuento adicional del 10% (0.90) si la estancia supera los 7 días
        if (dias > 7) {
            total = total * 0.90;
        }
        
        // Redondeo al entero más cercano
        return (int) Math.round(total);
    }
}
