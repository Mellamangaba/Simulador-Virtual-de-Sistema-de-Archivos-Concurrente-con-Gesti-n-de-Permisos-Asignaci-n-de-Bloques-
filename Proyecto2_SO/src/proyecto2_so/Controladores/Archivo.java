package proyecto2_so.Controladores;
import java.util.ArrayList;

public class Archivo {
    private String nombre;
    private String extension;
    private int bloques;
    private int tamanio; 
    private ArrayList<Integer> bloquesAsignados; 

    public Archivo(String nombre, int bloques) {
        this.nombre = nombre;
        this.extension = "txt"; 
        this.bloques = bloques;
        this.tamanio = bloques * 4; 
        this.bloquesAsignados = new ArrayList<>(); 
    }

    public String getNombre() { return nombre; }
    public String getExtension() { return extension; }
    public int getBloques() { return bloques; }
    public int getTamaño() { return tamanio; }
    
    public ArrayList<Integer> getBloquesAsignados() {
        return bloquesAsignados;
    }
    
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }
}