package org.jfl110.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

/**
 * Tests {@link ConfigVariableExtractor}
 * 
 * @author jim
 *
 */
public class TestConfigVariableExtractor {

	private static final String VAR_1 = "var-1";
	private static final String VAR_2 = "var-2";
	private static final String VAR_3 = "var-3";

	@Rule public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

	@Test
	public void testUnset() {
		assertFalse(ConfigVariableExtractor.readString(VAR_1).isPresent());
	}


	@Test(expected = IllegalStateException.class)
	public void testUnsetException() {
		ConfigVariableExtractor.readStringOrThrow(VAR_1);
	}


	@Test
	public void testEnv() {
		environmentVariables.set(VAR_2, "the-value");
		assertEquals("the-value", ConfigVariableExtractor.readString(VAR_2).orElse(null));
		assertEquals("the-value", ConfigVariableExtractor.readStringOrThrow(VAR_2));
	}


	@Test
	public void testProp() {
		System.setProperty(VAR_3, "the-value-2");
		assertEquals("the-value-2", ConfigVariableExtractor.readString(VAR_3).orElse(null));
		assertEquals("the-value-2", ConfigVariableExtractor.readStringOrThrow(VAR_3));
	}

}
