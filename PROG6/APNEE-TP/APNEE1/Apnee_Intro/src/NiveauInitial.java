/*
 * Armoroides - casse briques à but pédagogique
 * Copyright (C) 2016 Guillaume Huard

 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).

 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.

 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.

 * Contact: Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */


public class NiveauInitial implements Niveau {
    String nom;
    protected float largeur;
	protected float hauteur;
    FabriqueComposantsInitiale fab;

    public NiveauInitial(String n, FabriqueComposantsInitiale f) {
        nom = n;
        fab = f;
        init();
    }
    
    void init() {
            System.out.println("Niveau : " + nom);
    }

    public void nouvelleBalle() {        
        System.out.println("Le niveau a une largeur de " + largeur + " et une hauteur de " + hauteur);
        System.out.println("Prêt à jouer, en attente de balle !");
    }

    public void ajouteComposant(ComposantInitial c, float x, float y) {
        System.out.println(fab.copie(c, x, y));
    }
    
    public void fixerDimensions(float l, float h) {
        if (l > largeur)
            largeur = l;
        if (h > hauteur)
            hauteur = h;
    }
    
    @Override
    public String toString() {
    		return nom;
    }
}
