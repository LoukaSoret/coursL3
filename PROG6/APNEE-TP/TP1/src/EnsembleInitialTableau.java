import java.util.NoSuchElementException;

public class EnsembleInitialTableau<T> implements EnsembleInitial<T>{
	int taille = 0;
	T[] tableau = (T[]) new Object[32];

   	public void ajoute(T element){
   		if ( taille >= tableau.length ){
   			int i;
   			T[] newTableau =(T[]) new Object[tableau.length*2];
   			for(i=0; i<taille-1; i++){
   				newTableau[i] = tableau[i];
   			}
   			tableau = newTableau;
   		}
   		tableau[taille] = element;
   		taille ++;
   	}

   	public void supprime(T element){
   		int i = 0;
   		while(!tableau[i].equals(element) && i < taille-1){
   			i++;
   		}
   		if (tableau[i].equals(element)){
   			tableau[i] = tableau[taille-1];
   			taille --;
   		} else {
            throw new NoSuchElementException();
   		}
   	}
}