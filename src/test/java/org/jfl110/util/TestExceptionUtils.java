package org.jfl110.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.jfl110.util.ExceptionUtils.ThrowingRunnable;
import org.jfl110.util.ExceptionUtils.ThrowingSupplier;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;

/**
 * Tests {@link ExceptionUtils}
 * 
 * @author jim
 *
 */
public class TestExceptionUtils {

	@Test
	public void testRunnableNoException() {
		Runnable mockRunnable = mock(Runnable.class);
		ExceptionUtils.doRethrowing(() -> mockRunnable.run());
		verify(mockRunnable, times(1)).run();
	}


	@Test
	public void testCallableNoException() {
		String output = ExceptionUtils.doRethrowing(() -> "abc");
		assertEquals("abc", output);
	}


	@Test(expected = RuntimeException.class)
	public void testThrowingCallable() {
		ExceptionUtils.doRethrowing((ThrowingSupplier<Void>) () -> {
			throw new IOException();
		});
	}


	@Test(expected = IllegalStateException.class)
	public void testEnumWithCodeDeserializerNoClassDeserializeException() throws JsonProcessingException, IOException {
		new EnumWithCodeDeserializer<>(null).deserialize(mock(JsonParser.class), mock(DeserializationContext.class));
	}


	@Test(expected = RuntimeException.class)
	public void testRunnableCallable() {
		ExceptionUtils.doRethrowing((ThrowingRunnable) () -> {
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
