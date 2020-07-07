package org.jfl110.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Interface for enums that have a should be serialised to a code (not a string
 * of their name).
 * 
 * @author jim
 *
 */
@JsonDeserialize(using = EnumWithCodeDeserializer.class)
@JsonSerialize(using = EnumWithCodeSerializer.class)
public interface EnumWithCode {

	public String code();
}
