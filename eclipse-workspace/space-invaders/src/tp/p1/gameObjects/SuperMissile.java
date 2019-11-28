package tp.p1.gameObjects;

import tp.p1.game.Game;

public class SuperMissile extends UCMMissile {

	private static int SUPERMISSILE_HARM = 2;
	
	private static final String SYMBOL = "X";
	
	public SuperMissile(Game game, int r, int c) {
		super(game, r, c, SUPERMISSILE_HARM, SYMBOL);
	}

	public String toString()
	{
		return "X";
	}
}
