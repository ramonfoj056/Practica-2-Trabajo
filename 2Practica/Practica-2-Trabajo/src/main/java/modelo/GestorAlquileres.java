package modelo;

import java.time.LocalDate;

// Clase controladora principal que gestiona los clientes, vehículos y alquileres
public class GestorAlquileres {

    // Atributos para almacenar los registros del sistema
    private int contadorAlquileres = 0;
    private Alquiler[] alquileres = new Alquiler[1000];
    private Cliente[] clientes = new Cliente[10];
    private Vehiculo[] vehiculos = new Vehiculo[12];

    // Constructor que inicializa los datos predeterminados
    public GestorAlquileres() {
        inicializarClientes();
        inicializarVehiculos();
    }

    // Carga la lista inicial de clientes
    private void inicializarClientes() {
        clientes = new Cliente[10];
        clientes[0] = new Cliente("Clara Videncia", "001");
        clientes[1] = new Cliente("Aitor Menta", "002");
        clientes[2] = new Cliente("Lola Mento", "003");
        clientes[3] = new Cliente("Armando Guerra", "004");
        clientes[4] = new Cliente("Ana Lítica", "005");
        clientes[5] = new Cliente("Carmelo Cotón", "006");
    }

    // Carga la flota inicial de vehículos y los marca como disponibles
    private void inicializarVehiculos() {
        vehiculos = new Vehiculo[12];
        vehiculos[0] = new Turismo("TNA2020", Categoria.NORMAL, 2020, 5, 450.0);
        vehiculos[1] = new Motocicleta("MNA2021", Categoria.NORMAL, 2021, 200);
        vehiculos[2] = new VehiculoIndustrial("CNA2019", Categoria.NORMAL, 2019, 1400, 2);
        vehiculos[3] = new Turismo("TSA2024", Categoria.SUPERIOR, 2024, 5, 550.0);
        vehiculos[4] = new Motocicleta("MSA2021", Categoria.SUPERIOR, 2021, 500);
        vehiculos[5] = new VehiculoIndustrial("CSA2025", Categoria.SUPERIOR, 2025, 2400, 3);
        vehiculos[6] = new Turismo("TEA2026", Categoria.ESPECIAL, 2026, 7, 650.0);
        vehiculos[7] = new Motocicleta("MEA2026", Categoria.ESPECIAL, 2026, 1000);
        vehiculos[8] = new VehiculoIndustrial("CEA2025", Categoria.ESPECIAL, 2025, 4400, 5);
        for (int i = 0; i < vehiculos.length; i++) {
            if (vehiculos[i] != null) {
                vehiculos[i].setDisponible(true);
            }
        }
    }

