package one;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Spiel spiel = new Spiel();
		System.out.println(Arrays.toString(spiel.legalMoves().toArray()));
		System.out.println(spiel.lot.evaluatePosition());
	}
}
