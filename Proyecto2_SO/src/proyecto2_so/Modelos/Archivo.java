/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Modelos;


import proyecto2_so.Estructuras.ListaEnlazada;

public class Archivo {
    private String nombre;
    private String dueño; 
    private int tamañoEnBloques;
    private int bloqueInicio;
    
   
    private ListaEnlazada<Integer> bloquesAsignados;


    public Archivo(String nombre, String dueño, int tamañoEnBloques) {
        this.nombre = nombre;
        this.dueño = dueño;
        this.tamañoEnBloques = tamañoEnBloques;
        this.bloquesAsignados = new ListaEnlazada<>(); 
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDueño() {
        return dueño;
    }

    public int getTamañoEnBloques() {
        return tamañoEnBloques;
    }

    public ListaEnlazada<Integer> getBloquesAsignados() {
        return bloquesAsignados;
    }
    
    public int getBloqueInicio() {
    return bloqueInicio; 
}
}
