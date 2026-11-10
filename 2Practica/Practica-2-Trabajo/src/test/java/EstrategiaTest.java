
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estrategia.*;
import modelo.*;

public class EstrategiaTest {

    private Vehiculo coche;
    private LocalDate inicio;

    @BeforeEach
    public void setUp() {
        // Turismo Normal -> Precio base = 50€/día
        coche = new Turismo("TNA2020", Categoria.NORMAL, 2020, 5, 450.0);
        inicio = LocalDate.now();
    }

    @Test
    public void testCalculoPrecioNormal() {
        ICalculadorPrecio calculador = new CalculadorPrecioNormal();
        LocalDate fin = inicio.plusDays(3); // 4 días en total
        int precio = calculador.calcularPrecioAlquiler(inicio, fin, coche);
        
        // 4 días * 50€ = 200€
        assertEquals(200, precio);
    }

    @Test
    public void testCalculoPrecioTemporadaAlta() {
        ICalculadorPrecio calculador = new CalculadorPrecioTemporadaAlta();
        LocalDate fin = inicio.plusDays(3); // 4 días en total
        int precio = calculador.calcularPrecioAlquiler(inicio, fin, coche);
        
        // 4 días * (50€ * 2) = 400€
        assertEquals(400, precio);
    }

    @Test
    public void testCalculoPrecioOfertonHasta7Dias() {
        ICalculadorPrecio calculador = new CalculadorPrecioOferton();
        LocalDate fin = inicio.plusDays(4); // 5 días en total
        int precio = calculador.calcularPrecioAlquiler(inicio, fin, coche);
        
        // 5 días * (50€ * 0.70) = 175€
        assertEquals(175, precio);
    }

    @Test
    public void testCalculoPrecioOfertonMasDe7Dias() {
        ICalculadorPrecio calculador = new CalculadorPrecioOferton();
        LocalDate fin = inicio.plusDays(9); // 10 días en total
        int precio = calculador.calcularPrecioAlquiler(inicio, fin, coche);
        
        // 10 días * 35€ = 350€ -> Con 10% desc. extra = 315€
        assertEquals(315, precio);
    }

    @Test
    public void testCalculoPrecioClienteFrecuente() {
        ICalculadorPrecio calculador = new CalculadorPrecioClienteFrecuente();
        LocalDate fin = inicio.plusDays(4); // 5 días en total
        int precio = calculador.calcularPrecioAlquiler(inicio, fin, coche);
        
        // Primeos 3 días: 3 * (50€ * 0.80) = 120€
        // Días restantes (2): 2 * (50€ * 0.60) = 60€
        // Total = 180€
        assertEquals(180, precio);
    }
} 
