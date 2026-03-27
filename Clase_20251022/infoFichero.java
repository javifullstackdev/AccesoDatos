import java.io.File;

public class infoFichero {

    public static void main(String[] args) {

        String ruta = "C:\\Users\\elchi\\OneDrive\\Escritorio\\DAM\\segundoCurso\\accesoDatos\\texto.txt";

        File f = new File(ruta);

        if (f.exists()) {
            System.out.println("El fichero existe");
            
            if(f.isFile()) {
                System.out.println("Es un fichero");
                System.out.println("Nombre: " + f.getName());
                System.out.println("Ruta absoluta: " + f.getAbsolutePath());
                System.out.println("Tamaño: " + f.length() + " bytes");

            } else if (f.isDirectory()) {
                System.out.println("Es un directorio");
                String[] contenido = f.list();
                for (String nombre : contenido) {
                    System.out.println(" - " + nombre);
                }
            }
        } else {
            System.out.println("El fichero o directoriono existe");
        }
    }
    
}
