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
        proyecto2_so.Controladores.Archivo nuevoArchivo = new proyecto2_so.Controladores.Archivo(nombre, cantidadBloques);        listaArchivos.add(nuevoArchivo);
        return true; 
    }
    public java.util.ArrayList<Archivo> getListaArchivos() {
        return listaArchivos;
    }
    public boolean eliminarArchivo(String nombre) {
        proyecto2_so.Controladores.Archivo archivoABorrar = null;
        
        for (proyecto2_so.Controladores.Archivo arch : listaArchivos) {
            if (arch.getNombre().equals(nombre)) {
                archivoABorrar = arch;
                break;
            }
        }
        
        if (archivoABorrar != null) {
            int bloquesALiberar = archivoABorrar.getBloques();
            
            for (int i = 0; i < 100 && bloquesALiberar > 0; i++) {
                if (!mapaBits[i]) { 
                    mapaBits[i] = true;
                    bloquesALiberar--;
                }
            }
            
            listaArchivos.remove(archivoABorrar);
            return true;
        }
        
        return false; 
    }
}