/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Estructuras;

public class ListaSimple<T> {
    private Nodo<T> cabeza;
    private int tamano;

    public ListaSimple() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public void agregar(T data) {
        Nodo<T> nuevo = new Nodo<>(data);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.next != null) {
                actual = actual.next;
            }
            actual.next = nuevo;
        }
        tamano++;
    }

    public T obtener(int index) {
        if (index < 0 || index >= tamano) return null;
        Nodo<T> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.next;
        }
        return actual.data;
    }

    public void eliminar(T data) {
        if (cabeza == null) return;
        
        if (cabeza.data.equals(data)) {
            cabeza = cabeza.next;
            tamano--;
            return;
        }
        
        Nodo<T> actual = cabeza;
        while (actual.next != null) {
            if (actual.next.data.equals(data)) {
                actual.next = actual.next.next;
                tamano--;
                return;
            }
            actual = actual.next;
        }
    }

    public boolean contiene(T data) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.data.equals(data)) return true;
            actual = actual.next;
        }
        return false;
    }

    public int tamano() {
        return tamano;
    }
    
    public boolean estaVacia() {
        return tamano == 0;
    }
}
