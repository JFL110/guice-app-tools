package org.jfl110.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * String -> EnumWithCode
 */
class EnumWithCodeDeserializer<K extends Enum<K> & EnumWithCode> extends StdDeserializer<EnumWithCode> implements ContextualDeserializer {

	private static final long serialVersionUID = 1L;

	private final Class<K> clazz;

	protected EnumWithCodeDeserializer() {
		super(EnumWithCode.class);
		clazz = null;
	}


	EnumWithCodeDeserializer(Class<K> clazz) {
		super(clazz);
		this.clazz = clazz;
	}


	@Override
	public EnumWithCode deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		if (clazz == null) {
			throw new IllegalStateException("This EnumWithCodeDeserializer has not been properly created via ContextualDeserializer");
		}

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		String code = node.asText();
		return LambdaUtils.mapToEnumFromCode(code, clazz);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		Class<?> clazz = property.getType().getRawClass();
		return new EnumWithCodeDeserializer(clazz);
	}
}