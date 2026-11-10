import java.time.LocalDate;
import modelo.*;

// Clase principal para probar el funcionamiento del modelo, serialización y estrategias de precio
public class Main {
    public static void main(String[] args) {
        // Creación de objetos de prueba: Vehículo, Cliente y fecha actual
        Vehiculo vehiculo1 = new Turismo("ABC123", Categoria.NORMAL, 2020, 5, 400.0);
        Cliente cliente1 = new Cliente("Juan Pérez", "12345678A");
        LocalDate hoy = LocalDate.now();

        // Creación de un alquiler de 4 días (de hoy a dentro de 3 días)
        Alquiler alquiler1 = new Alquiler(vehiculo1, cliente1, hoy, hoy.plusDays(3));

        // Muestra el objeto Alquiler serializado a formato JSON
        System.out.println("--- Objeto Alquiler (Serializado a JSON) ---");
        System.out.println(alquiler1.toString());

        // Muestra el precio total evaluando cada una de las políticas de precios (Patrón Strategy)
        System.out.println("\n--- Cálculo de precios según política ---");
        System.out.println("Precio Normal: " + alquiler1.calcularPrecioAlquiler(PoliticasDePrecios.Normal) + " €");
        System.out.println("Precio Temporada Alta: " + alquiler1.calcularPrecioAlquiler(PoliticasDePrecios.TemporadaAlta) + " €");
        System.out.println("Precio Ofertón: " + alquiler1.calcularPrecioAlquiler(PoliticasDePrecios.Oferton) + " €");
        System.out.println("Precio Cliente Frecuente: " + alquiler1.calcularPrecioAlquiler(PoliticasDePrecios.ClienteFrecuente) + " €");
    }
}