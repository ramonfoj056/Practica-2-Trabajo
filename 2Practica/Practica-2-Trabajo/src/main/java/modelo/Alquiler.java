package modelo;

import java.time.LocalDate;
import org.json.JSONObject;

import estrategia.ICalculadorPrecio;
import factoria.FactoriaCalculadorPrecio;
import factoria.FactoriaVehiculos;

// Modelo que representa la transacción de alquiler de un vehículo
public class Alquiler {
    // Atributos del alquiler
    private Vehiculo vehiculo;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // Constructor estándar con parámetros
    public Alquiler(Vehiculo vehiculo, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin) {
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Constructor para deserializar desde una cadena en formato JSON
    public Alquiler(String jsonString) {
        JSONObject json = new JSONObject(jsonString);
        // Reconstruye el cliente a partir de su objeto JSON anidado
        this.cliente = new Cliente(json.getJSONObject("cliente").toString());
        // Reconstruye el vehículo utilizando la factoría 
        this.vehiculo = FactoriaVehiculos.getVehiculo(json.getJSONObject("vehiculo").toString());
        // Pone las fechas desde su representación en este formato (YYYY-MM-DD)
        this.fechaInicio = LocalDate.parse(json.getString("fechaInicio"));
        this.fechaFin = LocalDate.parse(json.getString("fechaFin"));
    }

    // Métodos Getters
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    // Calcula el precio del alquiler dependiendo de la factoría y la estrategia seleccionada
    public int calcularPrecioAlquiler(PoliticasDePrecios politica) {
        ICalculadorPrecio calculador = FactoriaCalculadorPrecio.getCalculador(politica);
        return calculador.calcularPrecioAlquiler(fechaInicio, fechaFin, vehiculo);
    }

    // Serializa el objeto Alquiler a una cadena en formato JSON
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}
