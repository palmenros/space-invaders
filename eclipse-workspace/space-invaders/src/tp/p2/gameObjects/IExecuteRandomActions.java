package tp.p2.gameObjects;

import tp.p2.game.Game;

public interface IExecuteRandomActions {
	
	static boolean canGenerateRandomOvni(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getUfoRate();
	}
	
	static boolean canGenerateRandomBomb(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getFireRate();
	}
	
	static boolean shouldBecomeExplosiveShip(Game game)
	{
		return game.getRandom().nextDouble() < game.getLevel().getExplosiveConversionRate();
	}
}
