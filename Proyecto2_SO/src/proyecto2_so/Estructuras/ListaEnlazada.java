/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Estructuras;

public class ListaEnlazada<T> {
    private Nodo<T> head; // Cabeza de la lista
    private int size;     // Tamaño de la lista

    public ListaEnlazada() {
        this.head = null;
        this.size = 0;
    }

    // Método para agregar un elemento al final
    public void add(T data) {
        Nodo<T> newNode = new Nodo<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Nodo<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    // Método para obtener un elemento por su índice
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        Nodo<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    // Método para saber el tamaño de la lista
    public int size() {
        return size;
    }

    // Método para saber si está vacía
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Método para limpiar la lista
    public void clear() {
        head = null;
        size = 0;
    }
}
