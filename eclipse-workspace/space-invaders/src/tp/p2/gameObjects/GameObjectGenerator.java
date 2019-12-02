package tp.p2.gameObjects;

import tp.p2.input.FileContentsVerifier;
import tp.p2.exceptions.FileContentsException;
import tp.p2.game.Game;

public class GameObjectGenerator {
	
	private static GameObject[] availableGameObjects = {
		new UcmShip(),
		new Ovni(),
		new RegularShip(),
		new DestroyerShip(),
		new ExplosiveShip(),
		new ShockWave(),
		new Bomb(),
		new UCMMissile(),
		new SuperMissile()
	};
	
	public static GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws FileContentsException {
		GameObject gameObject = null;
		for (GameObject go: availableGameObjects) {
			gameObject = go.parse(stringFromFile, game, verifier);
			if (gameObject != null) {
				break; 
			}
		}
		return gameObject;
	}
}
