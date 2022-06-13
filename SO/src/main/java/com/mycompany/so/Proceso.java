/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.so;

/**
 *
 * @author ecasa
 */
public class Proceso implements Comparable {

    public int id;
    public String user;
    public String nombre;
    public Integer prioridad;
    public int duracionTotalEjecucion;
    public int[] intervaloBloqueo = new int[2]; //[0] cada cuanto se bloquea, [1] cuanto tiempo esta bloqueado.   
    public int tiempoBloqueo;

    public Proceso(int id, String user, String nombre, int prioridad, int duracionTotalEjecucion, int[] intervaloBloqueo) {
        this.id = id;
        this.user = user;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.duracionTotalEjecucion = duracionTotalEjecucion;
        this.intervaloBloqueo = intervaloBloqueo;
        this.tiempoBloqueo = intervaloBloqueo[1];

    }

    @Override
    public int compareTo(Object o) {
        Proceso p = (Proceso) o;
        return this.prioridad.compareTo(p.prioridad);

    }
}
