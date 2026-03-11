/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Modelos;

public class Proceso {
    private static int contadorIds = 1; 
    
    private int id;
    private String operacion; 
    private String estado;    
    private Archivo archivoRelacionado; 
    

    private String usuarioCreador;

    public Proceso(String operacion, Archivo archivoRelacionado, String usuarioCreador) {
        this.id = contadorIds++; 
        this.operacion = operacion;
        this.estado = "NUEVO"; 
        this.archivoRelacionado = archivoRelacionado;
        this.usuarioCreador = usuarioCreador;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getOperacion() {
        return operacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado; 
    }

    public Archivo getArchivoRelacionado() {
        return archivoRelacionado;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }
}
