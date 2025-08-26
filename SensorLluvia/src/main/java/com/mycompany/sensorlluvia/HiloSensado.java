package com.mycompany.sensorlluvia;
import java.io.PrintWriter;
import java.lang.Math;
import java.net.Socket;

public class HiloSensado extends Thread{
    public boolean on;
    public int lluvia;
    public float probabilidad;
    Socket cnxServidor;
    PrintWriter pw;
    
    public HiloSensado(Socket s, PrintWriter pw){
        this.on = true;
        this.lluvia = 0;
        this.probabilidad = 0;
        cnxServidor = s;
        this.pw = pw;
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
