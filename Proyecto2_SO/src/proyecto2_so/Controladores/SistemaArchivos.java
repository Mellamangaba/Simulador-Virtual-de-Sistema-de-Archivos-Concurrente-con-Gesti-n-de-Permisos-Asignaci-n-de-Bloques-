package proyecto2_so.Controladores;

public class SistemaArchivos {
    
    // Nuestro disco duro simulado: un arreglo de 64 booleanos
    // true = Libre (Gris), false = Ocupado/Dañado (Azul)
    private boolean[] mapaBits;

    // Constructor: Esto se ejecuta cuando creamos el "new SistemaArchivos()"
    public SistemaArchivos() {
        // 1. Creamos el arreglo con exactamente 64 espacios
        mapaBits = new boolean[100];
        
        // 2. Llenamos todos los espacios como "Libres" (true) al inicio
        for (int i = 0; i < 100; i++) {
            mapaBits[i] = true;
        }
    }

    // Método para que la ventana pueda pedir y leer los bloques
    public boolean[] getMapaBits() {
        return mapaBits;
    }
}