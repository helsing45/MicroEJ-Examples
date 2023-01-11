/**
 *
 */
package com.logient.views;

import com.logient.tools.ObservableField;
import com.logient.tools.Rect;

import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;
import ej.observable.Observer;

/**
 *
 */
public abstract class ObserverView<T> implements Observer {
	private boolean needToRepaint = true;
	protected int BACKGROUND_COLOR = 0xFF0000;
	protected final ObservableField<T> value;
	protected int x;
	protected int y;
	protected int width = -1;
	protected int height = -1;

	/**
	 * @param _value
	 */
	public ObserverView(ObservableField<T> value) {
		super();
		this.value = value;
		this.value.addObserver(this);
	}

	public void setBound(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setBound(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	public Rect getBound() {
		return new Rect(this.x, this.y, this.width, this.height);
	}

	protected void setWidth(int width) {
		this.width = width;
	}

	protected void setHeight(int height) {
		this.height = height;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.BACKGROUND_COLOR = backgroundColor;
	}

	public T getValue() {
		return this.value.get();
	}

	@Override
	public void update() {
		onValueChanged(getValue());
	}

	public void onChanged() {
		this.needToRepaint = false;
	}

	protected void onValueChanged(T value) {
		this.needToRepaint = true;
	}

	public void render(GraphicsContext gc) {
		// System.out.println(this.getClass().getName() + "::render(" + this.needToRepaint + ")");
		if (!this.needToRepaint) {
			return;
		}
		onChanged();

		gc.setTranslation(this.x, this.y);
		clear(gc);

		renderContent(gc);

		gc.setTranslation(this.x, this.y);
	}

	protected void clear(GraphicsContext gc) {
		gc.setColor(this.BACKGROUND_COLOR);
		Painter.fillRectangle(gc, 0, 0, this.width, this.height);
	}

	public boolean handleEvent(int event) {
		return false;
	}

	protected abstract void renderContent(GraphicsContext gc);

}
