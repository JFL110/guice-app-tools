package org.jfl110.util;

import java.util.Optional;

/**
 * Generic Tuple Class
 * 
 * @author jim
 */
public class Tuple<A, B> {

	private final A a;
	private final B b;

	/**
	 * @return a new Tuple instance
	 */
	public static <A, B> Tuple<A, B> newTuple(A a, B b) {
		return new Tuple<>(a, b);
	}


	private Tuple(A a, B b) {
		this.a = a;
		this.b = b;
	}


	/**
	 * @return the first value in this Tuple.
	 */
	public A a() {
		return a;
	}


	/**
	 * @return the second value in this Tuple.
	 */
	public B b() {
		return b;
	}


	/**
	 * @return this Tuple as an Optional
	 */
	public Optional<Tuple<A, B>> asOptional() {
		return Optional.of(this);
	}
}