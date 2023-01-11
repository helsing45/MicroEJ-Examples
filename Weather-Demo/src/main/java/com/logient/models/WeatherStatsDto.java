/**
 *
 */
package com.logient.models;

/**
 *
 */
public class WeatherStatsDto {
	private final double temp;
	private final double feelsLike;
	private final double tempMin;
	private final double tempMax;
	private final int pressure;
	private final int humidity;
	private final int seaLevel;
	private final int grndLevel;

	public WeatherStatsDto(double temp, double feelsLike, double tempMin, double tempMax, int pressure, int humidity,
			int seaLevel, int grndLevel) {
		this.temp = temp;
		this.feelsLike = feelsLike;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.pressure = pressure;
		this.humidity = humidity;
		this.seaLevel = seaLevel;
		this.grndLevel = grndLevel;
	}

	/**
	 * Gets the temp.
	 *
	 * @return the temp.
	 */
	public double getTemp() {
		return this.temp;
	}

	/**
	 * Gets the feelsLike.
	 *
	 * @return the feelsLike.
	 */
	public double getFeelsLike() {
		return this.feelsLike;
	}

	/**
	 * Gets the tempMin.
	 *
	 * @return the tempMin.
	 */
	public double getTempMin() {
		return this.tempMin;
	}

	/**
	 * Gets the tempMax.
	 *
	 * @return the tempMax.
	 */
	public double getTempMax() {
		return this.tempMax;
	}

	/**
	 * Gets the pressure.
	 *
	 * @return the pressure.
	 */
	public int getPressure() {
		return this.pressure;
	}

	/**
	 * Gets the humidity.
	 *
	 * @return the humidity.
	 */
	public int getHumidity() {
		return this.humidity;
	}

	/**
	 * Gets the seaLevel.
	 *
	 * @return the seaLevel.
	 */
	public int getSeaLevel() {
		return this.seaLevel;
	}

	/**
	 * Gets the grndLevel.
	 *
	 * @return the grndLevel.
	 */
	public int getGrndLevel() {
		return this.grndLevel;
	}

}
