import java.io.*;

//
// args : LesAchats_petit LesVins Res_Nested Res_Hashed
//

class EssaiJoin {
    public static void main(String [] args) {
        minus j;
        try {
            j = new minus(args[0],args[1],args[2]);
            j.NestedJoin();
            j.ChangeOutput(args[3]);
            //j.HashJoin();
        } catch (Exception e) {
            System.out.println(e);
        }
   }
}