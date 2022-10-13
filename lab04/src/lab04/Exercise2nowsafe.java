package lab04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/* File: Example2_unsynchronized.java    
 * Starting point CM31133 Lab3 Exercise 2 */
/**
 * This version of exercise 2 is unsynchronized. The version suffers from "lost
 * updates" to the shared static variable resource and used.
 */
public class Exercise2nowsafe {

    public static void main(String[] args) {
        long NUMBER_RESOURCES = 100000000L;
        ResourceUser.setResources(NUMBER_RESOURCES);
        ArrayList<ResourceUser> users = new ArrayList();
        int N = 10;
        ReentrantLock lock = new ReentrantLock();
        
        ExecutorService exserv = Executors.newFixedThreadPool(N);
        
        
        for(int i = 0 ; i < N; i++){
            
            ResourceUser restmp = new ResourceUser("user"+i, lock);
            exserv.execute(restmp);
            users.add(restmp);
        }
        
        //for(ResourceUser user: users) user.start();
        
        System.out.println("Total should be constant:" + NUMBER_RESOURCES);
        while (ResourceUser.getResourceLeft() > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            System.out.println(ResourceUser.getReport(users)
            );
        }
    }

    /* Nested class for our ResourceUser objects */
    static class ResourceUser extends Thread {

        private static long resource = 100000000L;
        private static long used = 0L;
        private ReentrantLock locker;

        public ResourceUser(String name, ReentrantLock lock) {
            super(name);
            locker = lock;
        }

        public void run() {
            while (resource > 0) {
                try{
                    locker.lock();
                    takeResource();
                    //try{Thread.sleep(0L);}catch(InterruptedException ex){}
                    useResource();
                }catch(Exception e){
                    System.out.println("error");
                }finally{
                    locker.unlock();
                }
            }
        }

        public static void takeResource() {
            resource--;
        }

        public static void useResource() {
            used++;
        }

        public static long getResourceLeft() {
            return resource;
        }

        public static long getResourceUsed() {
            return used;
        }

        public static String getReport(List<ResourceUser> users) {
            String report = "";
            report = "Remaining = " + resource + "   Used = " + used + "  Total = " + (resource + used);
            return report;
        }

        public static void setResources(long nResources) {
            resource = nResources;
        }
    }
}
