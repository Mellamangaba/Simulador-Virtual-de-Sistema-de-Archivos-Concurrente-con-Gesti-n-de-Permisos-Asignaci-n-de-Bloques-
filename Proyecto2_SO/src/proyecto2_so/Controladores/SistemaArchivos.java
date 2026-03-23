package proyecto2_so.Controladores;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import proyecto2_so.Modelos.Proceso; 


public class SistemaArchivos {

    private boolean[] mapaBits;
    private ArrayList<Archivo> listaArchivos;
    
    private Queue<Proceso> colaProcesos;
    private int posicionCabezal; 
    
    private boolean moviendoHaciaArriba = true;

    public SistemaArchivos() {
        listaArchivos = new ArrayList<>();
        colaProcesos = new LinkedList<>();
        mapaBits = new boolean[100];
        posicionCabezal = 0; 
        
        for (int i = 0; i < 100; i++) {
            mapaBits[i] = true;
        }
    }

    public boolean[] getMapaBits() {
        return mapaBits;
    }

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

    public boolean crearDirectorio(String nombre, String padre) {
        Archivo nuevaCarpeta = new Archivo(nombre, padre);
        listaArchivos.add(nuevaCarpeta);
        return true; 
    }

    public ArrayList<Archivo> getListaArchivos() {
        return listaArchivos;
    }

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

    public boolean renombrarArchivo(String nombreViejo, String nombreNuevo) {
        for (Archivo arch : listaArchivos) {
            if (arch.getNombre().equals(nombreViejo)) {
                arch.setNombre(nombreNuevo);
                return true; 
            }
        }
        return false; 
    }

    public Archivo buscarArchivoPorBloque(int numBloque) {
        for (Archivo arch : listaArchivos) {
            if (!arch.isEsDirectorio() && arch.getBloquesAsignados() != null && arch.getBloquesAsignados().contains(numBloque)) {
                return arch; 
            }
        }
        return null; 
    }

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
    public Proceso obtenerSiguienteProcesoSSTF() {
        if (colaProcesos.isEmpty()) {
            return null;
        }
        
    

        Proceso masCercano = null;
        int minimaDistancia = Integer.MAX_VALUE;

        for (Proceso p : colaProcesos) {
            int distancia = Math.abs(p.getBloqueDestino() - posicionCabezal);
            
            if (distancia < minimaDistancia) {
                minimaDistancia = distancia;
                masCercano = p;
            }
        }

        if (masCercano != null) {
            colaProcesos.remove(masCercano);
        }
        
        return masCercano;
    }

}