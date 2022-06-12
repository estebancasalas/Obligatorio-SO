/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.so;

import java.util.*;

/**
 *
 * @author ecasa
 */
public class Main {

    public static void main(String[] args) {
        Proceso a = new Proceso(8, "yo", "nombre", 3, 9, new int[]{4,1});
        Proceso b = new Proceso(9, "yo2", "nombre3", 6, 7,new int[]{3,2});
        Proceso c = new Proceso(10, "yo3", "nombre4", 8, 5,new int[]{2,3});
        EvenDriven scheduler = new EvenDriven();
        CPU cpu = new CPU(1);
        SO so = new SO(cpu, scheduler);
        so.crearProceso(a);
        so.crearProceso(b);
        so.crearProceso(c);
    }
    
}
