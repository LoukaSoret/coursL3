import java.util.Scanner;

class Essai_Scanner {
    public static void main(String [] args) {
        Scanner my_scanner;
        int num = 0;
        boolean isNotInteger; 
        do{
        	my_scanner = new Scanner(System.in);
        	System.out.println("Saisissez une ligne");
	        try {
	        	num = my_scanner.nextInt();
	        	System.out.println("Vous avez saisi la ligne : " + num);
	        	isNotInteger = false;
	        } catch (java.util.InputMismatchException ex) {
	        	System.out.println("Ce n'est pas un integer.");
				isNotInteger = true;
	        } catch (java.util.NoSuchElementException ex) {
	        	System.out.println("Aucune ligne saisie");
	        	isNotInteger = false;
	        }
        } while (isNotInteger);
    }
}