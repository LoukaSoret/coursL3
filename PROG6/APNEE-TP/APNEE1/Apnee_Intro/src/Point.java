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


public class Point implements Cloneable {

    private float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Point clone() throws CloneNotSupportedException {
        return (Point) super.clone();
    }

    public float x() {
        return x;
    }
    
    public float y() {
        return y;
    }
    
    public Point fixe(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Point mult(float s) {
        return fixe(x()*s, y()*s);
    }

    public Point ajoute(float x, float y) {
        return fixe(x() + x, y() + y);
    }
    
    public Point ajoute(Point p) {
        return ajoute(p.x(), p.y());
    }

    public float normeCarree() {
        return x() * x() + y() * y();
    }

    public float norme() {
        return (float) Math.sqrt(normeCarree());
    }

    public Point normalise() {
        return mult(1.0f / norme());
    }

    public float produitScalaire(Point p) {
        return x() * p.x() + y() * p.y();
    }

    @Override
    public String toString() {
        return "(" + x() + ", " + y() + ")";
    }
}
