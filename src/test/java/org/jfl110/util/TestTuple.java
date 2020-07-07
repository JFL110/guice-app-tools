package org.jfl110.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Very basic tests of {@link Tuple}
 * 
 * @author jim
 *
 */
public class TestTuple {

	@Test
	public void test() {
		Tuple<String, String> tuple = Tuple.newTuple("a", "b");
		assertEquals("a", tuple.a());
		assertEquals("b", tuple.b());
		assertEquals("a", tuple.asOptional().get().a());
		assertEquals("b", tuple.asOptional().get().b());
	}
}
