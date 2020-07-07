package org.jfl110.app;

import java.util.Optional;

import com.google.inject.ImplementedBy;

/**
 *	Loads resources available on the classpath 
 */
@ImplementedBy(ResourceLoaderImpl.class)
public interface ResourceLoader {

	/**
	 * @return the contents of the specified resource as text, or throw an exception if it doesn't exist.
	 */
	String loadAsString(String resourceName);
	
	/**
	 * @return the contents of the specified resource as text, or Optional.empty() if it doesn't exist.
	 */
	Optional<String> loadAsStringIfExists(String resourceName);
}