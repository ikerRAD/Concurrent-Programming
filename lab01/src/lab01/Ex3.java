/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab01;

import java.time.Duration;
import java.time.LocalTime;

/**
 *
 * @author 2227094
 */
public class Ex3 {
    
    public static void main(String[] args) {
        
        LocalTime start = LocalTime.now();
        System.out.println("Main thread started at " + start);
        
        Task r = new Task();
        
        Thread t0 = new Thread(r, "t0");
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");
        
        t0.start();
        t1.start();
        t2.start();
        
        LocalTime finish = LocalTime.now();
        System.out.println("Main thread ended at " + finish
        + " after runing for " + Duration.between(start, finish).toMillis()
        + " milliseconds") ;
        
        
        System.out.println(Thread.MIN_PRIORITY);
        System.out.println(Thread.MAX_PRIORITY);
        System.out.println(Thread.NORM_PRIORITY);
        
        System.out.println("Main thread priority " + Thread.currentThread().getPriority());
        System.out.println("t0 thread priority " + t0.getPriority());
        System.out.println("t1 thread priority " + t1.getPriority());
        System.out.println("t2 thread priority " + t2.getPriority());
        
    }
    
}
