/*
 *  Java
 *
 */
package com.logient;

import java.util.logging.Logger;

import com.logient.api.services.WeatherService;
import com.logient.controllers.WeatherController;
import com.logient.di.DI;
import com.logient.events.OnUiEventListener;
import com.logient.mappers.WeatherMapper;
import com.logient.tools.OpenWeatherAppIdProvider;
import com.logient.tools.implementations.OpenWeatherAppIdProviderImpl;
import com.logient.views.WeatherDisplayable;

import ej.bon.Timer;
import ej.bon.TimerTask;
import ej.microui.MicroUI;
import ej.microui.display.Display;

public class Main {

	public static final Logger LOGGER = java.util.logging.Logger.getLogger("Weather-demo"); //$NON-NLS-1$

	public static void main(String[] args) throws Exception {
		initDependencies();
		MicroUI.start();
		Display display = Display.getDisplay();

		WeatherDisplayable weatherDisplay = new WeatherDisplayable(display);
		final WeatherController weatherController = new WeatherController(DI.get(WeatherService.class), weatherDisplay);

		weatherDisplay.setUiEventListener(new OnUiEventListener() {

			@Override
			public void onUiEventTrigger(int event) {
				System.out.println("onUIEventTrigger::" + event);
				if (event == WeatherDisplayable.ON_TEMPERATURE_FORMAT_CHANGE) {
					weatherController.switchTemperatureFormat();
				}
			}
		});

		weatherController.fetchWeather(45.531, -73.518);

		display.requestShow(weatherDisplay);

		startCurrentTimeTask(weatherController);
	}

	/**
	 *
	 */
	private static void startCurrentTimeTask(final WeatherController controller) {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				controller.updateTime(System.currentTimeMillis());

			}
		};
		new Timer().schedule(task, 1000L, 1000L);
	}

	private static void initDependencies() throws Exception {
		DI.register(OpenWeatherAppIdProvider.class, new OpenWeatherAppIdProviderImpl());
		WeatherService weatherService = new WeatherService(new WeatherMapper(), DI.get(OpenWeatherAppIdProvider.class));

		DI.register(WeatherService.class, weatherService);

	}
}
