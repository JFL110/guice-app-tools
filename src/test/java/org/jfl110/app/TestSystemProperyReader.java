package org.jfl110.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

/**
 * Tests {@link SystemProperyReader}
 * @author jim
 *
 */
public class TestSystemProperyReader {

	@Rule public final EnvironmentVariables environmentVariables = new EnvironmentVariables();
	private final SystemProperyReader reader = new SystemProperyReader();

	@Test
	public void testProp() {
		System.setProperty("PROP_1", "VAL_1");
		assertEquals("VAL_1", reader.readPropertyString("PROP_1").orElseThrow(AssertionError::new));
		assertFalse(reader.readPropertyString("NON_PROP").isPresent());
	}
	
	
	@Test
	public void testEnv() {
		environmentVariables.set("ENV_1", "VAL_2");
		assertEquals("VAL_2", reader.readEnvironmentString("ENV_1").orElseThrow(AssertionError::new));
		assertFalse(reader.readPropertyString("NON_ENV").isPresent());
	}
	
	
	@Test
	public void testEnvThenProp() {
		environmentVariables.set("ENV_1", "VAL_3");
		System.setProperty("ENV_1", "VAL_4");
		assertEquals("VAL_4", reader.readPropertyString("ENV_1").orElseThrow(AssertionError::new));
		assertEquals("VAL_3", reader.readEnvironmentString("ENV_1").orElseThrow(AssertionError::new));
		assertEquals("VAL_3", reader.readEnvironmentOrProperty("ENV_1").orElseThrow(AssertionError::new));
		assertFalse(reader.readPropertyString("NON_ENV").isPresent());
	}
}