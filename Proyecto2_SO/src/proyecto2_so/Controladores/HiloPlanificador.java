package proyecto2_so.Controladores;

import proyecto2_so.Modelos.Proceso;
import proyecto2_so.Modelos.Archivo;
import proyecto2_so.Vistas.MainInterface;

public class HiloPlanificador extends Thread {
    
    private SistemaArchivos fs;
    private MainInterface vista;
    private boolean corriendo = true;
    
    private String algoritmoActual = "FIFO"; 

    public HiloPlanificador(SistemaArchivos fs, MainInterface vista) {
        this.fs = fs;
        this.vista = vista;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmoActual = algoritmo;
    }

    @Override
    public void run() {
        while (corriendo) {
            try {
                if (!fs.getColaProcesos().estaVacia()) {
                    
                    Proceso p = null;
                    
                    switch (algoritmoActual) {
                        case "SSTF":
                            p = fs.obtenerSiguienteProcesoSSTF();
                            break;
                        case "SCAN":
                            p = fs.obtenerSiguienteProcesoSCAN();
                            break;
                        case "C-SCAN":
                            p = fs.obtenerSiguienteProcesoCSCAN();
                            break;
                        default: // FIFO
                            p = fs.obtenerSiguienteProceso();
                            break;
                    }

                    if (p != null) {
                        p.setEstado("Ejecutando");
                        
                        fs.setPosicionCabezal(p.getBloqueDestino());
                        
                        vista.actualizarVista();
                        vista.agregarLog("▶️ Iniciando [" + algoritmoActual + "]: " + p.getOperacion() + " (Cabezal movido al bloque " + fs.getPosicionCabezal() + ")");

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
                                vista.agregarLog("✅ Proceso terminado. Lock liberado.");
                                
                            } else {
                                p.setEstado("Bloqueado");
                                vista.agregarLog("⛔ Archivo ocupado. Proceso BLOQUEADO.");
                                fs.getColaProcesos().encolar(p); 
                                Thread.sleep(1000); 
                            }
                        } else {
                            p.setEstado("Terminado (Error)");
                            vista.agregarLog("❌ Error: Archivo no encontrado.");
                        }
                        vista.actualizarVista();
                    }
                } else {
                    Thread.sleep(500);
                }
                
            } catch (InterruptedException e) {
                System.out.println("El hilo planificador fue interrumpido.");
            }
        }
    }
}