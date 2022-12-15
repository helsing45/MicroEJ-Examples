/**
 *
 */
package com.logient.material;

import ej.microui.display.Font;

/**
 *
 */
public class Fonts {
	public Font h1;
	public Font h2;
	public Font h3;
	public Font h4;
	public Font h5;
	public Font subtitle;
	public Font body1;
	public Font body2;

	/**
	 * @param h1
	 * @param h2
	 * @param h3
	 * @param h4
	 * @param subtitle
	 * @param body1
	 */
	public Fonts(Font h1, Font h2, Font h3, Font h4, Font h5, Font body1, Font body2, Font subtitle) {
		super();
		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;
		this.h5 = h5;
		this.body1 = body1;
		this.body2 = body2;
		this.subtitle = subtitle;
	}
}
