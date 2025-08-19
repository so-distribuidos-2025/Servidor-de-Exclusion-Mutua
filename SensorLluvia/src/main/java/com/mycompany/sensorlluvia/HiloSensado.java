package com.mycompany.sensorlluvia;
import java.lang.Math;

public class HiloSensado extends Thread{
    public boolean on;
    public int lluvia;
    public float probabilidad;

    public HiloSensado(boolean on, int lluvia, float probabilidad) {
        this.on = on;
        this.lluvia = lluvia;
        this.probabilidad = probabilidad;
    }
    
    public HiloSensado(){
        this.on = true;
        this.lluvia = 0;
        this.probabilidad = 0;
    }
    
    public int generarLluvia(){
        return lluvia;
        
    }
    
    public void encender(){
        on = true;
    }
    
    public void apagar(){
        on = false;
    }
    
    /*Se genera una probabilida al azar para luego determinar si llueve o no*/
    public float probabilidadLluvia(){
        return ((float)Math.random() * 100);  
    }
    
    public void run(){
        /*En este while indica cuando llueve*/
        while(on){
            this.probabilidad = probabilidadLluvia();
            if (probabilidad > 50){
                this.lluvia= 1;
            }else{
                this.lluvia= 0;
            } 
            System.out.println("L: "+ generarLluvia());
            
            
            
        }
    }
}
