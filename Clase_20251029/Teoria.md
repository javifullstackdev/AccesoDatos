## 3. Ficheros binarios. Ficheros de objetos

### Ficheros binarios vs ficheros de texto
Ventajas del formato binario:
- Mayor rapidez y eficacia
- Mayor precisión (se conservan los tipos int, double...)
- Permite acceso directo a las posiciones del fichero sin tener que leerlo de nuevo entero
- Ofrece cierta protección, ya que no es fácilmente legible

### Flujos base: FileInputStream / FileOutputStream
- FileInputStream: Lee bytes de un fichero
- FileOutputStream: escribe bytes en un fichero

Métodos principales:
- read() -> lee un byte (valor 0-255). Devuelve -1 si llega al final del fichero
- write(int b) -> escribe un byte en el fichero

### Decoradores para tipos: DataInputStream / DataOutputStream
Permite trabajar por tipo de dato, en lugar de byte a byte

Métodos más usados:
- WriteInt(int n) -> guardar un número entero
- WriteDouble(double n) -> guardar un número decimal
- WriteUTF(String texto) -> guardar la longitud más el texto

- readInt() -> leer un entero
- readDouble() -> leer un decimal
- readUTF() -> leer una cadena guardada

Importante el orden de escritura y lectura, debe de ser el mismo

```java
DataOutputStream n = new DataOutputStream(new FileOutputStream("ejemplo.bin"));
n.writeInt(10);
m.writeUTF("Cadena");
n.writeDouble(3,14);
n.close();
```

### Ficheros de objetos y serialización
Cuando queremos guardar objetos completos (no solo datos sueltos)

- ObjectoutputStream -> para escribir objetos
- ObjectoInputStream -> para leer objetos

Serialización: significa convertir un objeto en una secuencia de bytes para guardarlo o enviarlo

```java
class Alumno implements Serializable {
    private String nombre;
    private int edad;
    private double nota;
}
```

```java
ObjectOutputStream n = new ObjectOutputStream(new FileOutputStream("ejemplo.bin"));
n.writeObject(listaDeAlumnos);
n.close;
```

```java
ObjectInputputStream n = new ObjectInputStream(new FileOutputStream("ejemplo.bin"));
Lista<Alumno> leido = (List<Alumno>) n.readObject();
n.close;
```