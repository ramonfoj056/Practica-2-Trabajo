package factoria;

import org.json.JSONObject;
import modelo.*;

// Factoría para deserializar y crear instancias de Vehiculo según su JSON 
public class FactoriaVehiculos {

    // Parsea el JSON y devuelve la subclase concreta de Vehiculo correspondiente
    public static Vehiculo getVehiculo(String jsonString) {
        // Convierte la cadena en texto a un objeto JSONObject
        JSONObject json = new JSONObject(jsonString);
        
        // Extrae el valor del atributo "tipo" ("Turismo", "Motocicleta", "VehiculoIndustrial")
        String clase = json.getString("tipo");
        
        // Instancia la subclase correspondiente usando su constructor desde JSON
        switch (clase) {
            case "Turismo":
                return new Turismo(jsonString);
            case "Motocicleta":
                return new Motocicleta(jsonString);
            case "VehiculoIndustrial":
                return new VehiculoIndustrial(jsonString);
            default:
                // Lanza excepción si el tipo de vehículo no está en el catalogo
                throw new IllegalArgumentException("Tipo de vehículo desconocido: " + clase);
        }
    }
}