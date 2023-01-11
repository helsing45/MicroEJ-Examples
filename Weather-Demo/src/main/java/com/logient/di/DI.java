/**
 *
 */
package com.logient.di;

import java.util.HashMap;
import java.util.Map;

import com.logient.di.qualifiers.ClassQualifier;
import com.logient.di.qualifiers.NamedQualifier;
import com.logient.di.qualifiers.Qualifier;

/**
 *
 */
public class DI {

	private DI() {
		super();
	}

	private static Map<String, Object> instances = new HashMap<>();

	public static <C, I> void register(Class<C> contract, I instance) {
		register(new ClassQualifier<C>(contract), instance);
	}

	public static <I> void register(String named, I instance) {
		register(new NamedQualifier(named), instance);
	}

	public static <I> I get(Class<I> clazz) {
		return get(new ClassQualifier(clazz));
	}

	public static <I> void register(Qualifier qualifier, I instance) {
		instances.put(qualifier.getQualifier(), instance);
	}

	public static <I> I get(String named) {
		return get(new NamedQualifier(named));
	}

	public static <I> I get(Qualifier qualifier) {
		return (I) instances.get(qualifier.getQualifier());
	}

}
