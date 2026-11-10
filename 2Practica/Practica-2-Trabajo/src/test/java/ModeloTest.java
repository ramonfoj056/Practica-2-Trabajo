

// 1. Importación estática para assertEquals, assertTrue, assertFalse, etc.
import static org.junit.jupiter.api.Assertions.*;

// 2. Anotaciones de JUnit 5
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// 3. Importación de las clases de tu paquete modelo
import modelo.*;

public class ModeloTest {

    private Cliente cliente;
    private Turismo turismoNormal;
    private Turismo turismoSuperior;
    private Motocicleta motoEspecial;
    private VehiculoIndustrial industrialSuperior;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Ana Lítica", "005");
        turismoNormal = new Turismo("TNA2020", Categoria.NORMAL, 2020, 5, 450.0);
        turismoSuperior = new Turismo("TSA2024", Categoria.SUPERIOR, 2024, 5, 550.0);
        motoEspecial = new Motocicleta("MEA2026", Categoria.ESPECIAL, 2026, 1000);
        industrialSuperior = new VehiculoIndustrial("CSA2025", Categoria.SUPERIOR, 2025, 2400, 3);
    }

    @Test
    public void testCreacionCliente() {
        assertEquals("Ana Lítica", cliente.getNombre());
        assertEquals("005", cliente.getDni());
    }

    @Test
    public void testPrecioBaseTurismo() {
        assertEquals(50, turismoNormal.getPrecioBase());
        assertEquals(100, turismoSuperior.getPrecioBase());
    }

    @Test
    public void testPrecioBaseMotocicleta() {
        assertEquals(50, motoEspecial.getPrecioBase());
    }

    @Test
    public void testPrecioBaseVehiculoIndustrial() {
        assertEquals(140, industrialSuperior.getPrecioBase());
    }

    @Test
    public void testGetTipo() {
        assertEquals("Turismo", turismoNormal.getTipo());
        assertEquals("Motocicleta", motoEspecial.getTipo());
        assertEquals("VehiculoIndustrial", industrialSuperior.getTipo());
    }

    @Test
    public void testDisponibilidadVehiculo() {
        assertTrue(turismoNormal.isDisponible());
        turismoNormal.setDisponible(false);
        assertFalse(turismoNormal.isDisponible());
    }
} 