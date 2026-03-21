package proyecto2_so.Controladores;

public class Archivo {
    private String nombre;
    private String extension;
    private int bloques;
    private int tamanio; 

    public Archivo(String nombre, int bloques) {
        this.nombre = nombre;
        this.extension = "txt"; 
        this.bloques = bloques;
        this.tamanio = bloques * 4; 
    }

    public String getNombre() {
        return nombre;
    }

    public String getExtension() {
        return extension;
    }

    public int getBloques() {
        return bloques;
    }

    public int getTamaño() {
        return tamanio;
    }
    
    public void setNombre(String nombre) {
    this.nombre = nombre;
    }
}