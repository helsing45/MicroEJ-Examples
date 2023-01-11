package com.logient.models;

public class WindDto {
	private final double speed;
	private final int deg;
	private final double gust;

	/**
	 * @param speed
	 * @param deg
	 * @param gust
	 */
	public WindDto(double speed, int deg, double gust) {
		super();
		this.speed = speed;
		this.deg = deg;
		this.gust = gust;
	}

	/**
	 * Gets the speed.
	 *
	 * @return the speed.
	 */
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Gets the deg.
	 *
	 * @return the deg.
	 */
	public int getDegree() {
		return this.deg;
	}

	/**
	 * Gets the gust.
	 *
	 * @return the gust.
	 */
	public double getGust() {
		return this.gust;
	}

}