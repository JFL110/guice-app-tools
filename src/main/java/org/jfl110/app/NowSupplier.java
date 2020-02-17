package org.jfl110.app;

import java.time.LocalDateTime;
import java.util.function.Supplier;


/**
 * Supplier of the current DateTime to simplify testing.
 * 
 * @author jim
 */
public interface NowSupplier extends Supplier<LocalDateTime>{}