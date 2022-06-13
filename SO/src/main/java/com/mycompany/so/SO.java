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
    public IPlanificador planificador;
    private PriorityQueue<Proceso> colaListos;
    private ArrayList<Proceso> ejecucion;
    public LinkedList<Proceso> bloqueados;
    public LinkedList<Proceso> finalizados;
    public boolean prendido;
    
    //Actualiza tiempo, y llama planificador periodicamente para mover entre las listas y actualizar prioridades.

    public SO(CPU cpu, LinkedList<Proceso> procesos, IPlanificador planificador) {
        this.cpu = cpu;
        this.planificador = planificador;
        this.colaListos = new PriorityQueue<Proceso>();
        this.ejecucion = new ArrayList<Proceso>(cpu.cores.length);
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
        this.ejecucion = new ArrayList<Proceso>(cpu.cores.length);
        this.bloqueados = new LinkedList<Proceso>();
        this.finalizados = new LinkedList<Proceso>();
        this.prendido = true;
    }

    public void ejecutar() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Object[] arr = planificador.planificar(colaListos, ejecucion, bloqueados, finalizados);
                PriorityQueue<Proceso> cl = (PriorityQueue<Proceso>) arr[0];
                colaListos = cl;
                ArrayList<Proceso> ej = (ArrayList<Proceso>) arr[1];
                ejecucion = ej;
                LinkedList<Proceso> b = (LinkedList<Proceso>) arr[2];
                bloqueados = b;
                LinkedList<Proceso> f = (LinkedList<Proceso>) arr[3];
                finalizados = f;
                for (Proceso p : colaListos) {
                    System.out.print(p.id);
                }
                System.out.println();
                for (Proceso p : ejecucion) {
                    if (p != null){
                        System.out.print(p.id);
                    }
                }
                System.out.println();
                for (Proceso p : bloqueados) {
                    System.out.print(p.id);
                }
                System.out.println();
                for (Proceso p : finalizados) {
                    System.out.print(p.id);
                }
                System.out.println();
            }
        }, 0, 1000);
    }
    public void crearProceso(Proceso p){
        colaListos.add(p);
    }
}
