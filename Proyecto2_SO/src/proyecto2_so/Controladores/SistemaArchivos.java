package proyecto2_so.Controladores;

import proyecto2_so.Modelos.Archivo;
import proyecto2_so.Modelos.Proceso;
import proyecto2_so.Estructuras.ListaSimple;
import proyecto2_so.Estructuras.Cola;

public class SistemaArchivos {

    private boolean[] mapaBits;
    private ListaSimple<Archivo> listaArchivos; 
    private Cola<Proceso> colaProcesos;         
    private int posicionCabezal; 

    public SistemaArchivos() {
        listaArchivos = new ListaSimple<>();
        colaProcesos = new Cola<>();
        mapaBits = new boolean[200];
        posicionCabezal = 0; 
        
        for (int i = 0; i < 200; i++) {
            mapaBits[i] = true;
        }
    }

    public boolean[] getMapaBits() { return mapaBits; }
    public ListaSimple<Archivo> getListaArchivos() { return listaArchivos; }
    public Cola<Proceso> getColaProcesos() { return colaProcesos; }
    public int getPosicionCabezal() { return posicionCabezal; }
    public void setPosicionCabezal(int nuevaPosicion) { this.posicionCabezal = nuevaPosicion; }

    public boolean crearArchivo(String nombre, int cantidadBloques, String padre) {
        int bloquesLibres = 0;
        for (int i = 0; i < 200; i++) {
            if (mapaBits[i]) bloquesLibres++;
        }

        if (bloquesLibres < cantidadBloques) return false; 

        Archivo nuevoArchivo = new Archivo(nombre, cantidadBloques, padre);
        
        int bloquesAsignados = 0;
        for (int i = 0; i < 200 && bloquesAsignados < cantidadBloques; i++) {
            if (mapaBits[i]) {
                mapaBits[i] = false; 
                nuevoArchivo.getBloquesAsignados().agregar(i);
                bloquesAsignados++;
            }
        }
        listaArchivos.agregar(nuevoArchivo);
        return true; 
    }

    public boolean crearDirectorio(String nombre, String padre) {
        Archivo nuevaCarpeta = new Archivo(nombre, padre);
        listaArchivos.agregar(nuevaCarpeta);
        return true; 
    }

    public boolean eliminarArchivo(String nombre) {
        Archivo archivoABorrar = null;
        for (int i = 0; i < listaArchivos.tamano(); i++) {
            Archivo arch = listaArchivos.obtener(i);
            if (arch.getNombre().equals(nombre)) {
                archivoABorrar = arch;
                break;
            }
        }
        
        if (archivoABorrar != null) {
            if (!archivoABorrar.isEsDirectorio()) {
                for (int i = 0; i < archivoABorrar.getBloquesAsignados().tamano(); i++) {
                    int numBloque = archivoABorrar.getBloquesAsignados().obtener(i);
                    mapaBits[numBloque] = true; 
                }
            }
            listaArchivos.eliminar(archivoABorrar); 
            return true;
        }
        return false; 
    }

    public boolean renombrarArchivo(String nombreViejo, String nombreNuevo) {
        for (int i = 0; i < listaArchivos.tamano(); i++) {
            Archivo arch = listaArchivos.obtener(i);
            if (arch.getNombre().equals(nombreViejo)) {
                arch.setNombre(nombreNuevo);
                return true; 
            }
        }
        return false; 
    }

    public Archivo buscarArchivoPorBloque(int numBloque) {
        for (int i = 0; i < listaArchivos.tamano(); i++) {
            Archivo arch = listaArchivos.obtener(i);
            if (!arch.isEsDirectorio() && arch.getBloquesAsignados().contiene(numBloque)) {
                return arch; 
            }
        }
        return null; 
    }
    public boolean crearArchivoDesdeJSON(String nombre, int cantidadBloques, int bloqueInicio) {
        proyecto2_so.Modelos.Archivo nuevoArchivo = new proyecto2_so.Modelos.Archivo(nombre, cantidadBloques, "Disco Local /");
        
        int bloquesAsignados = 0;
        for (int i = bloqueInicio; i < 200 && bloquesAsignados < cantidadBloques; i++) {
            if (mapaBits[i]) {
                mapaBits[i] = false; 
                nuevoArchivo.getBloquesAsignados().agregar(i);
                bloquesAsignados++;
            }
        }
        
        listaArchivos.agregar(nuevoArchivo);
        return true; 
    }
    public void agregarProceso(Proceso nuevoProceso) {
        colaProcesos.encolar(nuevoProceso);
    }

    public Proceso obtenerSiguienteProceso() { 
        return colaProcesos.desencolar(); 
    }

    public Proceso obtenerSiguienteProcesoSSTF() {
        if (colaProcesos.estaVacia()) return null;
        Proceso masCercano = null;
        int minimaDistancia = Integer.MAX_VALUE;

        for (int i = 0; i < colaProcesos.tamano(); i++) {
            Proceso p = colaProcesos.obtener(i);
            int distancia = Math.abs(p.getBloqueDestino() - posicionCabezal);
            if (distancia < minimaDistancia) {
                minimaDistancia = distancia;
                masCercano = p;
            }
        }
        if (masCercano != null) colaProcesos.remover(masCercano);
        return masCercano;
    }

    public Proceso obtenerSiguienteProcesoSCAN() {
        if (colaProcesos.estaVacia()) return null;
        Proceso seleccionado = null;
        int minimaDistancia = Integer.MAX_VALUE;

        for (int i = 0; i < colaProcesos.tamano(); i++) {
            Proceso p = colaProcesos.obtener(i);
            if (p.getBloqueDestino() >= posicionCabezal) { 
                int distancia = p.getBloqueDestino() - posicionCabezal;
                if (distancia < minimaDistancia) {
                    minimaDistancia = distancia;
                    seleccionado = p;
                }
            }
        }
        if (seleccionado == null) return obtenerSiguienteProcesoSSTF();
        
        colaProcesos.remover(seleccionado);
        return seleccionado;
    }

    public Proceso obtenerSiguienteProcesoCSCAN() {
        if (colaProcesos.estaVacia()) return null;
        Proceso seleccionado = null;
        int minimaDistancia = Integer.MAX_VALUE;

        for (int i = 0; i < colaProcesos.tamano(); i++) {
            Proceso p = colaProcesos.obtener(i);
            if (p.getBloqueDestino() >= posicionCabezal) {
                int distancia = p.getBloqueDestino() - posicionCabezal;
                if (distancia < minimaDistancia) {
                    minimaDistancia = distancia;
                    seleccionado = p;
                }
            }
        }
        if (seleccionado == null) {
            for (int i = 0; i < colaProcesos.tamano(); i++) {
                Proceso p = colaProcesos.obtener(i);
                if (p.getBloqueDestino() < minimaDistancia) {
                    minimaDistancia = p.getBloqueDestino();
                    seleccionado = p;
                }
            }
        }
        
        if (seleccionado != null) colaProcesos.remover(seleccionado);
        return seleccionado;
    }
    
    public proyecto2_so.Modelos.Archivo buscarArchivoPorNombre(String nombre) {
    for (int i = 0; i < listaArchivos.tamano(); i++) {
        proyecto2_so.Modelos.Archivo arch = listaArchivos.obtener(i);
        if (arch.getNombre().equals(nombre)) {
            return arch;
        }
    }
    return null; // Si no lo encuentra
}
}