    // Filtra y devuelve los vehículos según su tipo Turismo , Motocicleta, etc.
    public Vehiculo[] obtenerVehiculos(String tipo) {
        Vehiculo[] filtrados = new Vehiculo[vehiculos.length];
        int contador = 0;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo != null && vehiculo.getTipo().equalsIgnoreCase(tipo)) {
                filtrados[contador++] = vehiculo;
            }
        }

        Vehiculo[] resultado = new Vehiculo[contador];
        System.arraycopy(filtrados, 0, resultado, 0, contador);
        return resultado;
    }

    // Filtra los vehículos combinando tipo y categoría
    public Vehiculo[] obtenerVehiculos(String tipo, Categoria categoria) {
        Vehiculo[] filtrados = new Vehiculo[vehiculos.length];
        int contador = 0;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo != null && vehiculo.getTipo().equalsIgnoreCase(tipo) && vehiculo.getCategoria() == categoria) {
                filtrados[contador++] = vehiculo;
            }
        }

        Vehiculo[] resultado = new Vehiculo[contador];
        System.arraycopy(filtrados, 0, resultado, 0, contador);
        return resultado;
    }

    // Registra un nuevo alquiler en el array
    public void agregarAlquiler(Alquiler alquiler) {
        if (contadorAlquileres < alquileres.length) {
            alquileres[contadorAlquileres] = alquiler;
            contadorAlquileres++;
        } else {
            System.out.println("No se pueden agregar más alquileres. Capacidad máxima alcanzada.");
        }
    }

    // Devuelve un array con los alquileres registrados hasta el momento
    public Alquiler[] obtenerAlquileres() {
        Alquiler[] resultado = new Alquiler[contadorAlquileres];
        System.arraycopy(alquileres, 0, resultado, 0, contadorAlquileres);
        return resultado;
    }

    // Devuelve los alquileres asociados a un cliente especifico
    public Alquiler[] obtenerAlquileres(Cliente cliente) {
        Alquiler[] filtrados = new Alquiler[contadorAlquileres];
        int contador = 0;

        for (int i = 0; i < contadorAlquileres; i++) {
            if (alquileres[i] != null && alquileres[i].getCliente().equals(cliente)) {
                filtrados[contador++] = alquileres[i];
            }
        }

        Alquiler[] resultado = new Alquiler[contador];
        System.arraycopy(filtrados, 0, resultado, 0, contador);
        return resultado;
    }

    // Comprueba si un cliente alcanza el número minimo de alquileres para ser considerado frecuente
    private boolean esClienteFrecuente(Cliente cliente, int umbral) {
        return obtenerAlquileres(cliente).length >= umbral;
    }

    // Bucle principal del menú interactivo por consola
    public void menuPrincipal() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nGestión de Alquileres");
            System.out.println("¿Desea salir de la app (S/N)?");

            char s = KeyBoard.readChar();
            if (s == 's' || s == 'S') {
                System.out.println("Bye ...");
                return;
            }

            Cliente cliente = identificarCliente();
            if (cliente != null) {
                System.out.println("Sr " + cliente.getNombre() + " Introduzca una opción");
                System.out.println("(1) seleccionar coche para alquilar");
                System.out.println("(2) entregar un coche alquilado");
                System.out.println("(3) listar su histórico de alquileres");

                int opcion = KeyBoard.readInt();
                System.out.println("opción escogida: " + opcion);

                switch (opcion) {
                    case 1:
                        // Opción 1: Proceso de alquiler de vehículo
                        Vehiculo v = seleccionarVehiculo(cliente);
                        if (v != null && v.isDisponible()) {
                            System.out.println("¿Cuántos días desea alquilar el vehículo?");
                            int dias = KeyBoard.readInt();
                            LocalDate inicio = LocalDate.now();
                            LocalDate fin = inicio.plusDays(dias);

                            Alquiler q = new Alquiler(v, cliente, inicio, fin);
                            agregarAlquiler(q);

                            // Aplica la tarifa según si el usuario es cliente frecuente
                            boolean usuarioFrecuente = esClienteFrecuente(cliente, 3);
                            if (usuarioFrecuente) {
                                System.out.println("Alquiler realizado. Tarifa Usuario frecuente.");
                                int precio = q.calcularPrecioAlquiler(PoliticasDePrecios.ClienteFrecuente);
                                System.out.println("Precio total del alquiler: " + precio + " €");
                                v.setDisponible(false);
                            } else {
                                System.out.println("Alquiler realizado. Tarifa Normal.");
                                int precio = q.calcularPrecioAlquiler(PoliticasDePrecios.Normal);
                                System.out.println("Precio total del alquiler: " + precio + " €");
                                v.setDisponible(false);
                            }
                        } else {
                            System.out.println("Debe seleccionar un vehículo disponible.");
                        }
                        break;

                    case 2:
                        // Opción 2: Devolución de un vehículo alquilado
                        Alquiler[] misAlquileres = obtenerAlquileres(cliente);
                        int pendientes = 0;

                        System.out.println("Vehículos actualmente en su poder:");
                        for (int i = 0; i < misAlquileres.length; i++) {
                            if (misAlquileres[i] != null && !misAlquileres[i].getVehiculo().isDisponible()) {
                                System.out.println(i + " ---> " + misAlquileres[i].getVehiculo());
                                pendientes++;
                            }
                        }

                        if (pendientes == 0) {
                            System.out.println("No tiene ningún vehículo alquilado pendiente de entrega.");
                        } else {
                            System.out.println("Seleccione el número correspondiente al alquiler del vehículo que desea entregar:");
                            int sel = KeyBoard.readInt();
                            if (sel >= 0 && sel < misAlquileres.length && misAlquileres[sel] != null && !misAlquileres[sel].getVehiculo().isDisponible()) {
                                misAlquileres[sel].getVehiculo().setDisponible(true);
                                System.out.println("Vehículo " + misAlquileres[sel].getVehiculo().getMatricula() + " devuelto con éxito.");
                            } else {
                                System.out.println("Selección inválida.");
                            }
                        }
                        break;

                    case 3:
                        // Opción 3: Muestra el historial de alquileres del cliente
                        Alquiler[] alqs = obtenerAlquileres(cliente);
                        if (alqs.length == 0) {
                            System.out.println("No tiene alquileres registrados.");
                        } else {
                            for (Alquiler q : alqs) {
                                System.out.println(q);
                            }
                        }
                        break;

                    default:
                        break;
                }
            } else {
                System.out.println("Debe identificarse correctamente o salir de la app");
            }
        }
    }

    // Muestra y selecciona un cliente registrado por su índice
    public Cliente identificarCliente() {
        System.out.println("Bienvenido al Gestor de Alquileres");
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                System.out.println(i + " ---> " + clientes[i].getNombre() + " - " + clientes[i].getDni());
            }
        }

        System.out.println("Identifíquese como cliente con su número de cliente (-1 para salir):");
        int numeroCliente = KeyBoard.readInt();

        if (numeroCliente == -1) {
            return null;
        }
        if (numeroCliente < 0 || numeroCliente >= clientes.length || clientes[numeroCliente] == null) {
            System.out.println("identificarCliente: Número de cliente inválido.");
            return null;
        }
        return clientes[numeroCliente];
    }

    // Permite al usuario filtrar e interactuar para elegir un vehículo disponible
    public Vehiculo seleccionarVehiculo(Cliente cliente) {
        boolean seleccionado = false;
        Vehiculo vehiculo = null;

        while (!seleccionado) {
            System.out.println("Estimado Sr./Sra. " + cliente.getNombre());
            System.out.println("Introduzca el número correspondiente al tipo de vehículo que desea alquilar:");
            System.out.println("1. Turismo, 2. Motocicleta, 3. Vehículo Industrial: ");

            int tipoVehiculo = KeyBoard.readInt();
            String tipo = "";
            Vehiculo[] elegibles;

            switch (tipoVehiculo) {
                case 1:
                    System.out.println("Ha seleccionado Turismo.");
                    tipo = "Turismo";
                    break;
                case 2:
                    System.out.println("Ha seleccionado Motocicleta.");
                    tipo = "Motocicleta";
                    break;
                case 3:
                    System.out.println("Ha seleccionado Vehículo Industrial.");
                    tipo = "VehículoIndustrial";
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione un tipo de vehículo válido.");
                    continue;
            }

            System.out.println("Estimado Sr./Sra. " + cliente.getNombre() + "\nSeleccione categoría.");
            System.out.println("Introduzca el número correspondiente a la categoría que desea alquilar:");
            System.out.println("1. Normal, 2. Superior, 3. Especial, 4. Cualquiera: ");

            int categoriaVehiculo = KeyBoard.readInt();
            switch (categoriaVehiculo) {
                case 1:
                    System.out.println("Ha seleccionado Categoría Normal.");
                    elegibles = obtenerVehiculos(tipo, Categoria.NORMAL);
                    break;
                case 2:
                    System.out.println("Ha seleccionado Categoría Superior.");
                    elegibles = obtenerVehiculos(tipo, Categoria.SUPERIOR);
                    break;
                case 3:
                    System.out.println("Ha seleccionado Categoría Especial.");
                    elegibles = obtenerVehiculos(tipo, Categoria.ESPECIAL);
                    break;
                case 4:
                default:
                    System.out.println("Ha seleccionado Cualquier Categoría.");
                    elegibles = obtenerVehiculos(tipo);
                    break;
            }

            if (elegibles.length == 0) {
                System.out.println("No hay vehículos disponibles con los criterios seleccionados.");
                return null;
            }

            System.out.println("Coches elegibles:");
            for (int i = 0; i < elegibles.length; i++) {
                System.out.println(i + " ---> " + elegibles[i] + (elegibles[i].isDisponible() ? " (Disponible)" : " (No disponible)"));
            }

            System.out.println("Introduzca número coche (sólo si está disponible):");
            int seleccion = KeyBoard.readInt();
            if (seleccion >= 0 && seleccion < elegibles.length) {
                vehiculo = elegibles[seleccion];
                if (vehiculo != null && vehiculo.isDisponible()) {
                    seleccionado = true;
                } else {
                    System.out.println("El vehículo seleccionado no está disponible.");
                }
            } else {
                System.out.println("Selección de vehículo fuera de rango.");
            }
        }
        return vehiculo;
    }

    // Imprime la lista completa de alquileres
    public void listarAlquileres() {
        System.out.println("Listado de alquileres");
        for (int i = 0; i < contadorAlquileres; i++) {
            if (alquileres[i] != null) {
                System.out.println(alquileres[i]);
            }
        }
        System.out.println();
    }

    // Imprime la lista completa de vehículos
    public void listarVehiculos() {
        System.out.println("Listado de vehículos");
        for (Vehiculo veh : vehiculos) {
            if (veh != null) {
                System.out.println(veh);
            }
        }
        System.out.println();
    }

    // Método de entrada que inicia la aplicación
    public static void main(String[] args) {
        GestorAlquileres app = new GestorAlquileres();
        app.menuPrincipal();
    }
}
