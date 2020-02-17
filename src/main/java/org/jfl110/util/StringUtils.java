package org.jfl110.util;

/**
 * Container class for static helper methods related to strings.
 * 
 * 
 * @author jim
 */
public class StringUtils {

	/**
	 * @return true if the input string is null, empty or entirely composed of
	 *         spaces.
	 */
	public static boolean isBlank(String str) {
		return str == null || str.isEmpty() || str.trim().isEmpty();
	}


	/**
	 * Opposite of isBlank
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
}