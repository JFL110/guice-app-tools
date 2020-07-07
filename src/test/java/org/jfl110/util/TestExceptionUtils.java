package org.jfl110.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.jfl110.util.ExceptionUtils.ThrowingSupplier;
import org.junit.Test;

/**
 * Tests {@link ExceptionUtils}
 * 
 * @author jim
 *
 */
public class TestExceptionUtils {

	@Test
	public void testNoException() {
		assertEquals("abc", ExceptionUtils.doRethrowing(() -> "abc"));

		Runnable mockRunnable = mock(Runnable.class);
		ExceptionUtils.doRethrowing(() -> mockRunnable.run());
		verify(mockRunnable).run();
	}


	@Test(expected = RuntimeException.class)
	public void testException() {
		ExceptionUtils.doRethrowing(() -> {
			throw new IOException();
		});
	}


	@Test(expected = RuntimeException.class)
	public void testExceptionSupplier() {
		AtomicInteger i = new AtomicInteger(1);
		ThrowingSupplier<Object> supplier = () -> {
			if (i.decrementAndGet() == 0) {
				throw new IOException();
			}
			return null;
		};
		ExceptionUtils.doRethrowing(supplier);
		ExceptionUtils.doRethrowing(supplier);
	}
}
