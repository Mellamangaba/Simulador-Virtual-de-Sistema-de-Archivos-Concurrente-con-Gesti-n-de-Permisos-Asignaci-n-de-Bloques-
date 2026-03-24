/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_so.Controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManejadorJSON {

    public static void cargarArchivo(String rutaArchivo, SistemaArchivos fs) {
        StringBuilder contenido = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
            return;
        }
        
        String json = contenido.toString();

        Matcher mHead = Pattern.compile("\"initial_head\"\\s*:\\s*(\\d+)").matcher(json);
        if (mHead.find()) {
            fs.setPosicionCabezal(Integer.parseInt(mHead.group(1)));
        }

        Matcher mFiles = Pattern.compile("\"(\\d+)\"\\s*:\\s*\\{\\s*\"name\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"blocks\"\\s*:\\s*(\\d+)\\s*\\}").matcher(json);
        while (mFiles.find()) {
            int bloqueInicio = Integer.parseInt(mFiles.group(1));
            String nombre = mFiles.group(2);
            int bloques = Integer.parseInt(mFiles.group(3));
            
            fs.crearArchivoDesdeJSON(nombre, bloques, bloqueInicio);
        }

        Matcher mReqs = Pattern.compile("\\{\\s*\"pos\"\\s*:\\s*(\\d+)\\s*,\\s*\"op\"\\s*:\\s*\"([^\"]+)\"\\s*\\}").matcher(json);
        while (mReqs.find()) {
            int pos = Integer.parseInt(mReqs.group(1));
            String op = mReqs.group(2);
            
            proyecto2_so.Modelos.Proceso nuevoProc = new proyecto2_so.Modelos.Proceso("P_" + op, op + " en bloque " + pos, pos);
            fs.agregarProceso(nuevoProc);
        }
    }
}
