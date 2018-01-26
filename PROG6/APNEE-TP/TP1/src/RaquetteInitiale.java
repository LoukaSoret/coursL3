

public class RaquetteInitiale implements ComposantInitial {
    Point pos;

    RaquetteInitiale() {
        pos = new Point(0, 0);
    }

    @Override
    public String toString() {
        return "Raquette en (" + pos.x() + ", " + pos.y() + ")";
    }

    @Override
    public ComposantInitial clone() {
        RaquetteInitiale resultat = new RaquetteInitiale();
        resultat.fixePosition(pos.x(), pos.y());
        return resultat;
    }

    @Override
    public void fixePosition(float x, float y) {
        pos.fixe(x, y);
    }
}
