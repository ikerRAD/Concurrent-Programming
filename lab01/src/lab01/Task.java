/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab01;

import java.time.Duration;
import java.time.LocalTime;
import static lab01.ThreeTasks.MAX;

/**
 *
 * @author 2227094
 */
public class Task implements Runnable{
    
    @Override public void run(){
        String id = Thread.currentThread().getName();
        LocalTime start = LocalTime.now();
        System.out.println("Task " + id + " started at " + start);
        long sum = 0 ;
        for (long i = 0 ; i < MAX ; i++) // this creates a time-consuming loop
        sum ++ ;
        LocalTime finish = LocalTime.now();
        System.out.println("Task " + id + " ended at " + finish
        + " after running for " + Duration.between(start,finish).toMillis()
        + " milliseconds, with sum = " + sum ) ;
    }
    
}
