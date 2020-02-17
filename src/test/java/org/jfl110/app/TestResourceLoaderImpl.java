package org.jfl110.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.jfl110.app.Logger;
import org.jfl110.app.ResourceLoader;
import org.jfl110.app.ResourceLoaderImpl;
import org.junit.Test;

/**
 * Tests ResourceLoaderImpl
 * 
 * @author jim
 *
 */
public class TestResourceLoaderImpl {
	
	private final ResourceLoader loader = new ResourceLoaderImpl(new Logger.ConsoleLogger());

	
	@Test
	public void testResourceNotFoundOptional() {
		assertFalse(loader.loadAsStringIfExists("NON EXISTENT RESOURCE").isPresent());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testResourceNotFound() {
		loader.loadAsString("NON EXISTENT RESOURCE");
	}
	
	
	@Test
	public void testResource() {
		assertEquals("Expected Content", loader.loadAsString("test-resource-one.txt"));
		assertEquals("Expected Content", loader.loadAsStringIfExists("test-resource-one.txt").orElseThrow(AssertionError::new));
	}
}