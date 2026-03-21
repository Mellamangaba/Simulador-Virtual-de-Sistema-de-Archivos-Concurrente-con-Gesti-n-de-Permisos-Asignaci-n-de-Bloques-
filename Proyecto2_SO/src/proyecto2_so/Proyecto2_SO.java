/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2_so;

import proyecto2_so.Controladores.SistemaArchivos;
import proyecto2_so.Vistas.MainInterface;

public class Proyecto2_SO {

    public static void main(String[] args) {
        // 1. Inicializamos la lógica central (El disco de 64 bloques)
        SistemaArchivos sistema = new SistemaArchivos();
        
        // 2. Iniciamos la interfaz gráfica pasándole el sistema
        java.awt.EventQueue.invokeLater(() -> {
            // Aquí creamos la ventana y le "entregamos" el disco que acabamos de crear
            MainInterface ventana = new MainInterface(sistema);
            ventana.setVisible(true);
        });
    }
}