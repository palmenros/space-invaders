package tp.p1.game;

public abstract class AlienShip extends EnemyShip {

	private static boolean anyAlienHasLanded = false;
	
	/**
	 * Number of cycles since last joined alien move
	 */
	private static int cyclesSinceLastMove = Integer.MAX_VALUE;
			
	/**
	 *  Direction where the aliens are moving
	 */
	private static Direction alienDirection = Direction.LEFT;
	
	private static boolean shouldMoveAliensDown = false;
	
	private static int alienShipCount = 0;
	private static int alienUpdatedCount = 0;
	
	
	public AlienShip(Game game, int r, int c, int health, int score)
	{
		super(game, r, c, health, score);
		alienShipCount++;
	}
	
	
	@Override
	public void computerAction() {
		//Calculate movement direction
		if(!shouldMoveAliensDown && isAtColumnBorder(alienDirection)) {
			//Only called once
			shouldMoveAliensDown = true;
			alienDirection = alienDirection.getOppositeDirection();	
		}
	}	
	
	@Override
	public void update() {
		alienUpdatedCount++;
		boolean lastUpdateAlien = alienUpdatedCount == alienShipCount;	
		
		//Move according to calculated direction in computerAction
		if(shouldMoveAliensDown) {
			move(1, 0);
			if(lastUpdateAlien) {
				//Reset platoon variables only once, if last alien
				cyclesSinceLastMove = 0;
				shouldMoveAliensDown = false;	
			}	
		} else if(cyclesSinceLastMove >= game.getLevel().getSpeed()) {
			
			//Move in desired horizontal direction
			move(0, alienDirection.getDeltaCol());
			
			if(lastUpdateAlien) {
				//Reset platoon variables only once, if last alien
				cyclesSinceLastMove = 0;
			}	
		}	
		
		//If last updated alien, reset variables for platoon
		if(lastUpdateAlien) {
			cyclesSinceLastMove++;
			alienUpdatedCount = 0;
		}

		//If is at lowest row, at least an alien has landed
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

	@Override
	public void destroy() {
		alienShipCount--;
		super.destroy();
	}
	
	public static void reset() {
		anyAlienHasLanded = false;
		cyclesSinceLastMove = Integer.MAX_VALUE;	
		alienDirection = Direction.LEFT;
		shouldMoveAliensDown = false;
		alienUpdatedCount = 0;
	}
}