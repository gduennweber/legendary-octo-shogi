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
		for(int i = 0; i < 4; i++) {
			spiel.lot.alphaBetaMinMax(spiel, 2, meTurn);
			spiel.doMove(spiel.lot.moveOrigin, spiel.lot.moveZiel);
			System.out.println();
			System.out.println(spiel.lot.moveOrigin + " - " + spiel.lot.moveZiel);
			System.out.println();
			meTurn = (!meTurn);
			System.out.println("hig");
			for (int j = 0; j < spiel.spielfeld.length; j++)
				System.out.println(Arrays.toString(spiel.spielfeld[j]));
		}
		System.out.println(spiel.lot.evaluatePosition(spiel));
	}
}
