package tp.p2.gameObjects;

import tp.p2.game.Direction;

public class AlienShipStatics {
	
	private int cyclesSinceLastMove;
	private Direction direction;
	
	public AlienShipStatics(int cycles, Direction dir) {
		cyclesSinceLastMove = cycles;
		direction = dir;
	}
	
	public int getCycles() {
		return cyclesSinceLastMove;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
}
