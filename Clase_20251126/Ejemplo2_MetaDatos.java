package Clase_20251126;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejemplo2_MetaDatos {

    // Ejemplo 2: Usa de DatabaseMetaData para consultar meta información:

    public static void main(String[] args) {

        // 1. Datos de conexión:

        String url = "jdbc:h2:./Clase_20251119/BDJuegos";
        String user = "sa";
        String password = "";

        // 2. Cargamos el driver H2:

        try {
            Class.forName("org.h2.Driver");
            System.out.println("Driver H2 cargado correctamente");

            // 3. Establecemos la conexión:

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con BDJuegos");

            // 4. Obtener el objeto DatabaseMetaData

            DatabaseMetaData dbmd = conn.getMetaData();

            // 5. Consultar información general del SGBD:

            System.out.println("Información general de la base de datos:");
            System.out.println("    - Producto: " + dbmd.getDatabaseProductName());
            System.out.println("    - Versión producto: " + dbmd.getDatabaseProductVersion());
            System.out.println("    - Driver: " + dbmd.getDriverName());
            System.out.println("    - URL: " + dbmd.getURL());
            System.out.println("    - Usuario: " + dbmd.getUserName());

            // 6. Listamos las tablas del catálogo
            // En H2 podemos pasar null para catálogo y esquema y solo filtrar por el tipo TABLE

            System.out.println("\n Tablas existentes en la base de datos:");
            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = null;
            String[] tipos = {"TABLE"};

            ResultSet rsTablas = dbmd.getTables(catalog, schemaPattern, tableNamePattern, tipos);

            while (rsTablas.next()) {
                String nombreTabla = rsTablas.getString("TABLE_NAME");
                String tipoTabla = rsTablas.getString("TABLE_TYPE");
                System.out.println("    - " + nombreTabla + " (" + tipoTabla + ")");
            }

            rsTablas.close();

            // 7. Consultamos columnas de la tabla JUEGO

            System.out.println("\n Columnas de la tabla JUEGO:");

            ResultSet rsColumnas = dbmd.getColumns(catalog, schemaPattern, tableNamePattern, tableNamePattern);
            while (rsColumnas.next()) {
                String nombreCol = rsColumnas.getString("COLUMN_NAME");
                String tipoCol = rsColumnas.getString("TYPE_NAME");
                int tamanyo = rsColumnas.getInt("COLUMN_SIZE");

                System.out.println("    - " + nombreCol + " : " + tipoCol +
                " (" + tamanyo + ")");
            }

            rsColumnas.close();

            // 8. Consultamos las claves primarias de la tabla JUEGO

            System.out.println("\n Clave(s) primaria(s) de la tabla JUEGO:");

            ResultSet rsPK = dbmd.getPrimaryKeys(null, null, "JUEGO");

            while (rsPK.next()) {
                String nombreColPK = rsPK.getString("COLUMN_NAME");
                short keySeq = rsPK.getShort("KEY_SEQ");

                System.out.println("    - Columna: " + nombreColPK + " (orden " + keySeq + ")");
            }    

            rsPK.close();

            conn.close();

            // 9. Cerramos la conexión

            System.out.println("\n Conexión cerrada correctamente");
                
        } catch (ClassNotFoundException e) {
            System.out.println("Driver H2 no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error SQL/Metadatos: " + e.getMessage());
        }
    }

}
