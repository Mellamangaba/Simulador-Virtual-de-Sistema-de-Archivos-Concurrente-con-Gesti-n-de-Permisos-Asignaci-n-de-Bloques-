package proyecto2_so.Controladores;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import proyecto2_so.Modelos.Proceso; 
import proyecto2_so.Estructuras.ListaEnlazada;

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

    public Proceso obtenerSiguienteProcesoSCAN() {
    // 1. Usamos isEmpty() en lugar de estaVacia()
    if (colaProcesos.isEmpty()) return null;

    ListaEnlazada<Proceso> listaTemporal = new ListaEnlazada<>();
    
    // 2. Usamos poll() para sacar de la cola (si tu método es dequeue, cámbialo)
    while (!colaProcesos.isEmpty()) {
        listaTemporal.add(colaProcesos.poll());
    }

    Proceso seleccionado = null;
    int mejorDistancia = Integer.MAX_VALUE;
    int indiceSeleccionado = -1;

    for (int i = 0; i < listaTemporal.size(); i++) {
        Proceso p = listaTemporal.get(i);
        int bloque = p.getBloqueDestino();
        
        if (moviendoHaciaArriba && bloque >= posicionCabezal) {
            int dist = bloque - posicionCabezal;
            if (dist < mejorDistancia) {
                mejorDistancia = dist;
                seleccionado = p;
                indiceSeleccionado = i;
            }
        } else if (!moviendoHaciaArriba && bloque <= posicionCabezal) {
            int dist = posicionCabezal - bloque;
            if (dist < mejorDistancia) {
                mejorDistancia = dist;
                seleccionado = p;
                indiceSeleccionado = i;
            }
        }
    }

    // 3. Lógica de rebote
    if (seleccionado == null) {
        // Devolvemos todo a la cola usando add() o enqueue()
        for (int i = 0; i < listaTemporal.size(); i++) {
            colaProcesos.add(listaTemporal.get(i));
        }
        moviendoHaciaArriba = !moviendoHaciaArriba;
        return obtenerSiguienteProcesoSCAN(); 
    }

    // 4. Quitamos el elegido
    listaTemporal.remove(indiceSeleccionado);

    // 5. Devolvemos el resto a la cola original
    for (int i = 0; i < listaTemporal.size(); i++) {
        colaProcesos.add(listaTemporal.get(i));
    }

    return seleccionado;
}
    
    public Proceso obtenerSiguienteProcesoCSCAN() {
    // 1. Verificación: Si no hay procesos, no hace nada
    if (colaProcesos.isEmpty()) return null;

    // 2. Pasamos los procesos de la Cola a una ListaEnlazada temporal
    // Esto es necesario para poder usar .get(i) y revisar todos los bloques
    ListaEnlazada<Proceso> listaTemporal = new ListaEnlazada<>();
    while (!colaProcesos.isEmpty()) {
        listaTemporal.add(colaProcesos.poll());
    }

    Proceso seleccionado = null;
    int mejorDistancia = Integer.MAX_VALUE;
    int indiceSeleccionado = -1;

    // 3. Lógica C-SCAN: Solo buscamos procesos que estén ADELANTE (bloques mayores)
    for (int i = 0; i < listaTemporal.size(); i++) {
        Proceso p = listaTemporal.get(i);
        int bloque = p.getBloqueDestino();
        
        // Solo atendemos si el bloque es mayor o igual a la posición actual del cabezal
        if (bloque >= posicionCabezal) {
            int dist = bloque - posicionCabezal;
            if (dist < mejorDistancia) {
                mejorDistancia = dist;
                seleccionado = p;
                indiceSeleccionado = i;
            }
        }
    }

    // 4. EL SALTO CIRCULAR: Si no encontramos nada adelante...
    if (seleccionado == null) {
        // Devolvemos todos los procesos a la cola original para no perderlos
        for (int i = 0; i < listaTemporal.size(); i++) {
            colaProcesos.add(listaTemporal.get(i));
        }
        
        // Movemos el cabezal al inicio del disco (Bloque 0)
        this.posicionCabezal = 0; 
        
        // Volvemos a llamar a la función para que busque desde el inicio
        return obtenerSiguienteProcesoCSCAN(); 
    }

    // 5. Si encontramos un proceso, lo quitamos de la lista temporal
    listaTemporal.remove(indiceSeleccionado);

    // 6. Devolvemos el RESTO de los procesos a la cola original
    for (int i = 0; i < listaTemporal.size(); i++) {
        colaProcesos.add(listaTemporal.get(i));
    }

    return seleccionado;
}
    
}