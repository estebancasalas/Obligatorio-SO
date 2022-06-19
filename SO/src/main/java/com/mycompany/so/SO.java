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

    public CPU cpu;
    public IPlanificador planificador;
    public PriorityQueue<Proceso> colaListos;
    public ArrayList<Proceso> ejecucion;
    public LinkedList<Proceso> bloqueados;
    public LinkedList<Proceso> bloqueadosManual;
    public LinkedList<Proceso> finalizados;
    public boolean prendido;

    //Actualiza tiempo, y llama planificador periodicamente para mover entre las listas y actualizar prioridades.
    public SO(CPU cpu, LinkedList<Proceso> procesos, IPlanificador planificador) {
        this.cpu = cpu;
        this.planificador = planificador;
        this.colaListos = new PriorityQueue<Proceso>();
        this.ejecucion = new ArrayList<Proceso>(cpu.cores.length);
        this.bloqueados = new LinkedList<Proceso>();
        this.bloqueadosManual = new LinkedList<Proceso>();
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
        this.bloqueadosManual = new LinkedList<Proceso>();
        this.finalizados = new LinkedList<Proceso>();
        this.prendido = false;
    }

    public void ejecutar() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (prendido) {
                    Object[] arr = planificador.planificar(colaListos, ejecucion, bloqueados, finalizados);
                    PriorityQueue<Proceso> cl = (PriorityQueue<Proceso>) arr[0];
                    colaListos = cl;
                    ArrayList<Proceso> ej = (ArrayList<Proceso>) arr[1];
                    ejecucion = ej;
                    LinkedList<Proceso> b = (LinkedList<Proceso>) arr[2];
                    bloqueados = b;
                    LinkedList<Proceso> f = (LinkedList<Proceso>) arr[3];
                    finalizados = f;
                }
            }
        }, 0, 1000);
    }

    public void crearProceso(Proceso p) {
        colaListos.add(p);
    }

    public void bloquearProceso(int id) {
        for (Proceso p : ejecucion) {
            if (p.id == id) {
                bloqueadosManual.add(p);
                ejecucion.remove(p);
                break;
            }
        }
        for (Proceso p : colaListos) {
            if (p.id == id) {
                bloqueadosManual.add(p);
                colaListos.remove(p);
                break;
            }
        }
    }

    public void desbloquearProceso(int id) {
        for (Proceso p : bloqueadosManual) {
            if (p.id == id) {
                colaListos.add(p);
                bloqueadosManual.remove(p);
                break;
            }
        }
    }
}
