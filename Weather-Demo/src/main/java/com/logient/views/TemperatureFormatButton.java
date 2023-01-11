/**
 *
 */
package com.logient.views;

import com.logient.models.weather.TemperatureFormat;
import com.logient.tools.ObservableField;

import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.display.Painter;
import ej.widget.basic.OnClickListener;

/**
 *
 */
public class TemperatureFormatButton extends ObserverView<TemperatureFormat> {

	private OnClickListener clickListener;
	private String iconPath;

	/**
	 * @param value
	 */
	public TemperatureFormatButton(ObservableField<TemperatureFormat> value) {
		super(value);
	}

	/**
	 * Sets the clickListener.
	 *
	 * @param clickListener
	 *            the clickListener to set.
	 */
	public void setOnClickListener(OnClickListener clickListener) {
		this.clickListener = clickListener;
	}

	@Override
	protected void renderContent(GraphicsContext gc) {
		Image icon = Image.getImage(this.iconPath);
		Painter.drawImage(gc, icon, 0, 0);
		setHeight(icon.getHeight());
		setWidth(icon.getWidth());
	}

	@Override
	protected void onValueChanged(TemperatureFormat value) {
		super.onValueChanged(value);
		StringBuilder sb = new StringBuilder().append("/images/icons/");
		switch (value) {
		case CELSIUS:
			sb.append("celcius");
			break;
		case FAHRENHEIT:
			sb.append("fahrenheit");
			break;
		case KELVIN:
			sb.append("kelvin");
			break;
		}
		sb.append(".png");
		this.iconPath = sb.toString();
	}

	@Override
	public boolean handleEvent(int event) {
		if (this.clickListener != null) {
			this.clickListener.onClick();
		}
		return super.handleEvent(event);
	}

}
