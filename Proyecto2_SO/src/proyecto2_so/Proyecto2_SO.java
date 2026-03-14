/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2_so;
import proyecto2_so.Controladores.SistemaArchivos;
import proyecto2_so.Vistas.MainInterface;
/**
 *
 * @author gabri
 */
public class Proyecto2_SO {
    public static void main(String[] args) {
        // 1. Inicializamos la lógica central
        SistemaArchivos sistema = new SistemaArchivos(64);
        
        // 2. Iniciamos la interfaz gráfica
        java.awt.EventQueue.invokeLater(() -> {
            new MainInterface(sistema).setVisible(true);
        });
    }
}