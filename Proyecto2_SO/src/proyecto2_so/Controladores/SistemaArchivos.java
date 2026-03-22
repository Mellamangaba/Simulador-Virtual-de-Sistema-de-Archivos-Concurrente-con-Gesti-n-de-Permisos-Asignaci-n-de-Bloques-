package proyecto2_so.Controladores;

public class SistemaArchivos {

    private boolean[] mapaBits;
    private java.util.ArrayList<Archivo> listaArchivos;
    private java.util.Queue<String> colaProcesos = new java.util.LinkedList<>();

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

        proyecto2_so.Controladores.Archivo nuevoArchivo = new proyecto2_so.Controladores.Archivo(nombre, cantidadBloques);
        
        int bloquesAsignados = 0;
        for (int i = 0; i < 100 && bloquesAsignados < cantidadBloques; i++) {
            if (mapaBits[i]) {
                mapaBits[i] = false;
                nuevoArchivo.getBloquesAsignados().add(i); 
                bloquesAsignados++;
            }
        }
        
        listaArchivos.add(nuevoArchivo);
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
            for (Integer numBloque : archivoABorrar.getBloquesAsignados()) {
                mapaBits[numBloque] = true; 
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
                return true; 
            }
        }
        return false; 
    }

    public void agregarACola(String operacion) {
        colaProcesos.add(operacion);
    }

    public String obtenerSiguienteProceso() {
        return colaProcesos.poll(); 
    }

    public java.util.Queue<String> getCola() {
        return colaProcesos;
    }

    public proyecto2_so.Controladores.Archivo buscarArchivoPorBloque(int numBloque) {
        for (proyecto2_so.Controladores.Archivo arch : listaArchivos) {
            if (arch.getBloquesAsignados() != null && arch.getBloquesAsignados().contains(numBloque)) {
                return arch; 
            }
        }
        return null; 
    }

}   