/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.so;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * @author ecasa
 */
public interface IPlanificador {
    public Object[] planificar(PriorityQueue<Proceso> colaListos, ArrayList<Proceso> ejecucion, LinkedList<Proceso> bloqueados, LinkedList<Proceso> finalizados);
    public void actualizarPrioridades(PriorityQueue<Proceso> colaListos, ArrayList<Proceso> ejecucion, LinkedList<Proceso> bloqueados);
}
