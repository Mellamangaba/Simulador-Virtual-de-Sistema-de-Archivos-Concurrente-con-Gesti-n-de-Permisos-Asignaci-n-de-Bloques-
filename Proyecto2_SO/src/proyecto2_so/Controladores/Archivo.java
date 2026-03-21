package proyecto2_so.Controladores;

public class Archivo {
    private String nombre;
    private String extension;
    private int tamaño;  // En bytes o KB
    private int bloques; // Cuántos cuadritos ocupa en el disco

    // Constructor: cómo se crea el archivo
    public Archivo(String nombre, String extension, int tamaño, int bloques) {
        this.nombre = nombre;
        this.extension = extension;
        this.tamaño = tamaño;
        this.bloques = bloques;
    }

    // Métodos para obtener la información (Getters)
    public String getNombre() { return nombre; }
    public String getExtension() { return extension; }
    public int getTamaño() { return tamaño; }
    public int getBloques() { return bloques; }
}
