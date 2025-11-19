## TEMA 1
1. Sistema de ficheros

Como funciona la jerarquía de carpetas, rutas absolutas y relativas
Clases de Java para trabajar con el sistema de ficheros (Files, Path)

### Conceptos básicos

- Fichero
Es un contenedor de información (texto, binario, imagen...)

- Directorio
Es un contenedor de ficheros

- Ruta absoluta
Comienza en la raíz del sistema
C:\Users\elchi\OneDrive\Escritorio\DAM\2º

- Ruta relativa
Parte desde el directorio de ejecución del programa


### Clase FILE en JAVA

Constructores principales
```java
File f1 = new file("C:\Users\elchi\OneDrive\Escritorio\DAM\2º\\clase_20251022.md"); // ruta absoluta
File f1 = new file(parent,"clase_20251022.md"); // ruta relativa
```

Métodos importantes
- exists()
- isFile()
- isDirectory()
- canRead(), canWrite()
- length()
- delete()
- renameTo(File)

Ejemplo 1. Comprobar si existe un fichero (InfoFichero)

2. Ficheros de texto

Dos tipos de niveles:
- Carácter a carácter -> FileReader / FileWriter
- Línea a línea -> BufferedReader / BufferedWriter