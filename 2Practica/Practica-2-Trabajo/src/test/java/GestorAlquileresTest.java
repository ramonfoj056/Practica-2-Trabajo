

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Importación de los paquetes de la aplicación

import modelo.*;

public class GestorAlquileresTest {

    private GestorAlquileres gestor;
    private Cliente clienteAna;
    private Cliente clienteSinAlquileres;

    @BeforeEach
    public void setUp() {
        // Inicializa el gestor con sus vehículos y clientes predeterminados
        gestor = new GestorAlquileres();
        clienteAna = new Cliente("Ana Lítica", "005");
        clienteSinAlquileres = new Cliente("Cliente Nuevo", "999");
    }

    @Test
    public void testObtenerVehiculosPorTipo() {
        Vehiculo[] turismos = gestor.obtenerVehiculos("Turismo");
        assertNotNull(turismos, "El array devuelto no debe ser null");
        assertTrue(turismos.length > 0, "Debe haber al menos un turismo en la lista precargada");

        for (Vehiculo v : turismos) {
            assertEquals("Turismo", v.getTipo(), "Cada vehículo devuelto debe ser de tipo Turismo");
        }
    }

    @Test
    public void testObtenerVehiculosPorTipoYCategoria() {
        Vehiculo[] turismosNormales = gestor.obtenerVehiculos("Turismo", Categoria.NORMAL);
        assertNotNull(turismosNormales);
        assertTrue(turismosNormales.length > 0, "Debe haber turismos de categoría NORMAL");

        for (Vehiculo v : turismosNormales) {
            assertEquals("Turismo", v.getTipo());
            assertEquals(Categoria.NORMAL, v.getCategoria());
        }
    }

    @Test
    public void testAgregarYObtenerAlquileresCliente() {
        Vehiculo[] turismos = gestor.obtenerVehiculos("Turismo");
        assertTrue(turismos.length > 0);
        Vehiculo coche = turismos[0];

        LocalDate inicio = LocalDate.now();
        LocalDate fin = inicio.plusDays(3);

        Alquiler alquiler = new Alquiler(coche, clienteAna, inicio, fin);
        gestor.agregarAlquiler(alquiler);

        Alquiler[] alquileresCliente = gestor.obtenerAlquileres(clienteAna);
        assertNotNull(alquileresCliente);
        assertEquals(1, alquileresCliente.length, "El cliente debe tener exactamente 1 alquiler registrado");
        assertEquals(clienteAna.getDni(), alquileresCliente[0].getCliente().getDni());
    }

    @Test
    public void testHistorialClienteSinAlquileres() {
        Alquiler[] alquileres = gestor.obtenerAlquileres(clienteSinAlquileres);
        assertNotNull(alquileres, "No debe retornar null para un cliente sin registros");
        assertEquals(0, alquileres.length, "El array de alquileres debe tener longitud 0");
    }

    @Test
    public void testGestionDisponibilidadVehiculo() {
        Vehiculo[] motos = gestor.obtenerVehiculos("Motocicleta");
        assertTrue(motos.length > 0);
        Vehiculo moto = motos[0];

        assertTrue(moto.isDisponible(), "El vehículo debe estar disponible inicialmente");

        moto.setDisponible(false);
        assertFalse(moto.isDisponible(), "El vehículo debe marcarse como no disponible tras alquilarlo");

        moto.setDisponible(true);
        assertTrue(moto.isDisponible(), "El vehículo debe volver a estar disponible tras su entrega");
    }
} 
