/**
 *
 */
package com.logient.views;

import com.logient.models.DateTime;
import com.logient.tools.ObservableField;
import com.logient.tools.Rect;

import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;

/**
 *
 */
public class CurrentTimeView extends ObserverView<DateTime> {
	private int fontColor;
	private Rect boundFirstLine;
	private Rect boundSecondLine;

	public CurrentTimeView(ObservableField<DateTime> datetime) {
		super(datetime);
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

	private String getMonthName(int month) {
		switch (month) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		}
		throw new IllegalArgumentException(month + " is not a valid month");
	}

	private String getOrdinalOf(int number) {
		if (number >= 11 && number <= 13) {
			return "th";
		}
		switch (number % 10) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		}
	}

	private String addPadding(int number) {
		if (number < 10) {
			return "0" + number;
		}
		return Integer.toString(number);
	}

	@Override
	protected void renderContent(GraphicsContext gc) {
		DateTime current = this.value.get();

		gc.setColor(this.fontColor);
		Font regular48 = Font.getFont("/fonts/kanit-regular-48.ejf");
		Font regular36 = Font.getFont("/fonts/kanit-regular-36.ejf");
		Font light16 = Font.getFont("/fonts/kanit-light-16pt.ejf");

		// First line
		int firstLinemessageWidth = 0;
		int firstLineMessageHeight = regular48.getHeight();

		String dayOfMonthMessage = Integer.toString(current.getDayOfMonth());
		Painter.drawString(gc, dayOfMonthMessage, regular48, 0, 0);
		firstLinemessageWidth += regular48.stringWidth(dayOfMonthMessage);

		String ordinalSuffix = getOrdinalOf(current.getDayOfMonth());
		Painter.drawString(gc, ordinalSuffix, light16, firstLinemessageWidth, 0);
		firstLinemessageWidth += light16.stringWidth(ordinalSuffix);

		String monthMessage = getMonthName(current.getMonth());
		Painter.drawString(gc, monthMessage, regular48, firstLinemessageWidth, 0);
		firstLinemessageWidth += regular48.stringWidth(monthMessage);

		this.boundFirstLine = new Rect(0, 0, firstLineMessageHeight, firstLinemessageWidth);

		// Second line
		int secondLineMessageWidth = 0;
		int secondLineMessageHeight = regular36.getHeight();
		String time = new StringBuffer().append(addPadding(current.getHourOfDay())).append(":")
				.append(addPadding(current.getMinutes())).append(":").append(addPadding(current.getSeconds()))
				.toString();
		secondLineMessageWidth += regular36.stringWidth(time);
		Painter.drawString(gc, time, regular36, 0, firstLineMessageHeight);

		this.boundSecondLine = new Rect(0, this.boundFirstLine.getY2(), secondLineMessageHeight,
				secondLineMessageWidth);
	}

	@Override
	protected void clear(GraphicsContext gc) {
		gc.setColor(this.BACKGROUND_COLOR);
		if (this.boundFirstLine != null) {
			Painter.fillRectangle(gc, this.boundFirstLine.getX(), this.boundFirstLine.getY(),
					this.boundFirstLine.getWidth(), this.boundFirstLine.getHeight());
		}
		if (this.boundSecondLine != null) {
			Painter.fillRectangle(gc, this.boundSecondLine.getX(), this.boundSecondLine.getY(),
					this.boundSecondLine.getWidth(), this.boundSecondLine.getHeight());
		}
	}

}
