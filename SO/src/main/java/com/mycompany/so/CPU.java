/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.so;

/**
 *
 * @author ecasa
 */
public class CPU {
    Proceso[] cores;
    int coresLibres;
    
    
    public CPU(int numCores){
        cores = new Proceso[numCores];
        coresLibres = numCores;
    }
    public void asignar(Proceso p){
        if (coresLibres > 0){
            int i = 0;
            boolean terminar = false;
            while (i < cores.length && !terminar){
                if (cores[i] == null){
                    cores[i] = p;
                    terminar = true;
                }
            }
        }
    }
    
    public void ejecutar(){
        
    }
    
}
