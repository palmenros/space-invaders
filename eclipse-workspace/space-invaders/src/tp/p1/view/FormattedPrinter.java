package tp.p1.view;

import tp.p1.game.Game;
import tp.p1.util.MyStringUtils;

/**
 * Class that prints the game to string
 * @author Martín Gómez y Pedro Palacios
 */
public class FormattedPrinter extends GamePrinter {
	
	/**
	 *  Number of rows of the board
	 */
	private int numRows; 
	
	/**
	 *  Number of columns of the board
	 */
	private int numCols;
	
	/**
	 *  String representing the board
	 */
	private String[][] board;
		
	/**
	 * String that represents a space
	 */
	private final String space = " ";
		
	/**
	 * Construct a new game printer from given game
	 * @param game Game
	 * @param rows Number of rows
	 * @param cols Number of columns
	 */
	public FormattedPrinter () {
		this.numRows = Game.ROW_NUM;
		this.numCols = Game.COL_NUM;
		board = new String[numRows][numCols];
	}
	
	/**
	 * Encode current game board into string
	 * @param game Game
	 */
	private void encodeGame(Game game) {
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				board[i][j] =  game.characterAtToString(i, j);
			}
		}
	}
	
	/**
	 * Return game representation as a string
	 */
	public String toString() {

		encodeGame(game);
		
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (numCols * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append("Life: " + game.getPlayerHealth() + "\n");
		str.append("Number of cycles: " + game.getCycleCount() + "\n");
		str.append("Points: " + game.getScore() + "\n");
		
		int remainingAliens = game.getRemainingAliens();
	
		str.append("Remaining aliens: " + remainingAliens + "\n");
		str.append("ShockWave: " + ( game.hasShockWave() ? "YES" : "NO" ) + "\n");
		str.append("SuperMissiles: " + game.getSuperMissileNum() + "\n");		
		
		
		str.append(lineDelimiter);
		
		for(int i=0; i<numRows; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<numCols; j++) {
					str.append( MyStringUtils.center(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}
}