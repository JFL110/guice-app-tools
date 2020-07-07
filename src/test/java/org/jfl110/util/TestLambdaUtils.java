package org.jfl110.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Basic tests of {@link LambdaUtils}
 * 
 * @author jim
 *
 */
public class TestLambdaUtils {

	private static final List<String> IN = Lists.newArrayList("a", "b", "c", "d", "e");
	private static final ImmutableList<String> OUT_EQUAL = ImmutableList.of("a", "b", "c", "d", "e");
	private static final ImmutableList<String> OUT_MAPPED = ImmutableList.of("A", "B", "C", "D", "E");

	@Test
	public void testCollections() {
		assertEquals(OUT_EQUAL, LambdaUtils.toList(IN.stream()));
		assertEquals(ImmutableSet.copyOf(OUT_EQUAL), LambdaUtils.toSet(IN.stream()));

		assertEquals(OUT_MAPPED, LambdaUtils.mapList(IN, String::toUpperCase));
		assertEquals(OUT_MAPPED, LambdaUtils.mapList(IN.stream(), String::toUpperCase));
		assertEquals(OUT_MAPPED, LambdaUtils.mapList(IN.toArray(new String[IN.size()]), String::toUpperCase));

		assertEquals(ImmutableSet.copyOf(OUT_MAPPED), LambdaUtils.mapSet(IN, String::toUpperCase));
		assertEquals(ImmutableSet.copyOf(OUT_MAPPED), LambdaUtils.mapSet(IN.stream(), String::toUpperCase));
		assertEquals(ImmutableSet.copyOf(OUT_MAPPED), LambdaUtils.mapSet(IN.toArray(new String[IN.size()]), String::toUpperCase));
	}


	@Test
	public void testMaps() {
		Map<String, String> in = Maps.newHashMap();
		in.put("a", "Z");
		in.put("b", "Y");
		in.put("c", "X");

		ImmutableMap<String, String> out = ImmutableMap.of("A", "z", "B", "y", "C", "x");

		assertEquals(out, LambdaUtils.mapMap(in, String::toUpperCase, String::toLowerCase));
	}


	@Test
	public void testEnumWithCode() {
		assertEquals(TestEnum.VAL_1, LambdaUtils.mapToEnumFromCode("a", TestEnum.class));
		assertEquals(TestEnum.VAL_2, LambdaUtils.mapToEnumFromCode("b", TestEnum.class));
		assertFalse(LambdaUtils.mapToEnumFromCodeOptional("not-a-code", TestEnum.class).isPresent());

		assertEquals(ImmutableSet.of("c", "a"), LambdaUtils.mapToEnumCodeSet(ImmutableList.of(TestEnum.VAL_3, TestEnum.VAL_1)));
		assertEquals(ImmutableSet.of(TestEnum.VAL_3, TestEnum.VAL_1), LambdaUtils.mapToEnumsFromCodes(ImmutableList.of("c", "a"), TestEnum.class));
	}


	@Test(expected = IllegalArgumentException.class)
	public void testEnumWithCodeMissing() {
		LambdaUtils.mapToEnumFromCode("not-a-code", TestEnum.class);
	}

	enum TestEnum implements EnumWithCode {

		VAL_1("a"), VAL_2("b"), VAL_3("c");

		private final String code;

		private TestEnum(String code) {
			this.code = code;
		}


		@Override
		public String code() {
			return code;
		}
	}
}
