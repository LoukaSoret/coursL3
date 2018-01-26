import java.lang.Exception;

public class EnsembleEntiersTableau implements EnsembleEntiers{
	int taille = 0;
	int[] tableau = new int[32];

   	public void ajoute(int element){
   		if ( taille >= tableau.length ){
   			int i;
   			int[] newTableau = new int[tableau.length*2];
   			for(i=0; i<taille-1; i++){
   				newTableau[i] = tableau[i];
   			}
   			tableau = newTableau;
   		}
   		tableau[taille] = element;
   		taille ++;
   	}

   	public void supprime(int element) throws Exception{
   		int i = 0;
   		while(tableau[i] != element && i < taille-1){
   			i++;
   		}
   		if (tableau[i] == element){
   			tableau[i] = tableau[taille-1];
   			taille --;
   		} else {
   			throw new Exception(element + " non trouvé");
   		}
   	}
}