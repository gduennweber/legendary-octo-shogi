package one;
import java.util.*;

public class Spiel {

	public int[][] spielfeld = {{27, 26, 25, 24, 21, 24, 25, 26, 27}, {0, 22, 0, 0, 0, 0, 0, 23, 0}, {28, 28, 28, 28, 28, 28, 28, 28, 28}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {8,8,8,8,8,8,8,8,8}, {0,3,0,0,0,0,0,2,0}, {7,6,5,4,1,4,5,6,7}};
	public int[] ichHand;
	public int[] duHand;
	public LegendaryOctoThinker lot;


	public Spiel() {
		//spielfeld = new int[9][9];
		ichHand = new int[38];
		duHand = new int[38];
		lot = new LegendaryOctoThinker(spielfeld, ichHand, duHand);
	}


	// R�ckgabe: gerade							| ungerade
	//			 vorherige Position der Figur	| n�chste Position der Figur
	// 			 falls zuvor auf der Hand: Nummer der Figur + 100	 | n�chste Position der Figur
	public ArrayList<Integer> legalMovesIch() {
		ArrayList<Integer> moves = new ArrayList<>();
		for (int i=0; i < 9; i++) {
			for (int j=0; j < 9; j++) {
				moves.addAll(legalMovesIchFeld(spielfeld[i][j], i*10+j));
			}
		}
		for (int i = 0; i < 38; i++) {
			if(ichHand[i] == 0)
				break;
			moves.addAll(legalMovesHand(ichHand[i]));
		}
		return moves;
	}
	
	public ArrayList<Integer> legalMovesDu() {
		ArrayList<Integer> moves = new ArrayList<>();
		for (int i=0; i < 9; i++) {
			for (int j=0; j < 9; j++) {
				moves.addAll(legalMovesDuFeld(spielfeld[i][j], i*10+j));
			}
		}
		for (int i = 0; i < 38; i++) {
			if(duHand[i] == 0)
				break;
			moves.addAll(legalMovesHand(ichHand[i]));
		}
		return moves;
	}

