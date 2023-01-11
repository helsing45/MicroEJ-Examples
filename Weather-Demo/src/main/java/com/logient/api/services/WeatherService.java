/**
 *
 */
package com.logient.api.services;

import com.logient.api.JSONObjectMapper;
import com.logient.api.Response;
import com.logient.models.weather.Weather;
import com.logient.tools.OpenWeatherAppIdProvider;

public class WeatherService extends RestService {
	public static String NAMED_WEATHER_SERVICE = "weatherService-id";
	private final OpenWeatherAppIdProvider appIdProvider;

	public WeatherService(JSONObjectMapper<Weather> weatherMapper, OpenWeatherAppIdProvider appIdProvider)
			throws Exception {
		super("https://api.openweathermap.org/data/2.5/", "/certificates/_.openweathermap.org.crt");
		this.appIdProvider = appIdProvider;
		addMapper(weatherMapper);
	}

	public Response<Weather> getWeatherAt(double lat, double lon) throws Exception {
		return get("weather?lat=" + lat + "&lon=" + lon + "&appid=" + this.appIdProvider.getAppId(), Weather.class);
	}
}
