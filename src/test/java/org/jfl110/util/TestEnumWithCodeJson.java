package org.jfl110.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test serialising and deserialising {@link EnumWithCode}
 * 
 * @author jim
 *
 */
public class TestEnumWithCodeJson {

	@Test
	public void test() throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		// Serialize
		String serial = mapper.writeValueAsString(new TestBeanOne(TestEnumOne.VALUE_A, "hello"));
		assertEquals("{\"enumField\":\"ABC\",\"otherField\":\"hello\"}", serial);

		TestBeanOne bean = mapper.readValue(serial, TestBeanOne.class);
		assertEquals("hello", bean.otherField);
		assertEquals(TestEnumOne.VALUE_A, bean.enumField);
	}

	public static class TestBeanOne {

		@JsonProperty("enumField") private final TestEnumOne enumField;
		@JsonProperty("otherField") private final String otherField;

		@JsonCreator
		public TestBeanOne(@JsonProperty("enumField") TestEnumOne enumField, @JsonProperty("otherField") String otherField) {
			this.enumField = enumField;
			this.otherField = otherField;
		}

	}

	enum TestEnumOne implements EnumWithCode {

		VALUE_A("ABC"), VALUE_B("DEF");

		private final String code;

		private TestEnumOne(String code) {
			this.code = code;
		}


		@Override
		public String code() {
			return code;
		}
	}
}
