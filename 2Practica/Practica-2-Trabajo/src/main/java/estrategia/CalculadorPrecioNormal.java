package estrategia;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import modelo.Vehiculo;

// Estrategia de precio Normal 
public class CalculadorPrecioNormal implements ICalculadorPrecio {

    @Override
    public int calcularPrecioAlquiler(LocalDate fechaInicio, LocalDate fechaFin, Vehiculo vehiculo) {
        // Días totales de alquiler (incluye el día de inicio y de fin)
        int dias = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
        
        // Cálculo del precio total (días multiplicados por el precio base diario, sin descuentos)
        return dias * vehiculo.getPrecioBase();
    }
}
