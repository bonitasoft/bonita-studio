package org.eclipse.emf.edapt.common;

/**
 * Helper methods that operate on Strings.
 * 
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating RED Rev:
 */
public final class StringUtils {

	/** Constructor. */
	private StringUtils() {
		// not supposed to be instantiated
	}

	/** Make the first character of a String upper case. */
	public static String getFirstUpper(String s) {
		if (s.length() == 0) {
			return s;
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * Transform a String from upper camel case to lower camel case. In upper
	 * camel case, all words are upper case and separated by the character '_'.
	 * In lower camel case, all words are lower case and separated by upper case
	 * characters.
	 */
	public static String upperToLowerCamelCase(String s) {
		s = s.toLowerCase();
		String[] words = s.split("_");
		String r = null;
		for (String word : words) {
			if (r == null) {
				r = word;
			} else {
				r += getFirstUpper(word);
			}
		}
		return r;
	}

	/**
	 * Transform a String upper camel case to text. In upper camel case, all
	 * words are upper case and separated by the character '_'. The character
	 * '_' is replaced by a space and all words a transformed to lower case
	 * starting with an upper case character.
	 */
	public static String upperCamelCaseToText(String s) {
		s = s.toLowerCase();
		String[] words = s.split("_");
		String r = null;
		for (String word : words) {
			if (r == null) {
				r = getFirstUpper(word);
			} else {
				r += " " + getFirstUpper(word);
			}
		}
		return r;
	}
}
