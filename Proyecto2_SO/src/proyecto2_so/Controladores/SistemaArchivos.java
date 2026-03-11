/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Controladores;

import proyecto2_so.Modelos.Directorio;

public class SistemaArchivos {
    private Directorio raiz; 
    private int totalBloques;
    
    
    private boolean[] mapaBits; 

    // Constructor
    public SistemaArchivos(int totalBloques) {
        this.totalBloques = totalBloques;
        this.mapaBits = new boolean[totalBloques];
        
       
        for (int i = 0; i < totalBloques; i++) {
            this.mapaBits[i] = true;
        }
        

        this.raiz = new Directorio("Raiz", "admin");
    }

    // --- GETTERS ---
    public Directorio getRaiz() {
        return raiz;
    }

    public int getTotalBloques() {
        return totalBloques;
    }

    public boolean[] getMapaBits() {
        return mapaBits;
    }
    
   
    public int calcularBloquesLibres() {
        int libres = 0;
        for (int i = 0; i < totalBloques; i++) {
            if (mapaBits[i]) {
                libres++;
            }
        }
        return libres;
    }
}
