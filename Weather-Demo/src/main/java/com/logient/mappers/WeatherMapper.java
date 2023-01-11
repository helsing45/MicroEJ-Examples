/**
 *
 */
package com.logient.mappers;

import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.logient.api.JSONObjectMapper;
import com.logient.models.weather.Weather;

/**
 *
 */
public class WeatherMapper implements JSONObjectMapper<Weather> {

	@Override
	public String getOutputClassSimpleName() {
		return Weather.class.getSimpleName();
	}

	@Override
	public Weather transform(JSONObject input) throws JSONException {
		JSONObject weather = input.getJSONArray("weather").getJSONObject(0);
		JSONObject main = input.getJSONObject("main");
		JSONObject sys = input.getJSONObject("sys");
		JSONObject coord = input.getJSONObject("coord");
		JSONObject wind = input.getJSONObject("wind");

		return new Weather(getIntFromOrDefault(weather, "id", 0), getDoubleFromOrDefault(main, "temp", 0.0),
				getDoubleFromOrDefault(main, "feels_like", 0.0), getDoubleFromOrDefault(main, "temp_min", 0.0),
				getDoubleFromOrDefault(main, "temp_max", 0.0), getIntFromOrDefault(main, "pressure", -1),
				getIntFromOrDefault(main, "humidity", 0), getLongFromOrDefault(sys, "sunrise", 0),
				getLongFromOrDefault(sys, "sunset", 0), getDoubleFromOrDefault(wind, "speed", 0.0),
				getDoubleFromOrDefault(wind, "deg", 0.0), getDoubleFromOrDefault(wind, "gust", 0.0),
				getDoubleFromOrDefault(coord, "lon", 0.0), getDoubleFromOrDefault(coord, "lat", 0), 0);
	}

	private int getIntFromOrDefault(JSONObject object, String key, int defaultValue) {
		try {
			return object.getInt(key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return defaultValue;
		}
	}

	private double getDoubleFromOrDefault(JSONObject object, String key, double defaultValue) {
		try {
			return object.getDouble(key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return defaultValue;
		}
	}

	private long getLongFromOrDefault(JSONObject object, String key, long defaultValue) {
		try {
			return object.getLong(key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return defaultValue;
		}
	}

}
