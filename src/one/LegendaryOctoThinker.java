package one;

public class LegendaryOctoThinker {
	public int[][] spielfeld;
	public int[] ichHand;
	public int[] duHand;

	public LegendaryOctoThinker(int[][] sf, int[] ih, int[] dh) {
		spielfeld = sf;
		ichHand = ih;
		duHand = dh;

	}

	public float evaluatePosition() {
		float positionValue = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				positionValue += getValueFigur(spielfeld[i][j]);
			}
		}
		for(int i = 0; i < 38; i++) {
			positionValue += getValueFigur(ichHand[i]);
		}
		for(int i = 0; i < 38; i++) {
			positionValue += getValueFigur(duHand[i]);
		}	


		return positionValue;
	}



	public float getValueFigur(int figur) {
		switch(figur) {
		case 0:
			return 0;

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
			//Gegner	
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
