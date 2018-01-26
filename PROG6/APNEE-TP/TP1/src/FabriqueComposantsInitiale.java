

public class FabriqueComposantsInitiale {
    public ComposantInitial brique(int resistance) {
        return new BriqueInitiale(resistance);
    }

    public ComposantInitial bonus(String nature) {
        return new BonusInitial(nature);
    }

    public ComposantInitial bordHorizontal(float taille) {
        return new BriqueInitiale(0);
    }

    public ComposantInitial bordVertical(float taille) {
        return new BriqueInitiale(0);
    }
    
    public ComposantInitial raquette() {
        return new RaquetteInitiale();
    }

    public ComposantInitial copie(ComposantInitial modele, float x, float y) {
        ComposantInitial resultat = modele.clone();
        resultat.fixePosition(x, y);
        return resultat;
    }
}
