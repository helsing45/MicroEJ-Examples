/**
 *
 */
package com.logient.views;

import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;

/**
 *
 */
public class CircleContainer {
	private final int x;
	private final int y;
	private final int diameter;
	private final int BACKGROUND_COLOR;
	private final int BORDER_COLOR;

	/**
	 * @param x
	 * @param y
	 * @param diameter
	 */
	public CircleContainer(int x, int y, int diameter) {
		this(x, y, diameter, Colors.WHITE, Colors.BLUE);
	}

	/**
	 * @param x
	 * @param y
	 * @param diameter
	 * @param backgroundColor
	 * @param borderColor
	 */
	public CircleContainer(int x, int y, int diameter, int backgroundColor, int borderColor) {
		super();
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.BACKGROUND_COLOR = backgroundColor;
		this.BORDER_COLOR = borderColor;
	}

	public void renderContent(GraphicsContext gc) {
		gc.setColor(this.BACKGROUND_COLOR);
		drawFillCircle(gc, this.x, this.y, this.diameter);
		gc.setColor(this.BORDER_COLOR);
		drawFillCircle(gc, this.x, this.y, this.diameter - 5);

		gc.setColor(this.BACKGROUND_COLOR);
		drawFillCircle(gc, this.x, this.y, this.diameter - 8);

		gc.setColor(this.BORDER_COLOR);
		drawCircle(gc, this.x, this.y, this.diameter - 12);
	}

	private void drawFillCircle(GraphicsContext gc, int x, int y, int diameter) {
		int halfDiameter = diameter / 2;
		Painter.fillCircle(gc, x - halfDiameter, y - halfDiameter, diameter);
	}

	private void drawCircle(GraphicsContext gc, int x, int y, int diameter) {
		int halfDiameter = diameter / 2;
		Painter.drawCircle(gc, x - halfDiameter, y - halfDiameter, diameter);
	}

}
