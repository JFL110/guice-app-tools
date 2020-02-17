package org.jfl110.app;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.google.inject.AbstractModule;

/**
 * Guice module that binds an instance of NowSupplier that returns the current DateTime
 * 
 * @author jim
 *
 */
public class NowModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(NowSupplier.class).toInstance(LocalDateTime::now);
		bind(ZonedNowSupplier.class).toInstance(ZonedDateTime::now);
	}

}
