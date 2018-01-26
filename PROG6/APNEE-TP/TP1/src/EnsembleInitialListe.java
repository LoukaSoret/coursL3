import java.util.NoSuchElementException;

public class EnsembleInitialListe<T> implements EnsembleInitial<T>{
	class Element{
		T valeur;
		Element suivant;
	}

	Element list = new Element();

   	public void ajoute(T element){
			Element resultat = new Element();
			resultat.valeur = element;
			resultat.suivant = list;
			list = resultat;
	}

	public void supprime(T element){
		Element tmp = list;
		T elem;
		while (tmp.suivant != null && !tmp.suivant.valeur.equals(element)){
			tmp = tmp.suivant;
		}
		if(tmp.suivant != null){
			elem = tmp.suivant.valeur;
			tmp.suivant = tmp.suivant.suivant;
		} else {
			throw new NoSuchElementException();
		}
	}
}