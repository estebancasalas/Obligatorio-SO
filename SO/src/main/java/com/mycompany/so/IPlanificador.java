/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.so;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author ecasa
 */
public interface IPlanificador {
    public void planificar(PriorityQueue<Proceso> colaListos, Proceso[] ejecucion, LinkedList<Proceso> bloqueados, LinkedList<Proceso> finalizados);
    public void actualizarPrioridades(PriorityQueue<Proceso> colaListos, Proceso[] ejecucion, LinkedList<Proceso> bloqueados);
}
