/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab01;

/**
 *
 * @author 2227094
 */
public class Ex2 {
    
    public static void main(String[] args) {
        
        MyThread t0 = new MyThread("t0");
        MyThread t1 = new MyThread("t1");
        MyThread t2 = new MyThread("t2");
        
//        t0.setDaemon(true);
//        t1.setDaemon(true);
//        t2.setDaemon(true);        
        
        t0.run();
        t1.run();
        t2.run();
        
        try{
            Thread.sleep(1000L);
        }catch (InterruptedException ex){
            //nothing
        }
        
        t0.interrupt();
        t1.interrupt();
        t2.interrupt();
        
    }
    
}
