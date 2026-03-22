/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2_so;

import proyecto2_so.Controladores.SistemaArchivos;
import proyecto2_so.Vistas.MainInterface;

public class Proyecto2_SO {

    public static void main(String[] args) {
        SistemaArchivos sistema = new SistemaArchivos();
        
        java.awt.EventQueue.invokeLater(() -> {
            MainInterface ventana = new MainInterface(sistema);
            ventana.setVisible(true);
        });
    }
}