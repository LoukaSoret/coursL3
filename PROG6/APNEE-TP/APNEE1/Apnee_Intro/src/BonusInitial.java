public class BonusInitial implements ComposantInitial {
    String nom;
    Point pos;

    BonusInitial(String n) {
        nom = n;
        pos = new Point(0, 0);
    }

    @Override
    public String toString() {
        return "Bonus en (" + pos.x() + ", " + pos.y() + "), nom " + nom;
    }

    @Override
    public ComposantInitial clone() {
        BonusInitial resultat = new BonusInitial(nom);
        resultat.fixePosition(pos.x(), pos.y());
        return resultat;
    }

    @Override
    public void fixePosition(float x, float y) {
        pos.fixe(x, y);
    }
}
