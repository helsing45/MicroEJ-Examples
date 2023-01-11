/**
 *
 */
package com.logient.tools;

/**
 *
 */
public class Rect {
	private final int x;
	private final int y;
	private final int height;
	private final int width;

	/**
	 * @param x
	 * @param y
	 * @param height
	 * @param width
	 */
	public Rect(int x, int y, int height, int width) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width.
	 */
	public int getWidth() {
		return this.width;
	}

	public int getX2() {
		return this.x + this.width;
	}

	public int getY2() {
		return this.y + this.height;
	}

	public boolean contain(int x, int y) {
		return x >= this.x && x <= getX2() && y >= this.y && y <= getY2();
	}

	@Override
	public String toString() {
		return "Rect [x=" + this.x + ", y=" + this.y + ", x2=" + getX2() + ", y2=" + getY2() + "]";
	}

}
