package tp.p1.game;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * Represents level of the game
 */
public enum Level {
	
	EASY  	(4, 2, 0.1f, 3, 0.5f),
	HARD  	(8, 2, 0.3f, 2, 0.2f),
	INSANE	(8, 4, 0.5f, 1, 0.1f);

	/**
	 * @return Number of common ships
	 */
	public int getCommonShipNum() {
		return commonShipNum;
	}

	/**
	 * @return Number of destroyer ships
	 */
	public int getDestroyerNum() {
		return destroyerNum;
	}

	/**
	 * @return Fire rate
	 */
	public float getFireRate() {
		return fireRate;
	}

	/**
	 * @return Speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @return Rate at which UFO appear
	 */
	public float getUfoRate() {
		return ufoRate;
	}

	/**
	 * Number of common ships
	 */
	private final int commonShipNum;
	
	/**
	 * Number of destroyer ships
	 */
	private final int destroyerNum;
	
	/**
	 * Fire rate
	 */
	private final float fireRate;
	
	/**
	 * Speed
	 */
	private final int speed;
	
	/**
	 * Rate at which UFOs appear
	 */
	private final float ufoRate;
	
	/**
	 * @param commonShipNum Number of common ships
	 * @param destroyerNum Number of destroyer ships
	 * @param fireRate	Fire rate
	 * @param speed	Speed
	 * @param ufoRate Rate at which UFOs appear
	 */
	private Level(int commonShipNum, int destroyerNum, float fireRate, int speed, float ufoRate)
	{
		this.commonShipNum = commonShipNum;
		this.destroyerNum = destroyerNum;
		this.fireRate = fireRate;
		this.speed = speed;
		this.ufoRate = ufoRate;
	}

}
