package one;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Cloneable;

public class LegendaryOctoThinker {
	public int[][] spielfeld;
	public int[] ichHand;
	public int[] duHand;
	public int moveOrigin;
	public int moveZiel;

	public LegendaryOctoThinker(int[][] sf, int[] ih, int[] dh) {
		this.spielfeld = new int[sf.length][];
		for(int i = 0; i < sf.length; i++) {
			this.spielfeld[i] = sf[i].clone();
		}
		this.ichHand = ih.clone();
		this.duHand = dh.clone();
	}

	public LegendaryOctoThinker clone() {
		LegendaryOctoThinker lot = new LegendaryOctoThinker(this.spielfeld, this.ichHand, this.duHand);
		lot.moveOrigin = this.moveOrigin;
		lot.moveZiel = this.moveZiel;
		return lot;
	}

	public static float evaluatePosition(Spiel sp) {
		float positionValue = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				positionValue += getValueFigur(sp.spielfeld[i][j]);
			}
		}
		for(int i = 0; i < 38; i++) {
			positionValue += getValueFigur(sp.ichHand[i]);
		}
		for(int i = 0; i < 38; i++) {
			positionValue += getValueFigur(sp.duHand[i]);
		}	
		return positionValue;
	}

	public float alphaBetaMinMax(Spiel sp, int tiefeIch, int tiefeDu, boolean ich) {
		Spiel spiel = sp.clone(); // Virtual Spiel
		if (ich)
			return alphaBetaMinMax(spiel, spiel, -999999999.0f, 999999999.0f, 0, tiefeIch, ich);
		else 
			return alphaBetaMinMax(spiel, spiel, -999999999.0f, 999999999.0f, 0, tiefeDu, ich);
	}

	public float alphaBetaMinMax(Spiel spiel, Spiel vsp, float alpha, float beta, int tiefe, int maxTiefe, boolean isMax) {
		// Abbruchbedingung
		if (tiefe == maxTiefe)
			return evaluatePosition(spiel);

		// children = legalMoves
		ArrayList<Integer> children = new ArrayList<>();
		if(isMax)
			children = (ArrayList<Integer>) spiel.legalMovesIch().clone();
		else
			children = (ArrayList<Integer>) spiel.legalMovesDu().clone();


		// check if leaf
		if (children.size() == 0) {
			if (tiefe == 0) {
				moveOrigin = -1;
				moveZiel = -1;
				return evaluatePosition(spiel);
			}
		}

		// initialize bestMove
		if (tiefe == 0 && children.size() == 2){
			moveOrigin = children.get(0);
			moveZiel = children.get(1);
			return evaluatePosition(spiel);
		}


		if(isMax) {
			for (int i = 0; i < children.size(); i+=2) {
				vsp = spiel.clone();
				vsp.doMove(children.get(i), children.get(i+1));
				float result = alphaBetaMinMax(vsp, vsp, alpha, beta, tiefe + 1, maxTiefe, !isMax);
				if (result > alpha) {
					alpha = result;
					if (tiefe == 0) {
						moveOrigin = children.get(i);
						moveZiel = children.get(i+1);
					}
				}
				if (alpha >= beta)
					return alpha;
			}
			return alpha;
		}

		else {
			for (int i = 0; i < children.size(); i+=2) {
				vsp = spiel.clone();
				vsp.doMove(children.get(i), children.get(i+1));
				float result = alphaBetaMinMax(vsp, vsp, alpha, beta, tiefe + 1, maxTiefe, !isMax);
				if (result < beta) {
					beta = result;
					if (tiefe == 0) {
						moveOrigin = children.get(i);
						moveZiel = children.get(i+1);
					}
				}
				if (beta <= alpha)
					return beta;
			}
			return beta;
		}
	}
	/*for child in children{
		         result = alphaBetaMinimax(child, alpha, beta)
		         if result < beta
		            beta = result
		            if node is root
		               bestMove = operator of child
		         if beta <= alpha
		            return beta
		   }
		      return beta
		   }*/




	public static float getValueFigur(int figur) {
		switch(figur) {
		// Leer
		case 0:
			return 0;
			// Ich
		case 1:
			return 1000;
		case 2:
			return 12.5f;	
		case 3:
			return 10.5f;
		case 4:
			return 8;
		case 5:
			return 6;
		case 6:
			return 4.5f;
		case 7:
			return 3.5f;
		case 8:
			return 1;
		case 12:
			return 15.5f;
		case 13:
			return 13.5f;
		case 15:
			return 8.1f;
		case 16:
			return 8.2f;
		case 17:
			return 8.3f;
		case 18:
			return 8.5f;
			// Gegner	
		case 21:
			return -1000;
		case 22:
			return -12.5f;	
		case 23:
			return -10.5f;
		case 24:
			return -8;
		case 25:
			return -6;
		case 26:
			return -4.5f;
		case 27:
			return -3.5f;
		case 28:
			return -1;
		case 32:
			return -15.5f;
		case 33:
			return -13.5f;
		case 35:
			return -8.1f;
		case 36:
			return -8.2f;
		case 37:
			return -8.3f;
		case 38:
			return -8.5f;
		}
		return -100000;
	}
}
