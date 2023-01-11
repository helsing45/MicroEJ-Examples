/**
 *
 */
package com.logient.controllers;

import com.logient.api.Response;
import com.logient.api.services.WeatherService;
import com.logient.models.DateTime;
import com.logient.models.weather.TemperatureFormat;
import com.logient.models.weather.Weather;
import com.logient.views.WeatherUI;

/**
 *
 */
public class WeatherController {
	private final WeatherService service;
	private final WeatherUI view;
	private Weather current;
	private final int weatherIndex = 0;
	private TemperatureFormat temperatureFormat;

	/**
	 * @param service
	 * @param view
	 */
	public WeatherController(WeatherService service, WeatherUI view) {
		super();
		this.service = service;
		this.view = view;
		setTemperatureFormat(TemperatureFormat.KELVIN);
	}

	public void fetchWeather(double lat, double lon) {
		try {
			Response<Weather> response = this.service.getWeatherAt(lat, lon);
			if (response.body != null) {
				onWeatherFetched(response.body);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onError() {
		// TODO display error
	}

	protected void onWeatherFetched(Weather weather) {

		if (this.current == weather) {
			return;
		}

		if (this.current == null || this.current.getTemp() != weather.getTemp()) {
			this.view.onTemperatureChanged(weather.getTemp());
		}
		if (this.current == null || this.current.getFeelsLike() != weather.getFeelsLike()) {
			this.view.onFeltTemperatureChanged(weather.getFeelsLike());
		}
		if (this.current == null || this.current.getTempMin() != weather.getTempMin()) {
			this.view.onMinTemperatureChanged(weather.getTempMin());
		}
		if (this.current == null || this.current.getTempMax() != weather.getTempMax()) {
			this.view.onMaxTemperatureChanged(weather.getTempMax());
		}
		if (this.current == null || this.current.getSunrise() != weather.getSunrise()) {
			this.view.onSunriseChanged(weather.getSunrise());
		}
		if (this.current == null || this.current.getSunset() != weather.getSunset()) {
			this.view.onSunsetChanged(weather.getSunset());
		}
		if (this.current == null || this.current.getType() != weather.getType()) {
			this.view.onWeatherTypeChanged(weather.getType());
		}
		if (this.current == null || this.current.getHumidity() != weather.getHumidity()) {
			this.view.onHumidityChanged(weather.getHumidity());
		}
		if (this.current == null || this.current.getPressure() != weather.getPressure()) {
			this.view.onPressureChanged(weather.getPressure());
		}
		if (this.current == null || this.current.getCoordinate() != weather.getCoordinate()) {
			this.view.onCoordinateChanged(weather.getCoordinate());
		}
		if (this.current == null || this.current.getWind() != weather.getWind()) {
			this.view.onWindChanged(weather.getWind());
		}
	}

	/**
	 * @param currentTimeMillis
	 */
	public void updateTime(long currentTimeMillis) {
		DateTime now = new DateTime(currentTimeMillis);
		this.view.onDateTimeChanged(now);
	}

	public void switchTemperatureFormat() {
		setTemperatureFormat(this.temperatureFormat.next());
	}

	private void setTemperatureFormat(TemperatureFormat temperatureFormat) {
		this.temperatureFormat = temperatureFormat;
		this.view.onTemperatureFormatChanged(temperatureFormat);
	}
}
