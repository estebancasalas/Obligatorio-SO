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
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //asignar procesos al cpu, actualizar las prioridades, actualizar tiempos, chequear bloqueos
//what you want to do.
            }
        }, 0, 1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)
    }
    
}
