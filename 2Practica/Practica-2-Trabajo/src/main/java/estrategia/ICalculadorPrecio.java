package estrategia;

import java.time.LocalDate;
import modelo.Vehiculo;

// Interfaz para la estrategia de cálculo de precios de alquiler 
public interface ICalculadorPrecio {
    
    // Método que deben implementar todas las estrategias concretas para calcular el precio
    int calcularPrecioAlquiler(LocalDate fechaInicio, LocalDate fechaFin, Vehiculo vehiculo);
}
