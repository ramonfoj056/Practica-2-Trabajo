package modelo;

import org.json.JSONObject;

// Clase base abstracta que representa un vehículo genérico
public abstract class Vehiculo {

    // Atributos principales del vehículo
    private String matricula;
    private Categoria categoria;
    private int annio;
    private boolean disponible;

    // Constructor estándar (disponible por defecto al crearse)
    public Vehiculo(String matricula, Categoria categoria, int annio) {
        this.matricula = matricula;
        this.categoria = categoria;
        this.annio = annio;
        this.disponible = true;
    }

    // Constructor para deserializar desde una cadena en formato JSON
    public Vehiculo(String jsonString) {
        JSONObject json = new JSONObject(jsonString);
        this.matricula = json.getString("matricula");
        this.categoria = Categoria.valueOf(json.getString("categoria"));
        this.annio = json.getInt("annio");
        this.disponible = json.getBoolean("disponible");
    }

    // Métodos Getters
    public String getMatricula() {
        return matricula;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getAnnio() {
        return annio;
    }

    // Método abstracto que cada subclase debe implementar según sus tarifas
    public abstract int getPrecioBase();

    // Consulta y modificación del estado de disponibilidad
    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Devuelve el nombre de la subclase concreta ("Turismo", "Motocicleta", etc.)
    public String getTipo() {
        return getClass().getSimpleName();
    }

    // Serializa el objeto Vehiculo a una cadena en formato JSON
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}
