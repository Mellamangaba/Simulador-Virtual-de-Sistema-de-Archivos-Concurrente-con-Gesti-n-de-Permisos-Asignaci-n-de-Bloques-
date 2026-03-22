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
    public boolean renombrarArchivo(String nombreViejo, String nombreNuevo) {
    for (proyecto2_so.Controladores.Archivo arch : listaArchivos) {
        if (arch.getNombre().equals(nombreViejo)) {
            arch.setNombre(nombreNuevo);
            return true; // Éxito
        }
    }
    return false; // No se encontró el archivo
}
    
private java.util.Queue<String> colaProcesos = new java.util.LinkedList<>();

public void agregarACola(String operacion) {
    colaProcesos.add(operacion);
}

public String obtenerSiguienteProceso() {
    return colaProcesos.poll(); // Saca el primero de la lista
}

public java.util.Queue<String> getCola() {
    return colaProcesos;
}

public proyecto2_so.Controladores.Archivo buscarArchivoPorBloque(int numBloque) {
    // 1. Recorremos la lista real de archivos
    for (proyecto2_so.Controladores.Archivo arch : listaArchivos) {
        
        // 2. Calculamos el rango de bloques que ocupa ese archivo
        int inicio = arch.getBloqueInicio();
        int fin = inicio + arch.getTamaño();
        
        // 3. ¿El bloque que estamos pintando (i) está dentro de este rango?
        if (numBloque >= inicio && numBloque < fin) {
            return arch; // Enviamos el archivo a la interfaz
        }
    }
    return null; // Si nadie lo reclama, devuelve nada
}

}