	public ArrayList<Integer> legalMovesHand(int figur){
		ArrayList<Integer> moves = new ArrayList<>();
		// Figuren auf der Hand k�nnen auf allen leeren Feldern eingesetzt werden
		for (int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if (spielfeld[i] [j] == 0) {
					moves.add(figur + 100);
					moves.add(i*10 + j);
				}
			}
		}
		return moves;
	}


	//leer ist null
	public ArrayList<Integer> legalMovesIchFeld(int figur, int pos){
		ArrayList<Integer> moves = new ArrayList<>();
		switch(figur) {
		case 12: // Drachenturm
			int z = pos - 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}		
		case 2: // Turm
			z = pos + 1;
			while(z >= 0 && cX(z) < 9 && cY(z) < 9) {
				if (spielfeld[cY(z)][cX(z)] < 20 && spielfeld[cY(z)][cX(z)] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(z);
				if (spielfeld[cY(z)][cX(z)] > 19) {
					break;
				}
				z++;
			}
			z = pos - 1;
			while(z >= 0 && cX(z) < 9 && cY(z) < 9) {
				if (spielfeld[cY(z)][cX(z)] < 20 && spielfeld[cY(z)][cX(z)] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(z);
				if (spielfeld[cY(z)][cX(z)] > 19) {
					break;
				}
				z--;
			}
			z = pos + 10;
			while(z >= 0 && cX(z) < 9 && cY(z) < 9) {
				if (spielfeld[cY(z)][cX(z)] < 20 && spielfeld[cY(z)][cX(z)] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(z);
				if (spielfeld[cY(z)][cX(z)] > 19) {
					break;
				}
				z = z + 10;
			}
			z = pos - 10;
			while(z >= 0 && cX(z) < 9 && cY(z) < 9) {
				if (spielfeld[cY(z)][cX(z)] < 20 && spielfeld[cY(z)][cX(z)] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(z);
				if (spielfeld[cY(z)][cX(z)] > 19) {
					break;
				}
				z = z -10;
			}
			break;
		case 13: // Drachenl�ufer
			z = pos - 10;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 1;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 1;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 10;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
		case 3: // L�ufer
			int zx = cX(pos) + 1 ;
			int zy = cY(pos) + 1;
			while(zx >= 0 && zy >= 0 && zx < 9 && zy < 9) {
				if (spielfeld[zy][zx] < 20 && spielfeld[zy][zx] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(zx + zy*10);
				if (spielfeld[zy][zx] > 19) {
					break;
				}
				zx++;
				zy++;
			}
			zx = cX(pos) - 1 ;
			zy = cY(pos) - 1;
			while(zx >= 0 && zy >= 0 && zx < 9 && zy < 9) {
				if (spielfeld[zy][zx] < 20 && spielfeld[zy][zx] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(zx + zy*10);
				if (spielfeld[zy][zx] > 19) {
					break;
				}
				zx--;
				zy--;
			}
			zx = cX(pos) + 1 ;
			zy = cY(pos) - 1;
			while(zx >= 0 && zy >= 0 && zx < 9 && zy < 9) {
				if (spielfeld[zy][zx] < 20 && spielfeld[zy][zx] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(zx + zy*10);
				if (spielfeld[zy][zx] > 19) {
					break;
				}
				zx++;
				zy--;
			}
			zx = cX(pos) - 1 ;
			zy = cY(pos) + 1;
			while(zx >= 0 && zy >= 0 && zx < 9 && zy < 9) {
				if (spielfeld[zy][zx] < 20 && spielfeld[zy][zx] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(zx + zy*10);
				if (spielfeld[zy][zx] > 19) {
					break;
				}
				zx--;
				zy++;
			}
			break;
		case 5: // Silberner General
			z = pos - 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 10;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			break;
		case 6: // Springer
			z = pos - 21;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && spielfeld[cY(z)][cX(z)] == 0) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 19;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && spielfeld[cY(z)][cX(z)] == 0) {
				moves.add(pos);
				moves.add(z);
			}
			break;
		case 7: // Lanze
			z = pos -10;
			while(z >= 0 && cX(z) < 9 && cY(z) < 9) {
				if (spielfeld[cY(z)][cX(z)] < 20 && spielfeld[cY(z)][cX(z)] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(z);
				if (spielfeld[cY(z)][cX(z)] > 19) {
					break;
				}
				z += -10;
			}
			break;
		case 8: // Bauer
			z = pos - 10;
			if(z >= 0 && cX(z) < 9 && cY(z) < 9 && (spielfeld[cY(z)][cX(z)] == 0 || spielfeld[cY(z)][cX(z)] > 19)) {
				moves.add(pos);
				moves.add(z);
			}
			break;
		case 1: //K�nig
			z = pos + 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
		case 15: // Bef�rderter Silber
		case 16: // Bef�rderter Springer
		case 17: // Bef�rderte Lanze
		case 18: // Bef�rderter Bauer
		case 4: // Goldener General
			z = pos - 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 10;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 1;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 1;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 10;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			break;
		}
		return moves;
	}
	public ArrayList<Integer> legalMovesDuFeld(int figur, int pos){
		ArrayList<Integer> moves = new ArrayList<>();
		switch(figur) {
		case 32: // Drachenturm
			int z = pos + 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}		
		case 22: // Turm
			z = pos - 1;
			while(z >= 0 && cX(z) < 9 && cY(z) < 9) {
				if (spielfeld[cY(z)][cX(z)] < 20 && spielfeld[cY(z)][cX(z)] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(z);
				if (spielfeld[cY(z)][cX(z)] > 19) {
					break;
				}
				z--;
			}
			z = pos + 1;
			while(z >= 0 && cX(z) < 9 && cY(z) < 9) {
				if (spielfeld[cY(z)][cX(z)] < 20 && spielfeld[cY(z)][cX(z)] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(z);
				if (spielfeld[cY(z)][cX(z)] > 19) {
					break;
				}
				z++;
			}
			z = pos - 10;
			while(z >= 0 && cX(z) < 9 && cY(z) < 9) {
				if (spielfeld[cY(z)][cX(z)] < 20 && spielfeld[cY(z)][cX(z)] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(z);
				if (spielfeld[cY(z)][cX(z)] > 19) {
					break;
				}
				z = z - 10;
			}
			z = pos + 10;
			while(z >= 0 && cX(z) < 9 && cY(z) < 9) {
				if (spielfeld[cY(z)][cX(z)] < 20 && spielfeld[cY(z)][cX(z)] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(z);
				if (spielfeld[cY(z)][cX(z)] > 19) {
					break;
				}
				z = z + 10;
			}
			break;
		case 33: // Drachenl�ufer
			z = pos + 10;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 1;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 1;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 10;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
		case 23: // L�ufer
			int zx = cX(pos) - 1 ;
			int zy = cY(pos) - 1;
			while(zx >= 0 && zy >= 0 && zx < 9 && zy < 9) {
				if (spielfeld[zy][zx] < 20 && spielfeld[zy][zx] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(zx + zy*10);
				if (spielfeld[zy][zx] > 19) {
					break;
				}
				zx--;
				zy--;
			}
			zx = cX(pos) + 1 ;
			zy = cY(pos) + 1;
			while(zx >= 0 && zy >= 0 && zx < 9 && zy < 9) {
				if (spielfeld[zy][zx] < 20 && spielfeld[zy][zx] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(zx + zy*10);
				if (spielfeld[zy][zx] > 19) {
					break;
				}
				zx++;
				zy++;
			}
			zx = cX(pos) + 1 ;
			zy = cY(pos) - 1;
			while(zx >= 0 && zy >= 0 && zx < 9 && zy < 9) {
				if (spielfeld[zy][zx] < 20 && spielfeld[zy][zx] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(zx + zy*10);
				if (spielfeld[zy][zx] > 19) {
					break;
				}
				zx++;
				zy--;
			}
			zx = cX(pos) - 1 ;
			zy = cY(pos) + 1;
			while(zx >= 0 && zy >= 0 && zx < 9 && zy < 9) {
				if (spielfeld[zy][zx] < 20 && spielfeld[zy][zx] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(zx + zy*10);
				if (spielfeld[zy][zx] > 19) {
					break;
				}
				zx--;
				zy++;
			}
			break;
		case 25: // Silberner General
			z = pos + 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 10;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			break;
		case 26: // Springer
			z = pos + 21;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && spielfeld[cY(z)][cX(z)] == 0) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 19;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && spielfeld[cY(z)][cX(z)] == 0) {
				moves.add(pos);
				moves.add(z);
			}
			break;
		case 27: // Lanze
			z = pos + 10;
			while(z >= 0 && cX(z) < 9 && cY(z) < 9) {
				if (spielfeld[cY(z)][cX(z)] < 20 && spielfeld[cY(z)][cX(z)] != 0) {
					break;
				}
				moves.add(pos);
				moves.add(z);
				if (spielfeld[cY(z)][cX(z)] > 19) {
					break;
				}
				z += +10;
			}
			break;
		case 28: // Bauer
			z = pos + 10;
			if(z >= 0 && cX(z) < 9 && cY(z) < 9 && (spielfeld[cY(z)][cX(z)] == 0 || spielfeld[cY(z)][cX(z)] > 19)) {
				moves.add(pos);
				moves.add(z);
			}
			break;
		case 21: //K�nig
			z = pos - 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
		case 35: // Bef�rderter Silber
		case 36: // Bef�rderter Springer
		case 37: // Bef�rderte Lanze
		case 38: // Bef�rderter Bauer
		case 24: // Goldener General
			z = pos + 11;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 10;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 9;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos + 1;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 1;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			z = pos - 10;
			if (z > 0 && cX(z) < 9 && cY(z) < 9 && 0 == spielfeld[cY(z)] [cX(z)]) {
				moves.add(pos);
				moves.add(z);
			}
			break;
		}
		return moves;
	}
	


	public int cX (int p) {
		return p % 10;
	}

	public int cY (int p) {
		return ((p) - ((p) % 10)) / 10;
	}
}
