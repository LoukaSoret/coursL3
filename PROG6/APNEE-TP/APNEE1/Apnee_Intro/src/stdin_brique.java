import java.util.Scanner;

class stdin_brique {
    public static void main(String [] args) {
        Scanner my_scanner;
        int resistance = 0;
        float x=0, y=0;
        boolean isNotInteger;
        BriqueInitiale brique;

        // Lecture de la resistance
        do{
        	my_scanner = new Scanner(System.in);
        	System.out.println("Saisissez la resistance de la brique:");
	        try {
	        	resistance = my_scanner.nextInt();
	        	System.out.println("Vous avez saisi la resistance : " + resistance);
	        	isNotInteger = false;
	        } catch (java.util.InputMismatchException ex) {
	        	System.out.println("Ce n'est pas un integer.");
				isNotInteger = true;
	        } catch (java.util.NoSuchElementException ex) {
	        	System.out.println("Aucune ligne saisie");
	        	isNotInteger = true;
	        }
        } while (isNotInteger);

       	brique = new BriqueInitiale(resistance);

       	// Lecture de la position selon x et y
        do{
        	my_scanner = new Scanner(System.in);
        	System.out.println("Saisissez la position x:");
	        try {
	        	x = my_scanner.nextFloat();
	        	System.out.println("Vous avez saisi la position selon x : " + x);
	        	isNotInteger = false;
	        } catch (java.util.InputMismatchException ex) {
	        	System.out.println("Ce n'est pas un float.");
				isNotInteger = true;
	        } catch (java.util.NoSuchElementException ex) {
	        	System.out.println("Aucune ligne saisie");
	        	isNotInteger = true;
	        }
        } while (isNotInteger);
        do{
        	my_scanner = new Scanner(System.in);
        	System.out.println("Saisissez la position y:");
	        try {
	        	y = my_scanner.nextFloat();
	        	System.out.println("Vous avez saisi la position selon y : " + y);
	        	isNotInteger = false;
	        } catch (java.util.InputMismatchException ex) {
	        	System.out.println("Ce n'est pas un float.");
				isNotInteger = true;
	        } catch (java.util.NoSuchElementException ex) {
	        	System.out.println("Aucune ligne saisie");
	        	isNotInteger = true;
	        }
        } while (isNotInteger);

        brique.fixePosition(x,y);

        System.out.println("Vous avez cree une " + brique);
    }
}