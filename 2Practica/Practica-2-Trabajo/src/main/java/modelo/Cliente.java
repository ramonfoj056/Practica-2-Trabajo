package modelo;

import org.json.JSONObject;

// Modelo que representa un cliente del sistema
public class Cliente {
    // Atributos del cliente
    private String nombre;
    private String dni;

    // Constructor estándar con parámetros
    public Cliente(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    // Constructor para deserializar desde una cadena en formato JSON
    public Cliente(String jsonString) {
        JSONObject json = new JSONObject(jsonString);
        // Extrae el nombre y el DNI a partir de sus claves en el JSON
        this.nombre = json.getString("nombre");
        this.dni = json.getString("dni");
    }

    // Métodos Getters
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    // Serializa el objeto Cliente a una cadena en formato JSON
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}
