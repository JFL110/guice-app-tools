package org.jfl110.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Collection of commonly used method for Streams and Collections.
 * 
 * @author jim
 *
 */
public class LambdaUtils {

	public static <T, K> ImmutableSet<T> mapSet(Collection<K> collection, Function<? super K, T> mapper) {
		return ImmutableSet.copyOf(collection.stream().map(mapper).collect(Collectors.toSet()));
	}


	public static <T, K> ImmutableList<T> mapList(Collection<K> collection, Function<? super K, T> mapper) {
		return ImmutableList.copyOf(collection.stream().map(mapper).collect(Collectors.toList()));
	}


	public static <T, K> ImmutableSet<T> mapSet(Stream<K> stream, Function<? super K, T> mapper) {
		return ImmutableSet.copyOf(stream.map(mapper).collect(Collectors.toSet()));
	}


	public static <T, K> ImmutableList<T> mapList(Stream<K> stream, Function<? super K, T> mapper) {
		return ImmutableList.copyOf(stream.map(mapper).collect(Collectors.toList()));
	}


	public static <K, V, K2, V2> ImmutableMap<K2, V2> mapMap(
			Map<K, V> map,
			Function<? super K, K2> keyMapper,
			Function<? super V, V2> valueMapper) {
		return ImmutableMap.copyOf(map
				.entrySet()
				.stream()
				.collect(Collectors.toMap(
						k -> keyMapper.apply(k.getKey()),
						k -> valueMapper.apply(k.getValue()))));
	}


	public static <K> ImmutableSet<K> toSet(Stream<K> stream) {
		return ImmutableSet.copyOf(stream.collect(Collectors.toSet()));
	}


	public static <K> ImmutableList<K> toList(Stream<K> stream) {
		return ImmutableList.copyOf(stream.collect(Collectors.toList()));
	}


	public static <T, K> ImmutableList<T> mapList(K[] array, Function<? super K, T> mapper) {
		return ImmutableList.copyOf(Arrays.stream(array).map(mapper).collect(Collectors.toList()));
	}


	public static <T, K> ImmutableSet<T> mapSet(K[] array, Function<? super K, T> mapper) {
		return ImmutableSet.copyOf(Arrays.stream(array).map(mapper).collect(Collectors.toSet()));
	}


	public static <K extends EnumWithCode> ImmutableSet<String> mapToEnumCodeSet(Collection<K> collection) {
		return ImmutableSet.copyOf(collection.stream().map(EnumWithCode::code).collect(Collectors.toSet()));
	}


	public static <K extends Enum<K> & EnumWithCode> K mapToEnumFromCode(String code, Class<K> type) {
		for (K value : type.getEnumConstants()) {
			if (value.code().equals(code)) {
				return value;
			}
		}
		throw new IllegalArgumentException("No value of " + type.getSimpleName() + " for code " + code);
	}


	public static <K extends Enum<K> & EnumWithCode> Optional<K> mapToEnumFromCodeOptional(String code, Class<K> type) {
		for (K value : type.getEnumConstants()) {
			if (value.code().equals(code)) {
				return Optional.of(value);
			}
		}
		return Optional.empty();
	}


	public static <K extends Enum<K> & EnumWithCode> ImmutableSet<K> mapToEnumsFromCodes(Collection<String> collection, Class<K> type) {
		return mapSet(collection, v -> mapToEnumFromCode(v, type));
	}
}
