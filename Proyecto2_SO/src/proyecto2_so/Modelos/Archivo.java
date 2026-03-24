/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Modelos;

import proyecto2_so.Estructuras.ListaSimple;

public class Archivo {
    private String nombre;
    private String extension;
    private int bloques;
    private int tamanio; 
    private ListaSimple<Integer> bloquesAsignados; 
    
    private boolean esDirectorio;
    private String padre;

    public Archivo(String nombre, int bloques, String padre) {
        this.nombre = nombre;
        this.extension = "txt"; 
        this.bloques = bloques;
        this.tamanio = bloques * 4; 
        this.bloquesAsignados = new ListaSimple<>(); 
        this.esDirectorio = false; 
        this.padre = padre; 
    }

    public Archivo(String nombre, String padre) {
        this.nombre = nombre;
        this.extension = ""; 
        this.bloques = 0; 
        this.tamanio = 0;
        this.bloquesAsignados = new ListaSimple<>();
        this.esDirectorio = true; 
        this.padre = padre;
    }

    public String getNombre() { return nombre; }
    public String getExtension() { return extension; }
    public int getBloques() { return bloques; }
    public int getTamaño() { return tamanio; }
    public ListaSimple<Integer> getBloquesAsignados() { return bloquesAsignados; } 
    public void setNombre(String nuevoNombre) { this.nombre = nuevoNombre; }
    public boolean isEsDirectorio() { return esDirectorio; }
    public String getPadre() { return padre; }

    private int cantidadLectores = 0;
    private boolean estaEscribiendo = false;

    public synchronized boolean intentarLockLectura() {
        if (estaEscribiendo) {
            return false; 
        }
        cantidadLectores++;
        return true;
    }

    public synchronized void liberarLockLectura() {
        if (cantidadLectores > 0) {
            cantidadLectores--;
        }
        notifyAll(); 
    }

    public synchronized boolean intentarLockEscritura() {
        if (cantidadLectores > 0 || estaEscribiendo) {
            return false; 
        }
        estaEscribiendo = true;
        return true;
    }

    public synchronized void liberarLockEscritura() {
        estaEscribiendo = false;
        notifyAll(); 
    }

    public synchronized String getEstadoLock() {
        if (estaEscribiendo) return "Bloqueado (Escritura Exclusiva)";
        if (cantidadLectores > 0) return "Bloqueado (Lectura Compartida x" + cantidadLectores + ")";
        return "Libre";
    }
}