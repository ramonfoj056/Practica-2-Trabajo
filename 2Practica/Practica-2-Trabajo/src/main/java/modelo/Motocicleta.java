package modelo;

import org.json.JSONObject;

// Subclase que representa una motocicleta (hereda de Vehiculo)
public class Motocicleta extends Vehiculo {

    // Atributo específico de la motocicleta
    private int cilindrada;

    // Constructor estándar con parámetros
    public Motocicleta(String matricula, Categoria categoria, int annio, int cilindrada) {
        super(matricula, categoria, annio);
        this.cilindrada = cilindrada;
    }

    // Constructor para deserializar desde una cadena en formato JSON
    public Motocicleta(String jsonString) {
        super(jsonString);
        JSONObject json = new JSONObject(jsonString);
        // Extrae la cilindrada del objeto JSON
        this.cilindrada = json.getInt("cilindrada");
    }

    // Devuelve el precio base diario según la categoría de la motocicleta
    @Override
    public int getPrecioBase() {
        int precioBase = 0;
        switch (getCategoria()) {
            case NORMAL:
                precioBase = 30;
                break;
            case ESPECIAL:
                precioBase = 50;
                break;
            case SUPERIOR:
                precioBase = 70;
                break;
        }
        return precioBase;
    }

    // Método Getter
    public int getCilindrada() {
        return cilindrada;
    }
}
