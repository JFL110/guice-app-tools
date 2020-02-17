package org.jfl110.app;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.junit.Test;

import com.google.inject.Guice;

/**
 * Very basic tests for {@link NowModule}
 * @author jim
 *
 */
public class TestNowSuppliers {

	private static final int MARGIN_MILLIS = 20;
	private final NowSupplier nowSupplier = Guice.createInjector(new NowModule()).getInstance(NowSupplier.class);
	private final ZonedNowSupplier zonedNowSupplier = Guice.createInjector(new NowModule()).getInstance(ZonedNowSupplier.class);

	
	@Test
	public void testNow() {
		LocalDateTime now = LocalDateTime.now();
		assertTrue(Duration.between(now, nowSupplier.get()).abs().toMillis() < MARGIN_MILLIS);
	}
	
	
	@Test
	public void testZonedNow() {
		ZonedDateTime now = ZonedDateTime.now();
		assertTrue(Duration.between(now, zonedNowSupplier.get()).abs().toMillis() < MARGIN_MILLIS);
	}
}
