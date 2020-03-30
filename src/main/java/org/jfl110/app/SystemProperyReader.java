package org.jfl110.app;

import java.util.Optional;

import org.jfl110.util.StringUtils;

/**
 * Class to read environment variables or properties. Simplifies testing.
 * 
 * @author jim
 *
 */
public class SystemProperyReader {

	public Optional<String> readEnvironmentOrProperty(String variableName) {
		Optional<String> env = readEnvironmentString(variableName);
		return env.isPresent() ? env : readPropertyString(variableName);
	}


	public Optional<String> readEnvironmentString(String environmentVariableName) {
		String value = System.getenv(environmentVariableName);
		return StringUtils.isBlank(value) ? Optional.empty() : Optional.of(value);
	}


	public Optional<String> readPropertyString(String propertyVariableName) {
		String value = System.getProperty(propertyVariableName);
		return StringUtils.isBlank(value) ? Optional.empty() : Optional.of(value);
	}
}