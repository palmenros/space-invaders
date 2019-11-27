package tp.p1.game;

public class SuperMissile extends UCMMissile {

	private static int SUPERMISSILE_HARM = 2;
	
	public SuperMissile(Game game, int r, int c) {
		super(game, r, c, SUPERMISSILE_HARM);
	}

}
