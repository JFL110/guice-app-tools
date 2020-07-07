package org.jfl110.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests StringUtils
 * 
 * @author jim
 */
public class TestStringUtils {

	/**
	 * Tests isBlank
	 */
	@Test
	public void testIsBlank() {
		assertTrue(StringUtils.isBlank(null));
		assertTrue(StringUtils.isBlank(""));
		assertTrue(StringUtils.isBlank(" "));
		assertTrue(StringUtils.isBlank("    "));
		assertTrue(StringUtils.isBlank("    "));

		assertFalse(StringUtils.isBlank("hello"));
		assertFalse(StringUtils.isBlank(" hello"));
		assertFalse(StringUtils.isBlank(" hello "));

		assertTrue(StringUtils.isBlank("			"));
	}


	/**
	 * Tests isNotBlank
	 */
	@Test
	public void testIsNotBlank() {
		assertFalse(StringUtils.isNotBlank(null));
		assertFalse(StringUtils.isNotBlank(""));
		assertFalse(StringUtils.isNotBlank(" "));
		assertFalse(StringUtils.isNotBlank("    "));
		assertFalse(StringUtils.isNotBlank("    "));

		assertTrue(StringUtils.isNotBlank("hello"));
		assertTrue(StringUtils.isNotBlank(" hello"));
		assertTrue(StringUtils.isNotBlank(" hello "));

		assertFalse(StringUtils.isNotBlank("			"));
	}


	@Test
	public void testRemovePrefix() {
		assertEquals("hello", StringUtils.removePrefix("the-prefix-hello", "the-prefix-"));
		assertEquals("no-prefix", StringUtils.removePrefix("no-prefix", "too-long-to-be-the-prefix"));
	}


	/**
	 * Coverage bump
	 */
	@Test
	public void testInstantiation() {
		new StringUtils();
	}
}
