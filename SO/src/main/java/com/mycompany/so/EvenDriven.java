/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.so;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Se asignan prioridades a los procesos, inicialmente se determinan por la
 * jerarquia del usuario. Para evitar que procesos de alta prioridad ejecuten
 * indetrminadamente, cada cierto tiempo se reduce la prioridad de los procesos
 * en ejecucion. Tambien, se podria aumentar la prioridad de los procesos que
 * estan cierto tiempo sin ejecutarse.
 *
 * @author ecasa
 */
public class EvenDriven implements IPlanificador {

    public EvenDriven() {
    }

    @Override
    public void planificar(PriorityQueue<Proceso> colaListos, Proceso[] ejecucion, LinkedList<Proceso> bloqueados, LinkedList<Proceso> finalizados) {
        actualizarPrioridades(colaListos, ejecucion, bloqueados);

        //EJECUCION: 
        //chequear si finalizo un proceso
        for (Proceso p : ejecucion) {
            if (ejecucion[0].duracionTotalEjecucion == 0) { //ver despues para mas de un cpu.
                finalizados.add(ejecucion[0]);
                ejecucion[0] = (Proceso) colaListos.poll();
            }
            else {  //Modificar tiempos de ejecucion.
                p.duracionTotalEjecucion--;
            }
        }
        
        for (Proceso p : ejecucion) {
            if (p.duracionTotalEjecucion % p.intervaloBloqueo[0] == 0) {
                bloqueados.add(p);
                ejecucion[0] = colaListos.poll(); //ver caso de mas de 1 proceso(se necesita indice)
            }
        }
        for (Proceso p : ejecucion) {
            if (p.prioridad < colaListos.peek().prioridad) {
                colaListos.add(p);
                ejecucion[0] = colaListos.poll(); //ver caso de mas de 1 proceso(se necesita indice)
            }
        }
        //BLOQUEADOS
        for (Proceso p : bloqueados) {
            if (p.tiempoBloqueo == 0) {
                p.tiempoBloqueo = p.intervaloBloqueo[1];
                colaListos.add(p);
                bloqueados.remove(p);
            } else {
                p.tiempoBloqueo--;
            }
        }
    }

    @Override
    public void actualizarPrioridades(PriorityQueue<Proceso> colaListos, Proceso[] ejecucion, LinkedList<Proceso> bloqueados) {
        for (Proceso p : colaListos) {
            p.prioridad++;
        }
        for (Proceso p : ejecucion) {
            p.prioridad--;
        }
        for (Proceso p : bloqueados) {
            p.prioridad += 2;
        }
    }

}
