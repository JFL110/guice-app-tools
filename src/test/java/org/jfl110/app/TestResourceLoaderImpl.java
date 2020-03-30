package org.jfl110.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Scanner;

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
	public void testResourceBadNameOptional() {
		assertFalse(loader.loadAsStringIfExists("../#34543'<").isPresent());
	}


	@Test(expected = IllegalArgumentException.class)
	public void testResourceBadName() {
		loader.loadAsString("../#34543'<");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testBadResourceInputStream() {
		loader.loadAsInputStream("../#34543'<");
	}


	@Test(expected = IllegalArgumentException.class)
	public void testResourceEmptyName() {
		loader.loadAsString("");
	}


	@Test
	public void testResourceEmptyNameOptional() {
		assertFalse(loader.loadAsStringIfExists("NON EXISTENT RESOURCE").isPresent());
	}


	@Test
	public void testResource() {
		assertEquals("Expected Content", loader.loadAsString("test-resource-one.txt"));
		assertEquals("Expected Content", loader.loadAsStringIfExists("test-resource-one.txt").orElseThrow(AssertionError::new));

		assertEquals("Expected Content", loader.loadAsString("./test-resource-one.txt"));
		assertEquals("Expected Content", loader.loadAsStringIfExists("./test-resource-one.txt").orElseThrow(AssertionError::new));

		try (Scanner scanner = new Scanner(loader.loadAsInputStream("./test-resource-one.txt"))) {
			StringBuffer sb = new StringBuffer();
			while (scanner.hasNext()) {
				sb.append(scanner.nextLine());
			}
			assertEquals("Expected Content", sb.toString());
		}
	}
}