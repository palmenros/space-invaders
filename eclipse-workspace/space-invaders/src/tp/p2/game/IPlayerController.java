package tp.p2.game;

import tp.p2.exceptions.GameActionException;

public interface IPlayerController {

	// Player actions
	public void move (int numCells) throws GameActionException;
	public void shootMissile(boolean superMissile) throws GameActionException;
	public void shockWave() throws GameActionException;
	public void buySuperMissile(int cost) throws GameActionException;
	
	// Callbacks
	public void receivePoints(int points);
	public void enableShockWave();
	public void enableMissile();	
}
