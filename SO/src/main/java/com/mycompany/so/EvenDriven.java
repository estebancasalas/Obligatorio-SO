/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.so;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collection;

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
    public Object[] planificar(PriorityQueue<Proceso> colaListos, ArrayList<Proceso> ejecucion, LinkedList<Proceso> bloqueados, LinkedList<Proceso> finalizados) {
        Object[] arr = new Collection[4];

        actualizarPrioridades(colaListos, ejecucion, bloqueados);
        if (ejecucion.size() == 0) {
            ejecucion.add(null);
        }
        //EJECUCION: 
        //chequear si finalizo un proceso
        for (int i = 0; i < ejecucion.size(); i++) {
            Proceso p = ejecucion.get(i);
            if (p != null) {
                if (ejecucion.get(0).duracionTotalEjecucion == 0) { //ver despues para mas de un cpu.
                    finalizados.add(ejecucion.get(0));
                    ejecucion.set(0, (Proceso) colaListos.poll());

                } else if (p.tiempoBloqueo == p.intervaloBloqueo[0]) {
                    p.tiempoBloqueo = p.intervaloBloqueo[1];
                    bloqueados.add(p);
                    ejecucion.set(0, (Proceso) colaListos.poll()); //ver caso de mas de 1 proceso(se necesita indice)

                } else if (colaListos.peek() != null) {
                    if (p.prioridad < colaListos.peek().prioridad) {
                        colaListos.add(p);
                        ejecucion.set(0, colaListos.poll()); //ver caso de mas de 1 proceso(se necesita indice)
                    }
                }
                p.duracionTotalEjecucion--;//Modificar tiempos de ejecucion.
                p.tiempoBloqueo++;

            } else {
                ejecucion.set(0, colaListos.poll()); //ver caso de mas de 1 proceso(se necesita indice)
            }
        }
        /*
        for (Proceso p : ejecucion) {
            if (p != null) {
                if (ejecucion.get(0).duracionTotalEjecucion == 0) { //ver despues para mas de un cpu.
                    finalizados.add(ejecucion.get(0));
                    ejecucion.add(0, (Proceso) colaListos.poll());
                } else {  //Modificar tiempos de ejecucion.
                    p.duracionTotalEjecucion--;
                }
            }
        }

        for (Proceso p : ejecucion) {
            if (p != null) {
                if (p.duracionTotalEjecucion % p.intervaloBloqueo[0] == 0) {
                    bloqueados.add(p);
                    ejecucion.add(0, (Proceso) colaListos.poll()); //ver caso de mas de 1 proceso(se necesita indice)
                }
            }
        }
        for (Proceso p : ejecucion) {
            if (p != null) {
                if (p.prioridad < colaListos.peek().prioridad) {
                    colaListos.add(p);
                    ejecucion.add(0, colaListos.poll()); //ver caso de mas de 1 proceso(se necesita indice)
                }
            }
        }
        for (Proceso p : ejecucion) {
            if (p == null) {
                ejecucion.add(0, colaListos.poll()); //ver caso de mas de 1 proceso(se necesita indice)
            }
        }
*/
        //BLOQUEADOS
        for (Proceso p : bloqueados) {
            if (p.tiempoBloqueo == 0) {
                colaListos.add(p);
                bloqueados.remove(p);
            } else {
                p.tiempoBloqueo--;
            }
        }
         
        arr[0] = colaListos;
        arr[1] = ejecucion;
        arr[2] = bloqueados;
        arr[3] = finalizados;
        return arr;
    }

    @Override
    public void actualizarPrioridades(PriorityQueue<Proceso> colaListos, ArrayList<Proceso> ejecucion, LinkedList<Proceso> bloqueados) {
        for (Proceso p : colaListos) {

            if (p != null) {
                p.prioridad = Integer.min(99, p.prioridad + 5);
            }
        }

        for (Proceso p : ejecucion) {
            if (p != null) {
                p.prioridad = Integer.max(0, p.prioridad - 5);
            }
        }
        for (Proceso p : bloqueados) {
            if (p != null) {
                p.prioridad = Integer.min(99, p.prioridad + 10);
            }
        }
    }

}
