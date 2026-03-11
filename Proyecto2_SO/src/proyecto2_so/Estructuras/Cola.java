/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Estructuras;

public class Cola<T> {
    private Nodo<T> frente; 
    private Nodo<T> fin;    
    private int tamaño;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    
    public void encolar(T data) {
        Nodo<T> nuevoNodo = new Nodo<>(data);
        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.setNext(nuevoNodo);
            fin = nuevoNodo;
        }
        tamaño++;
    }

    
    public T desencolar() {
        if (estaVacia()) {
            return null; 
        }
        T data = frente.getData();
        frente = frente.getNext();
        
    
        if (frente == null) {
            fin = null;
        }
        tamaño--;
        return data;
    }

   
    public T verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getData();
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }
    
    public void limpiar() {
        frente = null;
        fin = null;
        tamaño = 0;
    }
}