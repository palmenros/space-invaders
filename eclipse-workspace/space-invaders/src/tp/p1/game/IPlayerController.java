package tp.p1.game;

public interface IPlayerController {

	// Player actions
	public boolean move (int numCells);
	public boolean shootMissile(boolean superMissile);
	public boolean shockWave();
	
	// Callbacks
	public void receivePoints(int points);
	public void enableShockWave();
	public void enableMissile();
	public boolean buySuperMissile(int cost);	
}
