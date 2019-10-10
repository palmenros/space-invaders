package tp.p1.game;

/**
 * Enum that represents the state of the game
 * @author Martín Gómez y Pedro Palacios 
 */
public enum GameState {
	
	/**
	 * Game is still progress, hasn't finished yet
	 */
	IN_PROGRESS,
	
	/**
	 * The game has finished and the player has won
	 */
	PLAYER_WIN,
	
	/**
	 * The game has finished and the alien has won 
	 */
	ALIEN_WIN	
}
