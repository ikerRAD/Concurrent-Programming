
package lab04;


import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author DAVID
 */
public class Exercise04nowsafe {
    
    
    public static void main(String[] args) {
          
        int N = 10;
        Data data = new Data();
        
        ExecutorService e = Executors.newFixedThreadPool(N);
        for(int i =0 ; i < N; i++){
            Speaker s = new Speaker(i, data);
            e.execute(s);
        }
        
        e.shutdown();
        try {
            e.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
        }
              
        System.out.println( data.record + "\n" + " has " + data.getNumberRecords() + " entries");
        System.out.println("Number messages heard by speakers was " + data.getCounts());
        System.out.println("Total of messages heard was " + data.getSumMessages());
    }
    
    public static class Data{
        CopyOnWriteArrayList<String> record;
        ConcurrentHashMap<Speaker, Integer> counts = new ConcurrentHashMap();
        
        public Data(){
            record = new CopyOnWriteArrayList();
            counts = new ConcurrentHashMap();
        }
        
        public synchronized void addRecord(String s){
            record.add(s);
        }
        
        public synchronized void sortRecord(){
            Collections.sort(record);
        }
        
        public int getNumberRecords(){
            return record.size();
        }
        
        public synchronized void addSpeaker(Speaker s){
            counts.put(s,0);
        }
        
        public synchronized void addMessage(Speaker source){
            if(!counts.containsKey(source)) addSpeaker(source);
            for(Speaker s: counts.keySet()){
                if(!s.equals(source)) {
                    int m = counts.get(s);
                    counts.put(s, m+1);
                }
            }
        }
        
        public int getSumMessages(){
            int sum = 0;
            for(Speaker s: counts.keySet()){
                sum += counts.get(s);
            }
            return sum;
        }
        
        public ConcurrentHashMap<Speaker, Integer> getCounts(){
            return counts;
        }
    }
    
    public static class Speaker extends Thread{
        Data data;
        String id;
        
        public Speaker(int id, Data d){
            this.id = id+"";
            this.data = d;
        }
        
        @Override public void run(){
            try{Thread.sleep(10L);}catch(InterruptedException e){}
            while(data.getNumberRecords() < 100){            
                String phrase = randomPhrase();
                data.addRecord(phrase);
                data.sortRecord();
                data.addMessage(this);   
                System.out.println(phrase + " was added to record");
            }
        }
        
        public String randomPhrase(){
            String phrase = "";
            for(int L = 1; L <=4 ; L++){
                phrase = phrase + (char)Math.floor(Math.random()*26 + 65 + (Math.random()>0.5?0:32));
            }
            return phrase;
        }
        
        @Override public String toString(){
            return "Speaker"+id;
        }
    }
    
}
