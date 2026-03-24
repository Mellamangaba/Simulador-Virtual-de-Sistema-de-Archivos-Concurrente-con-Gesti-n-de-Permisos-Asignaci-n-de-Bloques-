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
    private String padre; // Esta es la que usaremos como "Dueño" (Carpeta a la que pertenece)
    private java.awt.Color color;
    
    // Variables para el control de acceso (Locks)
    private int cantidadLectores = 0;
    private boolean estaEscribiendo = false;

    public Archivo(String nombre, int bloques, String padre) {
        this.nombre = nombre;
        this.extension = "txt"; 
        this.bloques = bloques;
        this.tamanio = bloques * 4; 
        this.bloquesAsignados = new ListaSimple<>(); 
        this.esDirectorio = false; 
        this.padre = padre; 
        this.color = generarColorAleatorio();
    }

    public Archivo(String nombre, String padre) {
        this.nombre = nombre;
        this.extension = ""; 
        this.bloques = 0; 
        this.tamanio = 0;
        this.bloquesAsignados = new ListaSimple<>();
        this.esDirectorio = true; 
        this.padre = padre;
        this.color = generarColorAleatorio();
    }

    // --- Getters y Setters ---
    public String getNombre() { return nombre; }
    public String getExtension() { return extension; }
    public int getBloques() { return bloques; }
    public int getTamaño() { return tamanio; }
    public ListaSimple<Integer> getBloquesAsignados() { return bloquesAsignados; } 
    public void setNombre(String nuevoNombre) { this.nombre = nuevoNombre; }
    public boolean isEsDirectorio() { return esDirectorio; }
    
    // Ajustado para que si no tiene padre, devuelva "Raíz"
    public String getPadre() { 
        return (padre == null || padre.isEmpty() || padre.equals("/")) ? "Raíz" : padre; 
    }

    public java.awt.Color getColor() { return color; }

    private java.awt.Color generarColorAleatorio() {
        int r = (int)(Math.random() * 200) + 55; 
        int g = (int)(Math.random() * 200) + 55;
        int b = (int)(Math.random() * 200) + 55;
        return new java.awt.Color(r, g, b);
    }
    
    // --- Lógica de Locks (Sincronizada) ---
    public synchronized boolean intentarLockLectura() {
        if (estaEscribiendo) return false; 
        cantidadLectores++;
        return true;
    }

    public synchronized void liberarLockLectura() {
        if (cantidadLectores > 0) cantidadLectores--;
        notifyAll(); 
    }

    public synchronized boolean intentarLockEscritura() {
        if (cantidadLectores > 0 || estaEscribiendo) return false; 
        estaEscribiendo = true;
        return true;
    }

    public synchronized void liberarLockEscritura() {
        estaEscribiendo = false;
        notifyAll(); 
    }

    // Método para mostrar el estado en el JTree
    public synchronized String getEstadoLock() {
        if (estaEscribiendo) return "🚫 Bloqueado (Escritura Exclusiva)";
        if (cantidadLectores > 0) return "🔒 Bloqueado (Lectura Compartida x" + cantidadLectores + ")";
        return "🔓 Libre";
    }
}