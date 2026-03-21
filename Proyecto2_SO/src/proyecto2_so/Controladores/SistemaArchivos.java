package proyecto2_so.Controladores;

public class SistemaArchivos {
    

    private boolean[] mapaBits;
    private java.util.ArrayList<Archivo> listaArchivos;

   
    public SistemaArchivos() {
        listaArchivos = new java.util.ArrayList<>();
        mapaBits = new boolean[100];
        
        
        for (int i = 0; i < 100; i++) {
            mapaBits[i] = true;
        }
    }

    public boolean[] getMapaBits() {
        return mapaBits;
    }
    public boolean crearArchivo(String nombre, int cantidadBloques) {
        
        int bloquesLibres = 0;
        for (int i = 0; i < 100; i++) {
            if (mapaBits[i]) { 
                bloquesLibres++;
            }
        }

        if (bloquesLibres < cantidadBloques) {
            return false; 
        }

        int bloquesAsignados = 0;
        for (int i = 0; i < 100 && bloquesAsignados < cantidadBloques; i++) {
            if (mapaBits[i]) {
                mapaBits[i] = false; 
                bloquesAsignados++;
            }
        }
        Archivo nuevoArchivo = new Archivo(nombre, "txt", cantidadBloques * 1024, cantidadBloques);
        listaArchivos.add(nuevoArchivo);
        return true; 
    }
    public java.util.ArrayList<Archivo> getListaArchivos() {
        return listaArchivos;
    }
}