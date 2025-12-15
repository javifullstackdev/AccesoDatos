import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tarea_04 {

    private static final String URL  = "jdbc:h2:./Clase_20251119/BDJuegos"; 
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void main(String[] args) {

        try {
            Class.forName("org.h2.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                
                // Primero, defino las variables que voy a usar en las consultas:
                String nombreJuego = "World of Warcraft";
                String genero = "MMORPG";
                double notaInicial = 9.5;
                double notaFinal = 9.9;

                // 1. Estado inicial:
                System.out.println("1. Estado inicial de la tabla:");
                mostrarJuegos(conn);

                // 2. Insertamos un juego:
                System.out.println("\n2. Insertando nuevo juego: " + nombreJuego + " (" + genero + ") -> " + notaInicial);
                insertarJuego(conn, nombreJuego, genero, notaInicial);

                // 3. Comprobamos que se haya insertado correctamente:
                System.out.println("\n3. Comprobando inserción...");
                mostrarJuegos(conn);

                // 4. Actualizamos la puntuación del juego:
                System.out.println("\n4. Actualizando puntuación de " + nombreJuego + "...");
                actualizarPuntuacion(conn, nombreJuego, notaFinal);

                // Mostramos la actualización de la puntuación:
                System.out.println("    - Nueva puntuación de " + nombreJuego + ": " + notaFinal);

                // 5. Comprobamos cómo ha quedado la lista tras la actualización:
                System.out.println("\n5. Comprobando lista actualizada...");
                mostrarJuegos(conn);

                // 6. Eliminamos el juego:
                boolean eliminado = eliminarJuego(conn, nombreJuego);
                if (eliminado) {
                    System.out.println("\n6. El juego " + nombreJuego + " ha sido eliminado correctamente");
                } else {
                    System.out.println("\n6. No se encontró el juego " + nombreJuego + " para eliminar");
                }

                // 7. Mostramos cómo ha quedado la lista tras eliminar el juego:
                System.out.println("\n7. Estado final de la tabla:");
                mostrarJuegos(conn);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver H2 no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // Método para consultar y mostrar los registros de la tabla JUEGO:
    private static void mostrarJuegos(Connection conn) throws SQLException {
        String sql = "SELECT ID, NOMBRE, GENERO, PUNTUACION FROM JUEGO";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // Recorro el cursor fila a fila mientras haya resultados:
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("NOMBRE");
                String genero = rs.getString("GENERO");
                double puntuacion = rs.getDouble("PUNTUACION");

                System.out.println("   [" + id + "] " + nombre +
                                   " (" + genero + ") -> " + puntuacion);
            }
        }
    }

    // Método para añadir un nuevo juego a la base de datos:
    private static void insertarJuego(Connection conn, String nombre, String genero, double nota) throws SQLException {
        String sql = "INSERT INTO JUEGO (NOMBRE, GENERO, PUNTUACION) VALUES (?, ?, ?)";

        // Uso PreparedStatment para pasar los parámetros de forma segura:
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setString(2, genero);
            pst.setDouble(3, nota);

            // Ejecutamos la inserción:
            pst.executeUpdate();
        }
    }

    // Método para modificar la nota de un juego exisitente buscándolo por nombre:
    private static void actualizarPuntuacion(Connection conn, String nombre, double nuevaNota) throws SQLException {
        String sql = "UPDATE JUEGO SET PUNTUACION = ? WHERE NOMBRE = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setDouble(1, nuevaNota);
            pst.setString(2, nombre);
            pst.executeUpdate();
        }
    }

    /* 
        Este método lo hago boolean para poder dar una respuesta diferente en función de si se borra
        o no el juego en cuestión, aunque en este ejercicio dado que uso la variable "nombreJuego" en nombre,
        sí o sí, se va a borrar
    */

    private static boolean eliminarJuego(Connection conn, String nombre) throws SQLException {

        String sql = "DELETE FROM JUEGO WHERE NOMBRE = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nombre);

            int filasAfectadas = pst.executeUpdate();
            
            // Devolvemos true si se borró al menos una fila
            return filasAfectadas > 0;
        }
    }
}
