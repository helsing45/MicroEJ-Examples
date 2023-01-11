/**
 *
 */
package com.logient.views;

import com.logient.models.Coordinate;
import com.logient.models.DateTime;
import com.logient.models.weather.TemperatureFormat;
import com.logient.models.weather.WeatherTypes;
import com.logient.models.weather.Wind;

/**
 *
 */
public interface WeatherUI {

	public void onTemperatureChanged(double temperature);

	public void onFeltTemperatureChanged(double feltTemp);

	public void onMinTemperatureChanged(double minTemp);

	public void onMaxTemperatureChanged(double maxTemp);

	public void onSunriseChanged(long sunrise);

	public void onSunsetChanged(long sunset);

	public void onWeatherTypeChanged(WeatherTypes type);

	public void onDateTimeChanged(DateTime dateTime);

	public void onHumidityChanged(double humidity);

	public void onPressureChanged(double pressure);

	public void onCoordinateChanged(Coordinate coordinate);

	public void onWindChanged(Wind wind);

	public void onTemperatureFormatChanged(TemperatureFormat format);
}
