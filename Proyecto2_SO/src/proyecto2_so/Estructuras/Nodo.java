/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Estructuras;

public class Nodo<T> {
    public T data;
    public Nodo<T> next;

    public Nodo(T data) {
        this.data = data;
        this.next = null;
    }
}