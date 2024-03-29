package tp.p2.gameObjects;

import tp.p2.exceptions.FileContentsException;
import tp.p2.game.Game;
import tp.p2.input.FileContentsVerifier;

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

	private static SuperMissile createInstance(Game game, int r, int c, int label) {
		SuperMissile missile = new SuperMissile(game, r, c);
		missile.setLabel(label);
		return missile;
	}
	
	@Override
	public 	GameObject parse(String string, Game game, FileContentsVerifier verifier) throws FileContentsException, NumberFormatException {
		if(super.parse(string, game, verifier) == null) { return null; }
	
		string = string.split(labelRefSeparator)[0];
		if(!verifier.verifyWeaponString(string, game)) { throw new FileContentsException("Invalid super missile serialization"); }
		
		//Load data	
		return createInstance(game, getRow(), getCol(), label);
	}
}
