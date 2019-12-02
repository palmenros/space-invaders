package tp.p2.gameObjects;

import tp.p2.game.Game;

public class SuperMissile extends UCMMissile {

	private static int SUPERMISSILE_HARM = 2;
	
	private static final String SYMBOL = "X";
	
	public SuperMissile(Game game, int r, int c) {
		super(game, r, c, SUPERMISSILE_HARM, SYMBOL);
	}

	public SuperMissile() {
		this(null, 0, 0);
	}

	public String toString()
	{
		return "X";
	}
}
