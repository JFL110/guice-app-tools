package org.jfl110.app;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

/**
 * Supplier of the current Zoned DateTime to simplify testing.
 * 
 * @author jim
 */
public interface ZonedNowSupplier extends Supplier<ZonedDateTime>{}