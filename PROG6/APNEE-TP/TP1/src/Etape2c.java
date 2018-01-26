
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
public class Etape2c {
    // On reprend la structure de l'étape 1, mais tout est créé via une
    // méthode d'un objet "principal". Nous verrons plus tard que cela nous
    // permettra de ne changer qu'une partie sans tout réécrire.
    FabriqueNiveaux creerFabriqueNiveaux(FabriqueComposantsInitiale fc) {
        return new FabriqueNiveauxTestEnsembles(fc);
    }

    FabriqueComposantsInitiale creerFabriqueComposants() {
        return new FabriqueComposantsInitiale();
    }

    JeuInitial creerJeu() {
        return new JeuInitial(creerFabriqueNiveaux(creerFabriqueComposants()));
    }
    
    void demarrer() {
        JeuInitial j = creerJeu();
        deroulementJeu(j);
    }
    
    void deroulementJeu(JeuInitial j) {
        while (j.rafraichit()) {
        }
    }

    public static void main(String[] args) {
        // Ici c'est juste technique : on crée un objet pour que les
        // méthode apelées ne soient pas statiques et puissent plus tard
        // être redéfinies
        Etape2c principal = new Etape2c();
        principal.demarrer();
    }
}
