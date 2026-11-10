
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import factoria.*;
import modelo.*;
import estrategia.*;

public class FactoriaTest {

    @Test
    public void testFactoriaVehiculosTurismoJSON() {
        Turismo tOriginal = new Turismo("TNA2020", Categoria.NORMAL, 2020, 5, 450.0);
        String jsonString = tOriginal.toString();

        Vehiculo vReconstruido = FactoriaVehiculos.getVehiculo(jsonString);

        assertNotNull(vReconstruido);
        assertTrue(vReconstruido instanceof Turismo);
        assertEquals("TNA2020", vReconstruido.getMatricula());
        assertEquals(Categoria.NORMAL, vReconstruido.getCategoria());
        assertEquals(50, vReconstruido.getPrecioBase());
    }

    @Test
    public void testFactoriaVehiculosMotocicletaJSON() {
        Motocicleta mOriginal = new Motocicleta("MNA2021", Categoria.SUPERIOR, 2021, 500);
        String jsonString = mOriginal.toString();

        Vehiculo vReconstruido = FactoriaVehiculos.getVehiculo(jsonString);

        assertNotNull(vReconstruido);
        assertTrue(vReconstruido instanceof Motocicleta);
        assertEquals("MNA2021", vReconstruido.getMatricula());
        assertEquals(500, ((Motocicleta) vReconstruido).getCilindrada());
    }

    @Test
    public void testFactoriaCalculadorPrecio() {
        ICalculadorPrecio calcNormal = FactoriaCalculadorPrecio.getCalculador(PoliticasDePrecios.Normal);
        ICalculadorPrecio calcAlta = FactoriaCalculadorPrecio.getCalculador(PoliticasDePrecios.TemporadaAlta);

        assertTrue(calcNormal instanceof CalculadorPrecioNormal);
        assertTrue(calcAlta instanceof CalculadorPrecioTemporadaAlta);
    }
} 
