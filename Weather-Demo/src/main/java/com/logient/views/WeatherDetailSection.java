/**
 *
 */
package com.logient.views;

import com.logient.tools.Function;
import com.logient.tools.ObservableField;

import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.display.Painter;

/**
 *
 */
public class WeatherDetailSection<T> extends ObserverView<T> {

	private final String iconPath;
	private Function<T, String> transformer;
	private int fontColor = 0x000000;

	/**
	 * @param value
	 * @param iconPath
	 */
	public WeatherDetailSection(ObservableField<T> value, String iconPath) {
		super(value);
		this.iconPath = iconPath;
	}

	/**
	 * Sets the transformer.
	 *
	 * @param transformer
	 *            the transformer to set.
	 */
	public void setTransformer(Function<T, String> transformer) {
		this.transformer = transformer;
	}

	/**
	 * Sets the fontColor.
	 *
	 * @param fontColor
	 *            the fontColor to set.
	 */
	public void setFontColor(int fontColor) {
		this.fontColor = fontColor;
	}

	@Override
	protected void renderContent(GraphicsContext gc) {
		gc.setTranslation(this.x, this.y);
		Image icon = Image.getImage(this.iconPath);
		int paddingTop = (this.height - icon.getHeight()) / 2;
		int paddingStart = 12;

		Painter.drawImage(gc, icon, paddingStart, paddingTop);
		gc.translate(icon.getWidth() + (paddingStart * 2), 0);
		gc.setColor(this.fontColor);

		Font font = Font.getFont("/fonts/kanit-regular-24.ejf");
		String message;
		if (getValue() == null || this.transformer == null) {
			message = "";
		} else {
			message = this.transformer.compute(getValue());
		}
		int messagePaddingTop = (this.height - font.getHeight()) / 2;
		Painter.drawString(gc, message, font, 0, messagePaddingTop);
	}

}
