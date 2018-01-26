import java.io.InputStream;
import java.util.Scanner;


public class ChargeurNiveaux {
	Scanner scan;
	FabriqueComposantsInitiale construct;
	ComposantInitial[] all_ComposantInital = new ComposantInitial[10];

	public ChargeurNiveaux(InputStream stream,FabriqueComposantsInitiale builder ){
		scan = new Scanner(stream);
		construct = builder;
	}

	public void lisNiveau (Niveau n){
		int composantsID=0, resistance, layerID, longueur=0, hauteur=0;
		String line;
		String[] splitedLine;
		Boolean isInt, eof=false;

		line = scan.nextLine().replaceAll("\\s","");
		splitedLine = line.split(":");
		if( ! splitedLine[0].equals("Composants")){
			System.out.println("[Error] Section 'Composants' not found (" + splitedLine[0] + ")." );
		} else {
			do {
				line = scan.nextLine().replaceAll("\\s","");
				splitedLine = line.split(":");
				try{
					composantsID = Integer.parseInt(splitedLine[0]);
					isInt = true;
				} catch ( NumberFormatException e ) {
					isInt = false;
				} catch ( NullPointerException e ) {
					isInt = false;
				}
				if(isInt){
					line = scan.nextLine().replaceAll("\\s","");
					splitedLine = line.split(":");
					switch (splitedLine[1]){
						case "brique":
							line = scan.nextLine().replaceAll("\\s","");
							splitedLine = line.split(":");
							resistance = Integer.parseInt(splitedLine[1]);
							all_ComposantInital[composantsID]=construct.brique(resistance);
							break;
						case "bonus":
							line = scan.nextLine().replaceAll("\\s","");
							splitedLine = line.split(":");
							all_ComposantInital[composantsID]=construct.bonus(splitedLine[1]);
							break;
						case "raquette":
							all_ComposantInital[composantsID]=construct.raquette();
							break;
						default:
							System.out.println("[Error] Unrecognized type.");
							break;
					}
				}
			} while (isInt);
		}
		

		line = scan.nextLine().replaceAll("\\s","");
		do {
			try{
				splitedLine = line.replaceAll("\\s","").split(":");
				layerID = Integer.parseInt(splitedLine[0]);
				isInt = true;
			} catch ( NumberFormatException e ) {
				isInt = false;
			} catch ( NullPointerException e ) {
				isInt = false;
			}
			if( isInt && splitedLine[1].equals("|")){
				hauteur = -1;
				do {
					try {
						line = scan.nextLine();
					} catch ( java.util.NoSuchElementException ex) {
						eof = true;
					}
					if (!eof) {
						if (!line.replaceAll("\\s","").equals("")){
							while(line.charAt(0) == ' ' || line.charAt(0) =='\t'){
								line = line.substring(1);
							}
						}
						splitedLine = line.split("(?!^)");
						if(splitedLine[0].equals(".") || splitedLine[0].equals("#")){
							longueur = -1;
							for (String block : splitedLine){
								if (block.equals(".") || block.equals(" ") ||  block.equals("#")){
									longueur = longueur + 1;
								} else {
									n.ajouteComposant(all_ComposantInital[Integer.parseInt(block)], longueur, hauteur);
									longueur = longueur + 1;
								}
							}
						}
						hauteur = hauteur + 1;
					}
				} while ( (splitedLine[0].equals(".") || splitedLine[0].equals("#")) && !eof );
			}
		} while (isInt && !eof);
		n.fixerDimensions(longueur-1, hauteur);
		n.ajouteComposant(all_ComposantInital[5],-1,0);
		n.ajouteComposant(all_ComposantInital[5],longueur-1,0);
		n.ajouteComposant(all_ComposantInital[5],0,-1);
		n.nouvelleBalle();
	}
}