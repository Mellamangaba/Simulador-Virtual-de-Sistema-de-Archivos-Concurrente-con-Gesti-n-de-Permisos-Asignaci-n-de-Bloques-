/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Modelos;

public class Proceso {
    private String idProceso; 
    private String operacion; 
    private int bloqueDestino; 

    public Proceso(String idProceso, String operacion, int bloqueDestino) {
        this.idProceso = idProceso;
        this.operacion = operacion;
        this.bloqueDestino = bloqueDestino;
    }

    public String getIdProceso() {
        return idProceso;
    }

    public String getOperacion() {
        return operacion;
    }

    public int getBloqueDestino() {
        return bloqueDestino;
    }

    @Override
    public String toString() {
        return idProceso + " | " + operacion + " -> Ir al Bloque: " + bloqueDestino;
    }
}