package modelo;

import org.json.JSONObject;

// Subclase que representa un turismo (hereda de Vehiculo)
public class Turismo extends Vehiculo {

    // Atributos específicos del turismo
    private int plazas;
    private double capacidadMaletero;

    // Constructor estándar con parámetros
    public Turismo(String matricula, Categoria categoria, int annio,
                   int plazas, double capacidadMaletero) {
        super(matricula, categoria, annio);
        this.plazas = plazas;
        this.capacidadMaletero = capacidadMaletero;
    }

    // Constructor para deserializar desde una cadena en formato JSON
    public Turismo(String jsonString) {
        super(jsonString);
        JSONObject json = new JSONObject(jsonString);
        // Extrae las plazas y la capacidad del maletero del objeto JSON
        this.plazas = json.getInt("plazas");
        this.capacidadMaletero = json.getDouble("capacidadMaletero");
    }

    // Métodos Getters
    public int getPlazas() {
        return plazas;
    }

    public double getCapacidadMaletero() {
        return capacidadMaletero;
    }

    // Devuelve el precio base diario según la categoría del turismo
    @Override
    public int getPrecioBase() {
        int precioBase = 0;
        switch (getCategoria()) {
            case NORMAL:
                precioBase = 50;
                break;
            case ESPECIAL:
                precioBase = 80;
                break;
            case SUPERIOR:
                precioBase = 100;
                break;
        }
        return precioBase;
    }
}
