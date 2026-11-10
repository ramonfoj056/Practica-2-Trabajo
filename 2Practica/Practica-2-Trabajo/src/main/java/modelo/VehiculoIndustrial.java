package modelo;

import org.json.JSONObject;

// Subclase que representa un vehículo industrial (hereda de Vehiculo)
public class VehiculoIndustrial extends Vehiculo {

    // Atributos específicos del vehículo industrial
    private int capacidadCarga;
    private int numeroEjes;

    // Constructor estándar con parametros
    public VehiculoIndustrial(String matricula, Categoria categoria, int annio,
                             int capacidadCarga, int numeroEjes) {
        super(matricula, categoria, annio);
        this.capacidadCarga = capacidadCarga;
        this.numeroEjes = numeroEjes;
    }

    // Constructor para deserializar  desde una cadena en formato JSON
    public VehiculoIndustrial(String jsonString) {
        super(jsonString);
        JSONObject json = new JSONObject(jsonString);
        // Extrae la capacidad de carga y el número de ejes del objeto JSON
        this.capacidadCarga = json.getInt("capacidadDeCarga");
        this.numeroEjes = json.getInt("numeroEjes");
    }

    // Métodos Getters
    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public int getNumeroEjes() {
        return numeroEjes;
    }

    // Devuelve el precio base diario según la categoría del vehículo industrial
    @Override
    public int getPrecioBase() {
        int precioBase = 0;
        switch (getCategoria()) {
            case NORMAL:
                precioBase = 70;
                break;
            case ESPECIAL:
                precioBase = 100;
                break;
            case SUPERIOR:
                precioBase = 140;
                break;
        }
        return precioBase;
    }
}
