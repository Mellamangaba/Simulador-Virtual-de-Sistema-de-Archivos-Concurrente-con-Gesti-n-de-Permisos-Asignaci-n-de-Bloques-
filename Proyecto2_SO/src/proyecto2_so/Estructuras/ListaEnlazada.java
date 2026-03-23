/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Estructuras;

public class ListaEnlazada<T> {
    private Nodo<T> head; 
    private int size;     

    public ListaEnlazada() {
        this.head = null;
        this.size = 0;
    }

 
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

   
    public int size() {
        return size;
    }

    
    public boolean isEmpty() {
        return size == 0;
    }
    

    public void clear() {
        head = null;
        size = 0;
    }

public void remove(int index) {
    if (index < 0 || index >= size) return;
    if (index == 0) {
        head = head.getNext();
    } else {
        Nodo<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
    }
    size--;
}
}
