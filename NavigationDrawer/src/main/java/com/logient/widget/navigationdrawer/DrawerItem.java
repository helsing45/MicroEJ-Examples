/**
 *
 */
package com.logient.widget.navigationdrawer;

import com.logient.material.MaterialTheme;

import ej.annotation.Nullable;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;
import ej.microui.event.Event;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;
import ej.mwt.Widget;
import ej.mwt.style.Style;
import ej.mwt.util.Size;
import ej.widget.basic.OnClickListener;
import ej.widget.util.render.StringPainter;

/**
 *
 */
public class DrawerItem extends Widget {
	private @Nullable OnClickListener onClickListener;
	private final Widget destination;
	private boolean isSelected;
	private final String title;
	private final MaterialTheme theme;

	public DrawerItem(MaterialTheme theme, String title, Widget destination) {
		super();
		this.theme = theme;
		this.title = title;
		this.destination = destination;
		setEnabled(true);
	}

	/**
	 * Gets the isSelected.
	 *
	 * @return the isSelected.
	 */
	public boolean isSelected() {
		return this.isSelected;
	}

	/**
	 * Sets the isSelected.
	 *
	 * @param isSelected
	 *            the isSelected to set.
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		requestRender();
	}

	/**
	 * Gets the destination.
	 *
	 * @return the destination.
	 */
	public Widget getDestination() {
		return this.destination;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title.
	 */
	public String getTitle() {
		return this.title;
	}

	public void setOnClickListener(@Nullable OnClickListener listener) {
		this.onClickListener = listener;
	}

	@Override
	public boolean handleEvent(int event) {
		int type = Event.getType(event);
		if (type == Pointer.EVENT_TYPE) {
			int action = Buttons.getAction(event);
			if (action == Buttons.RELEASED) {
				handleClick();
				return true;
			}
		}

		return super.handleEvent(event);
	}

	public void handleClick() {
		OnClickListener listener = this.onClickListener;
		if (listener != null) {
			listener.onClick();
		}
	}

	@Override
	protected void renderContent(GraphicsContext g, int contentWidth, int contentHeight) {
		if (this.isSelected) {
			g.setColor(this.theme.colors.primaryVariant);
			Painter.fillRectangle(g, 0, 0, contentWidth, contentHeight);
		}
		Style style = getStyle();
		g.setColor(style.getColor());
		StringPainter.drawStringInArea(g, this.title, style.getFont(), 0, 0, contentWidth, contentHeight,
				style.getHorizontalAlignment(), style.getVerticalAlignment());
	}

	@Override
	protected void computeContentOptimalSize(Size size) {
		Font font = getStyle().getFont();
		StringPainter.computeOptimalSize(this.title, font, size);
	}
}