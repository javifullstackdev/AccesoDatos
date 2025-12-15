package Clase_20251119;

import java.util.ArrayList;
import java.util.List;

public class SimulacionConexionJDBC {


    // Paso 1. Crear la clases "Fake" que simulan JDBC

    // Clase que simula la conexión a una base de datos:

    static class FakeConnection {

        // Guardamos la url del JDBC que nos pasan:
        private String url;

        // Constructor que recibe y guarda la url:
        public FakeConnection (String url) {
            this.url = url;
        }

        // Método que simula abrir la conexión:
        public void open() {
            System.out.println("[FakeConnection] Abriendo conexión a: " + url);
        }

        // Método que simula cerrar la conexión:
        public void close() {
            System.out.println("[FakeConnection] Cerrando conexión");
        }

    }

    // Clase que simula el DriverManager de JDBC
    static class FakeDriverManager {

        // Lista estática con los "drivers" registrados:
        private static List<String> driversRegistrados = new ArrayList<>();

        // Método que simula registrar un driver JDBC
        public static void registerDriver (String driverClassName) {

            // Añadimos el nombre del driver a la lista:
            driversRegistrados.add(driverClassName);
            System.out.println("[FakeDriverManager] Driver registrado: " + driverClassName);
        }

        // Simula el método getConnection (String url) de JDBC real:
        public static FakeConnection getConnection (String url) {

            // Si no hay drivers registrados, mostramos un aviso:
            if (driversRegistrados.isEmpty()) {
                System.out.println("[FakeDriverManager] No hay drivers JDBC registrados");
                return null;
            }

            // En un JDBC real, aquí se comprobaría que driver sabe manejar:
            System.out.println("[FakeDriverManager] Buscando driver compatible para: " + url);

            // Devolvemos una FakeConnection con esa url:
            return new FakeConnection(url);
        }
    }

    // Paso 2: Programa principal (main)
    public static void main(String[] args) {

        System.out.println("*** SIMULACIÓN DE CONEXIÓN JDBC (SIN BD REAL) ***");

        // Paso 2.1: Cargar el driver JDBC:

        // En un programa real sería: Class.forName("com.mysql.cj.jdbc.Driver");
        // Aquí solo guardamos el String en la lista de drivers
        String driverMySQL = "com.mysql.cj.jdbc.Driver";
        FakeDriverManager.registerDriver(driverMySQL);

        // Paso 2.2: Definimos los parámetros:

        // Host donde está la BD:
        String host = "localhost";

        // Puerto por defecto de MySQL:
        String port = "3306";

        // Nombre de la base de datos a la que queremos conectarnos:    
        String dbName = "BDJuegos";

        // Usuario y contraseña de la base de datos:
        String user = "root";
        String password = "toor";

        // Parámetros opcionales:
        String extraParams = "useUniCode=true&characterEncoding=UTF-8";

        // Paso 2.3: Construir la URL JDBC:

        // Forma general: 
        String connectionUrl =
            "jdbc:mysql://" + host + ":" + port + "/" + dbName +
            "?user=" + user +
            "&password=" + password +
            "&" + extraParams;
        
        System.out.println("URL de conexión generada");
        System.out.println("      " + connectionUrl);

        // Paso 2.4: Solicitar una conexión:

        FakeConnection conn = FakeDriverManager.getConnection(connectionUrl);

        // Si hemos obtenido una conexión, la usamos:
        if (conn != null) {
            conn.open();

            System.out.println("(Simulación) Aquí ejecutaríamos sentencias SQL...");

            conn.close();
        }

        System.out.println("Fin de la simulación de la conexión JDBC");
        
    }
    
}
