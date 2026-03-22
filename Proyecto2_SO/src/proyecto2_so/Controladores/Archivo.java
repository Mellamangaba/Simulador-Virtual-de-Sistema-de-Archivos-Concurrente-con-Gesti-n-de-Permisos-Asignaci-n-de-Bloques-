package proyecto2_so.Controladores;

public class Archivo {
    private String nombre;
    private String extension;
    private int bloques;
    private int tamanio; 
    private int bloqueInicio;

    public Archivo(String nombre, int bloques) {
        this.nombre = nombre;
        if (nombre != null && nombre.contains(".")) {
    // Sacamos solo lo que está después del punto
    this.extension = nombre.substring(nombre.lastIndexOf(".") + 1); 
} else {
    // Si no pusiste punto, le dejamos "txt" por defecto
    this.extension = "txt";
}
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
    public int getBloqueInicio() {
    return bloqueInicio; 
}
    
}