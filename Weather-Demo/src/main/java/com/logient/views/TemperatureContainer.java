/**
 *
 */
package com.logient.views;

import com.logient.models.weather.TemperatureFormat;
import com.logient.tools.ObservableField;

import ej.drawing.ShapePainter;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;

/**
 *
 */
public class TemperatureContainer extends ObserverView<Double> {
	private final ObservableField<TemperatureFormat> temperatureFormat;
	private final ObservableField<Double> temperatureFeel;
	private final ObservableField<Double> minTemp;
	private final ObservableField<Double> maxTemps;
	private int fontColor = 0x000000;

	/**
	 * @param value
	 * @param temperatureFeel
	 * @param minTemp
	 * @param maxTemps
	 * @param temperatureFormat
	 */
	public TemperatureContainer(ObservableField<Double> temperature, ObservableField<Double> temperatureFeel,
			ObservableField<Double> minTemp, ObservableField<Double> maxTemps,
			ObservableField<TemperatureFormat> temperatureFormat) {
		super(temperature);
		this.temperatureFeel = temperatureFeel;
		this.temperatureFeel.addObserver(this);
		this.minTemp = minTemp;
		this.minTemp.addObserver(this);
		this.maxTemps = maxTemps;
		this.maxTemps.addObserver(this);
		this.temperatureFormat = temperatureFormat;
		this.temperatureFormat.addObserver(this);
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
		int translateX = gc.getTranslationX();
		int translateY = gc.getTranslationY();
		int currentX = this.x;
		int currentY = this.y;

		int formattedTemperature = (int) this.temperatureFormat.get().valueFromRaw(getValue().doubleValue());
		String message = new StringBuffer().append(formattedTemperature).append(" ")
				.append(this.temperatureFormat.get().getFormat()).toString();
		Font regular48 = Font.getFont("/fonts/kanit-regular-48.ejf");
		Font regular12 = Font.getFont("/fonts/kanit-regular-12pt.ejf");

		// Top divider
		Painter.drawLine(gc, 0, regular48.getHeight(), this.width, regular48.getHeight());

		int messageWidth = regular48.stringWidth(message);
		int paddingStart = (this.width - messageWidth) / 2;

		gc.translate(paddingStart, 0);
		gc.setColor(this.fontColor);
		Painter.drawString(gc, message, regular48, 0, 0);
		ShapePainter.drawThickFadedCircle(gc, messageWidth, 15, 5, 2, 2);

		gc.setTranslation(this.x, this.y + regular48.getHeight());
		drawLabel(gc, regular12, "Feel like:", this.temperatureFeel.get());
		drawLabel(gc, regular12, "Min temp:", this.minTemp.get());
		drawLabel(gc, regular12, "Max temp:", this.maxTemps.get());

		setHeight(gc.getTranslationY() - this.y);
	}

	private void drawLabel(GraphicsContext gc, Font font, String label, Double value) {
		int x = gc.getTranslationX();
		int y = gc.getTranslationY();

		String formattedValue = getFormattedTemperature(value);

		Painter.drawString(gc, label, font, 0, 0);
		gc.translate(this.width, 0);
		gc.translate(-font.stringWidth(formattedValue), 0);
		gc.translate(-5, 0);
		Painter.drawString(gc, formattedValue, font, 0, 0);
		gc.translate(font.stringWidth(formattedValue), 0);
		ShapePainter.drawThickFadedCircle(gc, 0, 5, 3, 1, 2);

		gc.setTranslation(x, y);
		gc.translate(0, font.getHeight());
	}

	private String getFormattedTemperature(Double value) {
		int formattedTemperature = (int) this.temperatureFormat.get().valueFromRaw(value.doubleValue());
		return new StringBuffer().append(formattedTemperature).append(" ")
				.append(this.temperatureFormat.get().getFormat()).toString();
	}

}
