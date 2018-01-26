/*
 * Armoroides - casse briques à but pédagogique
 * Copyright (C) 2016 Guillaume Huard
 * 
 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).
 * 
 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.
 * 
 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.
 * 
 * Contact: Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */

import java.util.Random;

public class Etape2a {

    static Random r;

    static void testEnsemble(String[] args, EnsembleEntiers e) {
        if (args.length > 0) {
            r = new Random(Integer.parseInt(args[0]));
        } else if (r == null) {
            r = new Random();
        }

        System.out.println("Entiers :");
        for (int i = 0; i < 100; i++) {
            int entier = r.nextInt(100);
            if (entier == 42) {
                System.out.print("*** " + entier + " *** ");
            } else {
                System.out.print(entier + " ");
            }
            e.ajoute(entier);
        }
        System.out.println();
        try {
            while (true) {
                e.supprime(42);
                System.out.println("42 trouvé, on l'enlève de l'ensemble");
            }
        } catch (Exception ex) {
            System.out.println("Plus de 42, on affiche");
        }
        System.out.println("Ensemble : " + e);
    }

    public static void main(String[] args) throws Exception {
        EnsembleEntiers e;

        e = new EnsembleEntiersListe();
        testEnsemble(args, e);
    }
}
