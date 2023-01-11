/**
 *
 */
package com.logient.di.qualifiers;

/**
 *
 */
public class NamedQualifier implements Qualifier {
	private final String qualifier;

	/**
	 * @param qualifier
	 */
	public NamedQualifier(String qualifier) {
		super();
		this.qualifier = qualifier;
	}

	@Override
	public String getQualifier() {
		return this.qualifier;
	}

}
