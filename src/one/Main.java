package one;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		System.out.println("hello");
		Spiel spiel = new Spiel();
		System.out.println(Arrays.toString(spiel.legalMovesIch().toArray()));
		System.out.println(spiel.lot.evaluatePosition(spiel));
	}
}
