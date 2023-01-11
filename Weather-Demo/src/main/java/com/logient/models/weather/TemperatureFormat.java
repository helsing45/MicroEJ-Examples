/**
 *
 */
package com.logient.models.weather;

/**
 *
 */
public enum TemperatureFormat {
	KELVIN("K") {
		@Override
		public double valueFromRaw(double raw) {
			return raw;
		}
	},
	CELSIUS("C") {
		@Override
		public double valueFromRaw(double raw) {
			return raw - 273.15;
		}
	},
	FAHRENHEIT("F") {
		@Override
		public double valueFromRaw(double raw) {
			return raw * (9 / 5.0D) - 459.67;
		}
	};

	private String format;

	private TemperatureFormat(String format) {
		this.format = format;
	}

	/**
	 * Gets the format.
	 *
	 * @return the format.
	 */
	public String getFormat() {
		return this.format;
	}

	public TemperatureFormat next() {
		if (ordinal() == values().length - 1) {
			return values()[0];
		}
		return values()[ordinal() + 1];
	}

	public abstract double valueFromRaw(double raw);
}
