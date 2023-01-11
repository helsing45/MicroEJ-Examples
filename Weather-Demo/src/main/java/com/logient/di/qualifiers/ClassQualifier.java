/**
 *
 */
package com.logient.di.qualifiers;

/**
 *
 */
public class ClassQualifier<T> implements Qualifier {
	private final String qualifier;

	public ClassQualifier(Class<T> clazz) {
		super();
		this.qualifier = clazz.getName();
	}

	@Override
	public String getQualifier() {
		return this.qualifier;
	}

}
