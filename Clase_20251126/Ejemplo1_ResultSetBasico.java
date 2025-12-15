package Clase_20251126;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

// Ejemplo 1: Lectura de datos usando ResulSet:
// Relacionado con 4.1: Objeto ResulSet

public class Ejemplo1_ResultSetBasico {

    public static void main(String[] args) {

        // Datos de mi conexión:
        String url = "jdbc:h2:./Clase_20251119/BDJuegos";
        String user = "sa";
        String password = "";

        // 2. Cargamos el driver H2:

        try {
            Class.forName("org.h2.Driver");
            System.out.println("Driver H2 cargado correctamente");

            // 3. Establecemos la conexión con la BD (url + credenciales)
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con BDJuegos");

            // 4. Creamos el Statment para ejecutar una consulta:
            // El Statment es el canal por donde enviamos el SQL al motor
            Statement st = conn.createStatement();

            // 5. Definimos la sentencia de SQL:
            String sql = "SELECT ID, NOMBRE, GENERO, PUNTUACIÓN FROM JUEGO";

            // 6. Ejecutamos la consulta y obtenemos el ResultSet
            // El ResultSet es una "tabla" que nos va a devolver la BD
            // con tantas filas como registros tenga
            ResultSet rs = st.executeQuery(sql);


            // 7. Recorremos el ResultSet con rs.next()
            System.out.println("Lista de juegos (usando ResultSet):");

            while (rs.next()) {

                // 7.1. Leer columnas con el get...()

                int id = rs.getInt("ID");
                String nombre = rs.getString("NOMBRE");
                String genero = rs.getString("GENERO");
                double puntuacion = rs.getDouble("PUNTUACION");

                // 7.2 Mostramos las filas

                System.out.println(" - [" + id + "] " + nombre +
                    " (" + genero + ") -> " + puntuacion);
    
            }

            // 8. Cerramos recursos en orden inverso de apertura:

            rs.close();
            st.close();
            conn.close();
            System.out.println("Conexión cerrada correctamente");

            // Error al cargar el driver:
        } catch (ClassNotFoundException e) {
            System.out.println("Driver H2 no encontrado: " + e.getMessage());

            // Error con SQL (url errónea...)
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        
    }

}
