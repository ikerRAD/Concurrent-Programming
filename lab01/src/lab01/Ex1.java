/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab01;

/**
 *
 * @author 2227094
 */
public class Ex1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyRunnable r = new MyRunnable();
        
        Thread t0 = new Thread(r, "t0");
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");
        
        t0.start();
        t1.start();
        t2.start();
    }
    
}
