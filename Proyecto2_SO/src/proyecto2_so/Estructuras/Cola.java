/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Estructuras;

public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> finalCola;
    private int tamano;

    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamano = 0;
    }

    public void encolar(T data) {
        Nodo<T> nuevo = new Nodo<>(data);
        if (estaVacia()) {
            frente = nuevo;
        } else {
            finalCola.next = nuevo;
        }
        finalCola = nuevo;
        tamano++;
    }

    public T desencolar() {
        if (estaVacia()) return null;
        T data = frente.data;
        frente = frente.next;
        if (frente == null) {
            finalCola = null;
        }
        tamano--;
        return data;
    }

    public void remover(T data) {
        if (estaVacia()) return;
        if (frente.data.equals(data)) {
            desencolar();
            return;
        }
        Nodo<T> actual = frente;
        while (actual.next != null) {
            if (actual.next.data.equals(data)) {
                if (actual.next == finalCola) {
                    finalCola = actual;
                }
                actual.next = actual.next.next;
                tamano--;
                return;
            }
            actual = actual.next;
        }
    }

    public T obtener(int index) {
        if (index < 0 || index >= tamano) return null;
        Nodo<T> actual = frente;
        for (int i = 0; i < index; i++) {
            actual = actual.next;
        }
        return actual.data;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamano() {
        return tamano;
    }
}