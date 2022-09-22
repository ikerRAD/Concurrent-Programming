/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab01;

/**
 *
 * @author 2227094
 */
public class MyThread extends Thread {
    
    public MyThread(String name) {
        super(name) ; // Thread super-class constructor to initialise thread name
    }
    
    public MyThread() {
        super() ; // Thread super-class constructor if we don’t need a thread name
    }
    
    /* override the inherited Thread's empty method run()
    * the method name run() is important here; whenever a thread starts
    * it expects to call a method with the signature public void run() */
    @Override public void run() {
        /* put code to be executed when this thread starts in the run() method.
        * This example just prints the thread name and a count. */
        String tname = Thread.currentThread().getName() ;
        int count = 0;
        while(!interrupted()) {
            System.out.println("Thread name: " + tname + " count: " + count++) ;
        }
    }

    
}
