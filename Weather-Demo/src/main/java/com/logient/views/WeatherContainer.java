/**
 *
 */
package com.logient.views;

import com.logient.models.weather.WeatherTypes;
import com.logient.tools.ObservableField;

import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.display.Painter;

/**
 *
 */
public class WeatherContainer extends ObserverView<WeatherTypes> {
	private int diameter;
	private int backgroundColor;
	private int borderColor;

	/**
	 * @param x
	 * @param y
	 * @param diameter
	 * @param backgroundColor
	 * @param borderColor
	 */
	public WeatherContainer(ObservableField<WeatherTypes> weatherType) {
		super(weatherType);
	}

	/**
	 * Sets the diameter.
	 *
	 * @param diameter
	 *            the diameter to set.
	 */
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	/**
	 * Sets the backgroundColor.
	 *
	 * @param backgroundColor
	 *            the backgroundColor to set.
	 */
	@Override
	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * Sets the borderColor.
	 *
	 * @param borderColor
	 *            the borderColor to set.
	 */
	public void setBorderColor(int borderColor) {
		this.borderColor = borderColor;
	}

	@Override
	protected void renderContent(GraphicsContext gc) {
		CircleContainer circleContainer = new CircleContainer(this.diameter / 2, this.diameter / 2, this.diameter,
				this.backgroundColor, this.borderColor);
		circleContainer.renderContent(gc);

		Image image1 = Image.getImage("/images/icons/" + getValue().getIconId() + ".png");
		int paddingTop = (this.diameter - image1.getHeight()) / 2;
		int paddingStart = (this.diameter - image1.getWidth()) / 2;
		gc.translate(0, 0);

		Painter.drawImage(gc, image1, paddingStart, paddingTop);
	}
}
