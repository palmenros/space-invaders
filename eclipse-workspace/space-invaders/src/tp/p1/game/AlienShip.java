package tp.p1.game;

public abstract class AlienShip extends EnemyShip {

	private static boolean anyAlienHasLanded = false;
	
	public AlienShip(Game game, int r, int c, int health, int score)
	{
		super(game, r, c, health, score);
	}
	
	@Override
	public void update() {
		if(isAtLowestRow()) {
			anyAlienHasLanded = true;
		}
	}
	
	/**
	 * @return True if any alien has landed
	 */
	public static boolean haveLanded() {
		return anyAlienHasLanded;
	}
}
