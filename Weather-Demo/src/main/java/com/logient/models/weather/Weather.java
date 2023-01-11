/**
 *
 */
package com.logient.models.weather;

import com.logient.models.Coordinate;

/**
 *
 */
public class Weather {
	private final int conditionCode;
	private final double temp;
	private final double feelsLike;
	private final double tempMin;
	private final double tempMax;
	private final int pressure;
	private final int humidity;
	private final long sunrise;
	private final long sunset;
	private final Wind wind;
	private final Coordinate coordinate;
	private final int weatherQuantities;

	/**
	 * @param conditionCode
	 * @param temp
	 * @param feelsLike
	 * @param tempMin
	 * @param tempMax
	 * @param pressure
	 * @param humidity
	 * @param seaLevel
	 * @param groundLevel
	 * @param sunrise
	 * @param sunset
	 * @param windSpeed
	 * @param windDegree
	 * @param gust
	 * @param latitude
	 * @param longitude
	 * @param weatherQuantities
	 */
	public Weather(int conditionCode, double temp, double feelsLike, double tempMin, double tempMax, int pressure,
			int humidity, long sunrise, long sunset, double windSpeed, double windDegree, double gust, double latitude,
			double longitude, int weatherQuantities) {
		super();
		this.conditionCode = conditionCode;
		this.temp = temp;
		this.feelsLike = feelsLike;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.pressure = pressure;
		this.humidity = humidity;
		this.sunrise = sunrise;
		this.sunset = sunset;
		this.wind = new Wind(windSpeed, windDegree, gust);
		this.coordinate = new Coordinate(longitude, latitude);
		this.weatherQuantities = weatherQuantities;
	}

	/**
	 * @param conditionCode
	 * @param temp
	 * @param feelsLike
	 * @param tempMin
	 * @param tempMax
	 * @param pressure
	 * @param humidity
	 * @param seaLevel
	 * @param groundLevel
	 * @param sunrise
	 * @param sunset
	 * @param wind
	 * @param coordinate
	 * @param weatherQuantities
	 */
	public Weather(int conditionCode, double temp, double feelsLike, double tempMin, double tempMax, int pressure,
			int humidity, long sunrise, long sunset, Wind wind, Coordinate coordinate, int weatherQuantities) {
		super();
		this.conditionCode = conditionCode;
		this.temp = temp;
		this.feelsLike = feelsLike;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.pressure = pressure;
		this.humidity = humidity;
		this.sunrise = sunrise;
		this.sunset = sunset;
		this.wind = wind;
		this.coordinate = coordinate;
		this.weatherQuantities = weatherQuantities;
	}

