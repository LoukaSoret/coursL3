import java.io.*;
import java.util.Scanner;
import java.util.Hashtable;


class distinct {
    String f1,f2;
    PrintWriter out;

    distinct(String f1, String f2, String res) {
        this.f1 = f1;
        this.f2 = f2;
        try {
        out = new PrintWriter(new FileOutputStream(res));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void ChangeOutput(String res) {
        try {
        out = new PrintWriter(new FileOutputStream(res));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void NestedJoin() {
        Scanner in1;
        String t1 ;
        Integer l = 0;
        String [] t2_splited = new String[100000];
        String [] t1_splited;
        Boolean b;
        
        long startTime = System.currentTimeMillis();
        try {
            in1 = new Scanner(new FileInputStream(f1));
            while (in1.hasNext()) {
                t1 = in1.nextLine();
                t1_splited = t1.split("\t");
                b = false;
                for(String i: t2_splited){
                    if (t1_splited[1].equals(i)) {
                        b = true;
                    }
                }
                if(!b){
                    System.out.println(t1_splited[1]);
                    t2_splited[l]= t1_splited[1];
                    l++;
                }
            }
            in1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        out.close();
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Computation time, nested loop join: "+totalTime+" ms");
    } 
    
    public void HashJoin() {
        Scanner in1, in2;
        String t1,t2 ;
        String [] t2_splited;
        String [] t1_splited;
        Hashtable<String, String> hashed_t2 = new Hashtable<String, String>();
        long startTime = System.currentTimeMillis();
        try{
            in2 = new Scanner(new FileInputStream(f1));
            while (in2.hasNext()) {
                t2 = in2.nextLine();
                t2_splited = t2.split("\t");
                if(!hashed_t2.containsKey(String.valueOf(t2_splited[1].hashCode()))){
                    System.out.println(t2_splited[1]);
                    hashed_t2.put(String.valueOf(t2_splited[1].hashCode()),t2);
                }
            }
            in2.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        out.close();
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Computation time, hash join: "+totalTime+" ms");
    }
    
}
