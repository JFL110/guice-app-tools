package org.jfl110.app;

import java.util.Optional;

import org.jfl110.util.StringUtils;

/**
 * Reads environment variables / properties
 * 
 * @author jim
 */
public class ConfigVariableExtractor {

	public static Optional<String> readString(String variableName) {
		String value = System.getenv(variableName);
		if (StringUtils.isBlank(value)) {
			value = System.getProperty(variableName);
			if (StringUtils.isBlank(value)) {
				return Optional.empty();
			}
		}
		return Optional.of(value);
	}


	public static String readStringOrThrow(String variableName) {
		return readString(variableName).orElseThrow(() -> new IllegalStateException("No value for [" + variableName + "] set"));
	}
}