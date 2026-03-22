package proyecto2_so.Controladores;
import java.util.ArrayList;

public class Archivo {
    private String nombre;
    private String extension;
    private int bloques;
    private int tamanio; 
    private ArrayList<Integer> bloquesAsignados; 
    
    // --- ¡NUEVAS VARIABLES PARA LAS CARPETAS! ---
    private boolean esDirectorio;
    private String padre;

    // Constructor 1: Para crear un ARCHIVO normal
    public Archivo(String nombre, int bloques, String padre) {
        this.nombre = nombre;
        this.extension = "txt"; 
        this.bloques = bloques;
        this.tamanio = bloques * 4; 
        this.bloquesAsignados = new ArrayList<>(); 
        this.esDirectorio = false; // No es carpeta
        this.padre = padre; // La carpeta donde se guardó
    }

    // Constructor 2: Para crear una CARPETA
    public Archivo(String nombre, String padre) {
        this.nombre = nombre;
        this.extension = ""; // Las carpetas no tienen extensión
        this.bloques = 0; // Las carpetas no ocupan cuadritos en el disco
        this.tamanio = 0;
        this.bloquesAsignados = new ArrayList<>();
        this.esDirectorio = true; // ¡Sí es carpeta!
        this.padre = padre;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getExtension() { return extension; }
    public int getBloques() { return bloques; }
    public int getTamaño() { return tamanio; }
    public ArrayList<Integer> getBloquesAsignados() { return bloquesAsignados; }
    public void setNombre(String nuevoNombre) { this.nombre = nuevoNombre; }
    public boolean isEsDirectorio() { return esDirectorio; }
    public String getPadre() { return padre; }
}