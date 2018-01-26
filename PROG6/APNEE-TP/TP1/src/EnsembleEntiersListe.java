import java.lang.Exception;

public class EnsembleEntiersListe implements EnsembleEntiers{
	class Element{
		int valeur;
		Element suivant;
	}

	Element list = new Element();

   	public void ajoute(int element){
			Element resultat = new Element();
			resultat.valeur = element;
			resultat.suivant = list;
			list = resultat;
	}

	public void supprime(int element) throws Exception{
		Element tmp = list;
		int entier = 0;
		while (tmp.suivant != null && tmp.suivant.valeur != element){
			tmp = tmp.suivant;
		}
		if(tmp.suivant != null){
			entier = tmp.suivant.valeur;
			tmp.suivant = tmp.suivant.suivant;
		} else {
			throw new Exception(element + " non trouvé");
		}
	}
}