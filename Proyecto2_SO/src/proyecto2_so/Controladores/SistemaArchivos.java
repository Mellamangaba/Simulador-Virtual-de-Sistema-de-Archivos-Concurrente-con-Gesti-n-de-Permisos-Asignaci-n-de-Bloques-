package proyecto2_so.Controladores;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import proyecto2_so.Modelos.Proceso; // Importamos la nueva clase Proceso

public class SistemaArchivos {

    private boolean[] mapaBits;
    private ArrayList<Archivo> listaArchivos;
    
    // --- VARIABLES NUEVAS PARA EL PLANIFICADOR ---
    private Queue<Proceso> colaProcesos;
    private int posicionCabezal; 

    public SistemaArchivos() {
        listaArchivos = new ArrayList<>();
        colaProcesos = new LinkedList<>();
        mapaBits = new boolean[100];
        posicionCabezal = 0; // El disco siempre arranca en la posición 0
        
        // Inicializamos todos los bloques como libres (true)
        for (int i = 0; i < 100; i++) {
            mapaBits[i] = true;
        }
    }

    public boolean[] getMapaBits() {
        return mapaBits;
    }

    // --- 1. MÉTODO PARA CREAR ARCHIVOS ---
    public boolean crearArchivo(String nombre, int cantidadBloques, String padre) {
        int bloquesLibres = 0;
        for (int i = 0; i < 100; i++) {
            if (mapaBits[i]) { 
                bloquesLibres++;
            }
        }

        if (bloquesLibres < cantidadBloques) {
            return false; 
        }

        Archivo nuevoArchivo = new Archivo(nombre, cantidadBloques, padre);
        
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

    // --- 2. MÉTODO PARA CREAR CARPETAS ---
    public boolean crearDirectorio(String nombre, String padre) {
        Archivo nuevaCarpeta = new Archivo(nombre, padre);
        listaArchivos.add(nuevaCarpeta);
        return true; 
    }

    public ArrayList<Archivo> getListaArchivos() {
        return listaArchivos;
    }

    // --- 3. MÉTODO PARA ELIMINAR ARCHIVOS Y CARPETAS ---
    public boolean eliminarArchivo(String nombre) {
        Archivo archivoABorrar = null;
        
        for (Archivo arch : listaArchivos) {
            if (arch.getNombre().equals(nombre)) {
                archivoABorrar = arch;
                break;
            }
        }
        
        if (archivoABorrar != null) {
            if (!archivoABorrar.isEsDirectorio()) {
                for (Integer numBloque : archivoABorrar.getBloquesAsignados()) {
                    mapaBits[numBloque] = true; 
                }
            }
            listaArchivos.remove(archivoABorrar); 
            return true;
        }
        
        return false; 
    }

    // --- 4. MÉTODO PARA RENOMBRAR ---
    public boolean renombrarArchivo(String nombreViejo, String nombreNuevo) {
        for (Archivo arch : listaArchivos) {
            if (arch.getNombre().equals(nombreViejo)) {
                arch.setNombre(nombreNuevo);
                return true; 
            }
        }
        return false; 
    }

    // --- 5. MÉTODO PARA DIBUJAR LOS COLORES EN EL DISCO ---
    public Archivo buscarArchivoPorBloque(int numBloque) {
        for (Archivo arch : listaArchivos) {
            if (!arch.isEsDirectorio() && arch.getBloquesAsignados() != null && arch.getBloquesAsignados().contains(numBloque)) {
                return arch; 
            }
        }
        return null; 
    }

    // --- 6. MÉTODOS DE LA COLA DE PROCESOS Y CABEZAL ---
    public void agregarProceso(Proceso nuevoProceso) {
        colaProcesos.add(nuevoProceso);
    }

    public Proceso obtenerSiguienteProceso() {
        return colaProcesos.poll(); 
    }

    public Queue<Proceso> getColaProcesos() {
        return colaProcesos;
    }

    public int getPosicionCabezal() {
        return posicionCabezal;
    }

    public void setPosicionCabezal(int nuevaPosicion) {
        this.posicionCabezal = nuevaPosicion;
    }

}