package proyecto2_so.Controladores;

import proyecto2_so.Modelos.Proceso;
import proyecto2_so.Modelos.Archivo;
import proyecto2_so.Vistas.MainInterface;

public class HiloPlanificador extends Thread {
    
    private SistemaArchivos fs;
    private MainInterface vista;
    private boolean corriendo = true;

    public HiloPlanificador(SistemaArchivos fs, MainInterface vista) {
        this.fs = fs;
        this.vista = vista;
    }

    @Override
    public void run() {
        while (corriendo) {
            try {
                if (!fs.getColaProcesos().estaVacia()) {
                    
                    Proceso p = fs.getColaProcesos().desencolar();
                    p.setEstado("Ejecutando");
                    vista.actualizarVista();
                    vista.agregarLog("▶️ Iniciando proceso: " + p.getOperacion());

                    Archivo arch = fs.buscarArchivoPorBloque(p.getBloqueDestino());
                    boolean tieneLock = false;

                    if (arch != null) {
                        if (p.getOperacion().contains("READ") || p.getIdProceso().contains("READ")) {
                            tieneLock = arch.intentarLockLectura();
                        } else {
                            tieneLock = arch.intentarLockEscritura();
                        }

                        if (tieneLock) {
                            vista.agregarLog("🔒 Lock adquirido en " + arch.getNombre() + " (" + arch.getEstadoLock() + ")");
                            vista.actualizarVista();
                            
                            Thread.sleep(2000); 
                            
                            if (p.getOperacion().contains("READ") || p.getIdProceso().contains("READ")) {
                                arch.liberarLockLectura();
                            } else {
                                arch.liberarLockEscritura();
                            }
                            p.setEstado("Terminado");
                            vista.agregarLog("✅ Proceso " + p.getOperacion() + " terminado. Lock liberado.");
                            
                        } else {
                            p.setEstado("Bloqueado");
                            vista.agregarLog("⛔ Archivo ocupado. Proceso '" + p.getOperacion() + "' ha sido BLOQUEADO.");
                            
                            fs.getColaProcesos().encolar(p); 
                            Thread.sleep(1000); 
                        }
                    } else {
                        p.setEstado("Terminado (Error)");
                        vista.agregarLog("❌ Error: Archivo no encontrado para la operación.");
                    }
                    
                    vista.actualizarVista();
                } else {
                    Thread.sleep(500);
                }
                
            } catch (InterruptedException e) {
                System.out.println("El hilo planificador fue interrumpido.");
            }
        }
    }
}