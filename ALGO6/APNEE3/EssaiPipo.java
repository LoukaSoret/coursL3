import java.io.*;

//
// args : CRexemple NbdeMots
//

class EssaiPipo {
    public static void main(String [] args) {
        Pipo p;
        try {
            p = new Pipo(args[0]);
            p.Learn();
            p.Talk(Integer.parseInt(args[1]));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
