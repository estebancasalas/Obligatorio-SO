/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.so;

import java.util.LinkedList;
import java.util.PriorityQueue;

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
    
    //Actualiza tiempo, y llama planificador periodicamente para mover entre las listas y actualizar prioridades.
    
}
