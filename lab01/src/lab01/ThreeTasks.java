/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab01;

import java.time.Duration;
import java.time.LocalTime;
public class ThreeTasks{

 public static final long MAX = 100000000L ;

 public static void main(String[] args){
 LocalTime start = LocalTime.now();
 System.out.println("Main thread started at " + start);

 task(0) ;
 task(1) ;
 task(2) ;
 LocalTime finish = LocalTime.now();
 System.out.println("Main thread ended at " + finish
 + " after runing for " + Duration.between(start, finish).toMillis()
 + " milliseconds") ;
 }

 public static void task(int id){
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
