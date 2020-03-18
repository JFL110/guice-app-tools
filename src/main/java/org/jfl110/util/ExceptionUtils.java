package org.jfl110.util;

/**
 * Utils for rethrowing exceptions
 * 
 * @author jim
 *
 */
public class ExceptionUtils {

	/**
	 * Run the supplied runnable and rethrow any exception as a RuntimeException
	 */
	public static void doRethrowing(ThrowingRunnable runnable) {
		try {
			runnable.run();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * Run the supplied supplier and rethrow any exception as a RuntimeException
	 */
	public static <T> T doRethrowing(ThrowingSupplier<T> supplier) {
		try {
			return supplier.get();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@FunctionalInterface
	public interface ThrowingRunnable {
		void run() throws Exception;
	}

	@FunctionalInterface
	public interface ThrowingSupplier<T> {
		T get() throws Exception;
	}
}