	/**
	 * Gets the conditionCode.
	 *
	 * @return the conditionCode.
	 */
	public int getConditionCode() {
		return this.conditionCode;
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
	 * Gets the sunrise.
	 *
	 * @return the sunrise.
	 */
	public long getSunrise() {
		return this.sunrise;
	}

	/**
	 * Gets the sunset.
	 *
	 * @return the sunset.
	 */
	public long getSunset() {
		return this.sunset;
	}

	/**
	 * Gets the coordinate.
	 *
	 * @return the coordinate.
	 */
	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type.
	 */
	private WeatherTypes type = null;

	public WeatherTypes getType() {
		if (this.type == null) {
			try {
				this.type = WeatherTypes.findById(this.conditionCode);
			} catch (Exception e) {
				// ignore
			}
		}
		return this.type;
	}

	/**
	 * Gets the weatherQuantities.
	 *
	 * @return the weatherQuantities.
	 */
	public int getWeatherQuantities() {
		return this.weatherQuantities;
	}

	/**
	 * Gets the wind.
	 *
	 * @return the wind.
	 */
	public Wind getWind() {
		return this.wind;
	}

	@Override
	public String toString() {
		return "Weather [conditionCode=" + this.conditionCode + ", temp=" + this.temp + ", feelsLike=" + this.feelsLike
				+ ", tempMin=" + this.tempMin + ", tempMax=" + this.tempMax + ", pressure=" + this.pressure
				+ ", humidity=" + this.humidity + ", sunrise=" + this.sunrise + ", sunset=" + this.sunset + ", wind="
				+ this.wind + ", coordinate=" + this.coordinate + ", weatherQuantities=" + this.weatherQuantities
				+ ", getType()=" + getType() + "]";
	}

	public static class Builder {
		private int conditionCode = 200;
		private double temp = 270.69;
		private double feelsLike = 264.49;
		private double tempMin = 269.9;
		private double tempMax = 271.09;
		private int pressure = 1013;
		private int humidity = 83;
		private long sunrise = 1673267592;
		private long sunset = 1673299739;
		private Wind wind = new Wind(6.17, 240, 0);
		private Coordinate coordinate = new Coordinate(-73.5706, 45.4963);
		private int weatherQuantities = 0;

		/**
		 * Sets the conditionCode.
		 *
		 * @param conditionCode
		 *            the conditionCode to set.
		 */
		public Builder setConditionCode(int conditionCode) {
			this.conditionCode = conditionCode;
			return this;
		}

		/**
		 * Sets the temp.
		 *
		 * @param temp
		 *            the temp to set.
		 */
		public Builder setTemp(double temp) {
			this.temp = temp;
			return this;
		}

		/**
		 * Sets the feelsLike.
		 *
		 * @param feelsLike
		 *            the feelsLike to set.
		 */
		public Builder setFeelsLike(double feelsLike) {
			this.feelsLike = feelsLike;
			return this;
		}

		/**
		 * Sets the tempMin.
		 *
		 * @param tempMin
		 *            the tempMin to set.
		 */
		public Builder setTempMin(double tempMin) {
			this.tempMin = tempMin;
			return this;
		}

		/**
		 * Sets the tempMax.
		 *
		 * @param tempMax
		 *            the tempMax to set.
		 */
		public Builder setTempMax(double tempMax) {
			this.tempMax = tempMax;
			return this;
		}

		/**
		 * Sets the pressure.
		 *
		 * @param pressure
		 *            the pressure to set.
		 */
		public Builder setPressure(int pressure) {
			this.pressure = pressure;
			return this;
		}

		/**
		 * Sets the humidity.
		 *
		 * @param humidity
		 *            the humidity to set.
		 */
		public Builder setHumidity(int humidity) {
			this.humidity = humidity;
			return this;
		}

		/**
		 * Sets the sunrise.
		 *
		 * @param sunrise
		 *            the sunrise to set.
		 */
		public Builder setSunrise(long sunrise) {
			this.sunrise = sunrise;
			return this;
		}

		/**
		 * Sets the sunset.
		 *
		 * @param sunset
		 *            the sunset to set.
		 */
		public Builder setSunset(long sunset) {
			this.sunset = sunset;
			return this;
		}

		/**
		 * Sets the wind.
		 *
		 * @param wind
		 *            the wind to set.
		 */
		public Builder setWind(Wind wind) {
			this.wind = wind;
			return this;
		}

		/**
		 * Sets the coordinate.
		 *
		 * @param coordinate
		 *            the coordinate to set.
		 */
		public Builder setCoordinate(Coordinate coordinate) {
			this.coordinate = coordinate;
			return this;
		}

		/**
		 * Sets the weatherQuantities.
		 *
		 * @param weatherQuantities
		 *            the weatherQuantities to set.
		 */
		public Builder setWeatherQuantities(int weatherQuantities) {
			this.weatherQuantities = weatherQuantities;
			return this;
		}

		public Weather build() {
			return new Weather(this.conditionCode, this.temp, this.feelsLike, this.tempMin, this.tempMax, this.pressure,
					this.humidity, this.sunrise, this.sunset, this.wind, this.coordinate, this.weatherQuantities);
		}

	}

}
