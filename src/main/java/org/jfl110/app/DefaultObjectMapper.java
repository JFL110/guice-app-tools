package org.jfl110.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Module;

/**
 * Default object mapper with:
 * - Java time
 * - JDK8 objects (Optional)
 * 
 * @author jim
 *
 */
public class DefaultObjectMapper {

	public static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.registerModule(new Jdk8Module());

	/**
	 * @return A Guice module to bind the object mapper
	 */
	public static Module module() {
		return b -> b.bind(ObjectMapper.class).toInstance(DEFAULT_OBJECT_MAPPER);
	}
}
