package one;

import java.util.Arrays;

public class Main {
	//TODO: Umwandeln
	public static void main(String[] args) {
		System.out.println("hello");
		Spiel spiel = new Spiel();
		System.out.println(Arrays.toString(spiel.legalMovesIch().toArray()));
		System.out.println(spiel.lot.evaluatePosition(spiel));
		boolean meTurn = true;
		for(int i = 0; i < 200; i++) {
			spiel.lot.alphaBetaMinMax(spiel, 5, 1, meTurn);
			spiel.doMove(spiel.lot.moveOrigin, spiel.lot.moveZiel);
			System.out.println();
			System.out.println(spiel.lot.moveOrigin + " - " + spiel.lot.moveZiel);
			System.out.println();
			meTurn = (!meTurn);
			System.out.println("hig: " + (i+1) + ". Zug");
			String reihe = "";
			for (int j = 0; j < spiel.spielfeld.length; j++) {
				for(int k = 0; k < spiel.spielfeld.length; k ++) {
					reihe += Integer.toString(spiel.spielfeld[j][k]) + "\t";
				}
				//reihe = Arrays.toString(spiel.spielfeld[j]);
				System.out.println(reihe + "\n");
				reihe = "";
			}
		System.out.println(LegendaryOctoThinker.evaluatePosition(spiel));
		}
	}
}
