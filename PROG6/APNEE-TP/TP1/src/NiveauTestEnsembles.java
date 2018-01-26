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

public class NiveauTestEnsembles implements Niveau {
    // Pour tester les deux implémentations, on stocke tous les objets en double
    protected EnsembleInitial<ComposantInitial> liste;
    protected EnsembleInitial<ComposantInitial> tableau;
    NiveauInitial niv;
    FabriqueComposantsInitiale fab;

    public NiveauTestEnsembles(String n, FabriqueComposantsInitiale f) {
        // On ne connait pas encore l'héritage, au programme du prochain cours.
        // On utilise donc la délégation : on crée un objet de la classe
        // NiveauInitial précédemment écrite et on l'utilise pour toutes les
        // parties n'ayant pas changé.
        niv = new NiveauInitial(n, f);
        fab = f;
        initialiseEnsembles();
    }
    
    protected void initialiseEnsembles() {
        liste = new EnsembleInitialListe<>();
        tableau = new EnsembleInitialTableau<>();
    }

    public void nouvelleBalle() {
        System.out.println("Terminé, voici les composants que j'ai stocké :");
        System.out.println("- dans l'ensemble implémenté dans un tableau");
        System.out.println(tableau);
        System.out.println("- dans l'ensemble implémenté dans une liste");
        System.out.println(liste);
        niv.nouvelleBalle();
    }

    public void ajouteComposant(ComposantInitial composant, float x, float y) {
        ComposantInitial nouveau = fab.copie(composant, x, y);
        liste.ajoute(nouveau);
        tableau.ajoute(nouveau);
    }

    public void fixerDimensions(float l, float h) {
        niv.fixerDimensions(l, h);
    }
}
