package tp.p1.util;

/**
 * @author Martín Gómez y Pedro Palacios
 *
 * Helper class for string manipulation
 */
public class MyStringUtils {

	
	/**
	 * Return string repeated length times
	 * @param elmnt Element to repeat
	 * @param length Times to repeat
	 * @return Repeated string
	 */
	public static String repeat(String elmnt, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
		    result += elmnt;
		}
		return result;
	}

	
	/**
	 * Center given text in space of length len
	 * @param text Text to center
	 * @param len Length
	 * @return Centered text
	 */
	public static String center(String text, int len){
	    String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
	    float mid = (out.length()/2);
	    float start = mid - (len/2);
	    float end = start + len; 
	    return out.substring((int)start, (int)end);
	}

}