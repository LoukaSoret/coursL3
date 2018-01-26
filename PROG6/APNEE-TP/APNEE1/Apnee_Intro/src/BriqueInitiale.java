

public class BriqueInitiale implements ComposantInitial {
    int resistance;
    Point pos;

    BriqueInitiale(int r) {
        resistance = r;
        pos = new Point(0, 0);
    }

    @Override
    public String toString() {
        return "Brique en (" + pos.x() + ", " + pos.y() + "), resistance " + resistance;
    }

    @Override
    public ComposantInitial clone() {
        BriqueInitiale resultat = new BriqueInitiale(resistance);
        resultat.fixePosition(pos.x(), pos.y());
        return resultat;
    }

    @Override
    public void fixePosition(float x, float y) {
        pos.fixe(x, y);
    }
}
