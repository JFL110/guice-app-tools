package org.jfl110.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * EnumWithCode -> string
 *
 */
class EnumWithCodeSerializer extends StdSerializer<EnumWithCode> {

	private static final long serialVersionUID = 1L;

	protected EnumWithCodeSerializer() {
		super(EnumWithCode.class);
	}


	@Override
	public void serialize(
			EnumWithCode enumValue, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		generator.writeString(enumValue.code());
	}
}