

import java.io.InputStream;

public class JeuInitial {
    FabriqueNiveaux fabNiv;
    int niveauCourant;
    Niveau n;
    
    public Niveau niveau() {
    		return n;
    }
    
    public JeuInitial(FabriqueNiveaux fn) {
        fabNiv = fn;
        niveauCourant = 1;
    }

    protected void prochainNiveau() {
    	String fileName = "Niveaux/Niveau-" + niveauCourant + ".txt";
        InputStream in = Chargeur.donnees(fileName);

        if (in != null) {
            ChargeurNiveaux ch = new ChargeurNiveaux(in, fabNiv.fabriqueComposants());
            n = fabNiv.niveau("Niveau " + (niveauCourant));
            ch.lisNiveau(n);
            niveauCourant++;
        } else {
        		throw new RuntimeException(fileName + " introuvable");
        }
    }
    
    public boolean rafraichit() {
        prochainNiveau();
        return true;
    }
}
