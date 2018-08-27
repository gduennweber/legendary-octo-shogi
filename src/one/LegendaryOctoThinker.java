package one;

public class LegendaryOctoThinker {
	public int[][] spielfeld;
	public int[] ichHand;
	public int[] duHand;
	public int moveOrigin;
	public int moveZiel;

	public LegendaryOctoThinker(int[][] sf, int[] ih, int[] dh) {
		spielfeld = sf;
		ichHand = ih;
		duHand = dh;
	}
	
	public float evaluatePosition(Spiel sp) {
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
	
	public float alphaBetaMinMax(Spiel sp, int maxTiefe) {
		Spiel vsp = sp; // Virtual Spiel
		return alphaBetaMinMax(vsp, -999999999, 999999999, 0, maxTiefe);
	}
	
	public float alphaBetaMinMax(Spiel vsp, float apha, float beta, int tiefe, int maxTiefe) {
		// Abbruchbedingung
		if (tiefe == maxTiefe)
			return evaluatePosition(vsp);
		
	return  0;}
		
/*		
		
		   // check if leaf
		   children = legalMoves(vsp, vih, vdh);
		   if len(children) == 0
		      if node is root
		         bestMove = [] 
		      return staticEval(node)

		   # initialize bestMove
		   if node is root
		      bestMove = operator of first child
		      # check if there is only one option
		      if len(children) == 1
		         return None

		   if it is MAX's turn to move
		      for child in children
		         result = alphaBetaMinimax(child, alpha, beta)
		         if result > alpha
		            alpha = result
		            if node is root
		               bestMove = operator of child
		         if alpha >= beta
		            return alpha
		      return alpha

		   if it is MIN's turn to move
		      for child in children
		         result = alphaBetaMinimax(child, alpha, beta)
		         if result < beta
		            beta = result
		            if node is root
		               bestMove = operator of child
		         if beta <= alpha
		            return beta
		      return beta
			
		
		
	}
*/
	public float getValueFigur(int figur) {
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
