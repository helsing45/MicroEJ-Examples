/**
 *
 */
package com.logient.tools.implementations;

import com.logient.tools.OpenWeatherAppIdProvider;

/**
 *
 */
public class OpenWeatherAppIdProviderImpl implements OpenWeatherAppIdProvider {

	@Override
	public String getAppId() {
		return System.getProperty("open.weather.app.id");
	}

}
