package org.jfl110.app;

import static org.junit.Assert.assertEquals;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.OptionalLong;

import org.jfl110.app.DefaultObjectMapper;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;

/**
 * Test {@link DefaultObjectMapper}
 * 
 * @author jim
 *
 */
public class TestObjectMapper {

	private final ObjectMapper mapper = Guice.createInjector(DefaultObjectMapper.module()).getInstance(ObjectMapper.class);
	private final ZonedDateTime testTime = ZonedDateTime.now();

	@Test
	public void testOne() throws Exception {
		Bean beanOne = new Bean("abc", Optional.of("def"), OptionalLong.of(123), testTime);
		String asString = mapper.writeValueAsString(beanOne);
		Bean asBean = mapper.readValue(asString, Bean.class);

		assertEquals(beanOne.fieldOne, asBean.fieldOne);
		assertEquals(beanOne.fieldTwo, asBean.fieldTwo);
		assertEquals(beanOne.fieldThree, asBean.fieldThree);
		assertEquals(beanOne.fieldFour.toEpochSecond(), asBean.fieldFour.toEpochSecond());
	}


	@Test
	public void testTwo() throws Exception {
		Bean beanOne = new Bean("abc", Optional.empty(), OptionalLong.empty(), testTime);
		String asString = mapper.writeValueAsString(beanOne);
		Bean asBean = mapper.readValue(asString, Bean.class);

		assertEquals(beanOne.fieldOne, asBean.fieldOne);
		assertEquals(beanOne.fieldTwo, asBean.fieldTwo);
		assertEquals(beanOne.fieldThree, asBean.fieldThree);
		assertEquals(beanOne.fieldFour.toEpochSecond(), asBean.fieldFour.toEpochSecond());

	}

	public static class Bean {
		@JsonProperty("f1") private final String fieldOne;
		@JsonProperty("f2") private final Optional<String> fieldTwo;
		@JsonProperty("f3") private final OptionalLong fieldThree;
		@JsonProperty("f4") private final ZonedDateTime fieldFour;

		Bean(
				@JsonProperty("f1") String fieldOne,
				@JsonProperty("f2") Optional<String> fieldTwo,
				@JsonProperty("f3") OptionalLong fieldThree,
				@JsonProperty("f4") ZonedDateTime fieldFour) {
			this.fieldOne = fieldOne;
			this.fieldTwo = fieldTwo;
			this.fieldThree = fieldThree;
			this.fieldFour = fieldFour;
		}

	}
}
