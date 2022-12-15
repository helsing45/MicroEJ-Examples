/**
 *
 */
package com.logient.material;

import ej.mwt.Widget;
import ej.mwt.style.Style;
import ej.mwt.stylesheet.Stylesheet;
import ej.mwt.stylesheet.cascading.CascadingStylesheet;

/**
 *
 */
public class MaterialTheme implements Stylesheet {

	public Colors colors;
	public Fonts fonts;
	public CascadingStylesheet stylesheet;

	/**
	 * @param colors
	 * @param fonts
	 * @param stylesheet
	 */
	public MaterialTheme(Colors colors, Fonts fonts, CascadingStylesheet stylesheet) {
		super();
		this.colors = colors;
		this.fonts = fonts;
		this.stylesheet = stylesheet;
	}

	@Override
	public Style getStyle(Widget widget) {
		return this.stylesheet.getStyle(widget);
	}

}
