/* File: Example1_unsynchronizedExercise4.java    Starting point CM3113 Lab4 Exercise 1 */
package lab04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This version of exercise 1 is unsynchronized. The version suffers from "lost
 * updates" to the shared object countShared. The effect of lost updates is
 * easily verified by running each of the CountingThread threads for a fixed
 * number of iterations and comparing the final sum of theLoopCount for each
 * thread and theTotalLoopCount
 */
public class Exercise1nowsafe {
    
    public static boolean running;

    public static void main(String[] args) {
        
        ReentrantLock lock = new ReentrantLock();
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        Counter sharedCounter = new Counter();
        CountingThread t1 = new CountingThread(counter1, sharedCounter, "t1", lock);
        CountingThread t2 = new CountingThread(counter2, sharedCounter, "t2", lock);
        t1.start();
        t2.start();

        running = true;
        while(running) { // wake up main() occasionally and test state of counters
            long count1, count2, countTotal;
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
            }

            /* capture state of current counts */
                count1 = counter1.getCount();
                count2 = counter2.getCount();
                countTotal = sharedCounter.getCount();

            System.out.println("Actual C1 + C2: " + (count1 + count2)
                    + ", Total Recorded " + countTotal
                    + ", Lost: " + (count1 + count2 - countTotal));
            
            if(countTotal > 100000000) running = false;
        }
    }
    
    static class Counter { /* Nested class for Counter objects */

        private volatile long theCount;

        public Counter() {
            theCount = 0L;
        }

        public void increment() {
            theCount++;
        }

        public long getCount() {
            return theCount;
        }
    }
    
    /* creating a nested class for the CountingThread - could make as 
     * separate file but since CountingClass is only used in Example 1
     * saves having multiple files and naming conflicts if we make changes */
    static class CountingThread extends Thread {

        private Counter thisCounter;
        private Counter sharedCounter;
        private ReentrantLock locker;

        public CountingThread(Counter counter, Counter shared, String name, ReentrantLock lock) {
            super(name);
            thisCounter = counter;
            sharedCounter = shared;
            locker = lock;
            this.setDaemon(true);
        }

        public void run() {
            while(true) {   // start of another loop
                //try {Thread.sleep(10L);} catch (InterruptedException e) {}
                thisCounter.increment();  // count one more loop for this thread 
                try{
                    locker.lock();
                    sharedCounter.increment();  
                }catch (Exception e){
                    System.out.println("error");
                }finally{
                    locker.unlock();
                }
                //sharedCounter.increment();  // count one more loop for all threads
            }
        }
    }
}





