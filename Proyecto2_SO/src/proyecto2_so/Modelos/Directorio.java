/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Modelos;

import proyecto2_so.Estructuras.ListaEnlazada;

public class Directorio {
    private String nombre;
    private String dueño;
    

    private ListaEnlazada<Archivo> archivos;
    private ListaEnlazada<Directorio> subdirectorios;

    public Directorio(String nombre, String dueño) {
        this.nombre = nombre;
        this.dueño = dueño;
        this.archivos = new ListaEnlazada<>();
        this.subdirectorios = new ListaEnlazada<>();
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

    public ListaEnlazada<Archivo> getArchivos() {
        return archivos;
    }

    public ListaEnlazada<Directorio> getSubdirectorios() {
        return subdirectorios;
    }
}