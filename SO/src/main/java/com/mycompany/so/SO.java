/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.so;

import java.util.*;
/**
 *
 * @author ecasa
 */

public class SO {
    private CPU cpu;
    private IPlanificador planificador;
    private PriorityQueue<Proceso> colaListos;
    private Proceso[] ejecucion;
    public LinkedList<Proceso> bloqueados;
    public LinkedList<Proceso> finalizados;
    public boolean prendido;
    
    //Actualiza tiempo, y llama planificador periodicamente para mover entre las listas y actualizar prioridades.

    public SO(CPU cpu, LinkedList<Proceso> procesos, IPlanificador planificador) {
        this.cpu = cpu;
        this.planificador = planificador;
        this.colaListos = new PriorityQueue<Proceso>();
        this.ejecucion = new Proceso[cpu.cores.length];
        this.bloqueados = new LinkedList<Proceso>();
        this.finalizados = new LinkedList<Proceso>();
        this.prendido = true;
        for (Proceso p : procesos) {
            this.colaListos.add(p);
        }
    }
    public SO(CPU cpu, IPlanificador planificador) {
        this.cpu = cpu;
        this.planificador = planificador;
        this.colaListos = new PriorityQueue<Proceso>();
        this.ejecucion = new Proceso[cpu.cores.length];
        this.bloqueados = new LinkedList<Proceso>();
        this.finalizados = new LinkedList<Proceso>();
        this.prendido = true;
    }

    public void ejecutar() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                while (prendido) {
                    planificador.planificar(colaListos, ejecucion, bloqueados, finalizados);
                }
                for (Proceso p : colaListos) {
                    System.out.println(p.id);
                }
                for (Proceso p : ejecucion) {
                    System.out.println(p.id);
                }
                for (Proceso p : bloqueados) {
                    System.out.println(p.id);
                }
                for (Proceso p : finalizados) {
                    System.out.println(p.id);
                }
            }
        }, 0, 1000);
    }
    public void crearProceso(Proceso p){
        colaListos.add(p);
    }
}
