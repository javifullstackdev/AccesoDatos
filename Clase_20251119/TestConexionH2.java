package Clase_20251119;

// Importamos las clases JDBC reales:

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class TestConexionH2 {

    public static void main(String[] args) {
        
        // 1. URL de conexión en modo fichero:
        // Formato general h2 en fichero:
        //      jbdc:h2/NOMBRE crea fichero en la carpeta actual
        String url = "jdbc:h2:./Clase_20251119/BDJuegos";

        // 2. Credenciales por defecto de h2
        String user = "sa";
        String password = "";

        try {

            // 3. Cargar explícitamente el driver H2:
            Class.forName("org.h2.Driver");
            System.out.println("Driver H2 cargado correctamente");

            // 4. Pedimos una conexión real al DriverManager:
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con H2");

            // 5. (Opcional) Realizamos una consulta simple:
            String sql = "SELECT 1 AS resultado";

            // Creamos un statment a partir de la conexión
            //El Statment servirá para enviar la sentencia SQL
            Statement st = conn.createStatement();

            // Ejecutamos la consulta con executeQuery:
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                int valor = rs.getInt("resultado");
                System.out.println("Resultado de prueba: " + valor);
            }

            // 6. Cerramos la conexión:
            rs.close();
            st.close();
            conn.close();
            System.out.println("Conexión cerrada correctamente");

        } catch (ClassNotFoundException e) {
            System.out.println("No se ha encontrado la clase del driver H2: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error SQL al conectar o consultar: " + e.getMessage());
        }
    }

}
