/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Estructuras;

public class Cola<T> {
    private Nodo<T> frente; // El primero en la fila (head)
    private Nodo<T> fin;    // El último en la fila (tail)
    private int tamaño;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    // Método para agregar un elemento al final de la cola (Enqueue)
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

    // Método para sacar y devolver el primer elemento de la cola (Dequeue)
    public T desencolar() {
        if (estaVacia()) {
            return null; // O podrías lanzar una excepción
        }
        T data = frente.getData();
        frente = frente.getNext();
        
        // Si al sacar el elemento la cola quedó vacía, el fin también es null
        if (frente == null) {
            fin = null;
        }
        tamaño--;
        return data;
    }

    // Método para ver el primer elemento sin sacarlo (Peek)
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