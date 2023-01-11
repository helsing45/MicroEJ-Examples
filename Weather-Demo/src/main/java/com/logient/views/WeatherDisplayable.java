/**
 *
 */
package com.logient.views;

import com.logient.events.OnUiEventListener;
import com.logient.models.Coordinate;
import com.logient.models.DateTime;
import com.logient.models.weather.TemperatureFormat;
import com.logient.models.weather.WeatherTypes;
import com.logient.models.weather.Wind;
import com.logient.tools.Function;
import com.logient.tools.ObservableField;

import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;
import ej.microui.event.Event;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;
import ej.observable.Observer;
import ej.widget.basic.OnClickListener;

/**
 *
 */
public class WeatherDisplayable extends Displayable implements Observer, WeatherUI {
	public static final int ON_TEMPERATURE_FORMAT_CHANGE = 1;

	private final double HEIGHT_DIVISION_RATIO = 0.35;
	private final int PRIMARY_COLOR = 0x0081C9;
	private final int PRIMARY_VARIANT_COLOR = 0x5BC0F8;
	private final int ON_PRIMARY_COLOR = 0xE9E9E9;
	private final int SECONDARY_COLOR = 0x86E5FF;
	private final int ACCENT_COLOR = 0xFFC93C;
	private final int BACKGROUND_COLOR = 0xE9E9E9;
	private final int ON_BACKGROUND_COLOR = 0x646464;

	private final int width;
	private final int height;
	private final int heightCenter;

	private final ObservableField<WeatherTypes> weatherType = new ObservableField<>();
	private final ObservableField<Double> temperature = new ObservableField<>();
	private final ObservableField<TemperatureFormat> temperatureFormat = new ObservableField<>();
	private final ObservableField<Double> temperatureFeel = new ObservableField<>();
	private final ObservableField<Double> minTemp = new ObservableField<>();
	private final ObservableField<Double> maxTemps = new ObservableField<>();
	private final ObservableField<DateTime> currentDatetime = new ObservableField<>(new DateTime());
	private final ObservableField<Double> humidity = new ObservableField<>();
	private final ObservableField<Double> pressure = new ObservableField<>();
	private final ObservableField<Coordinate> coordinate = new ObservableField<>();
	private final ObservableField<Wind> wind = new ObservableField<>();
	private final ObservableField<Long> sunrise = new ObservableField<>();
	private final ObservableField<Long> sunset = new ObservableField<>();

	private final ObserverView[] containers = new ObserverView[10];

	private boolean firstRender = true;
	private OnUiEventListener uiEventListener;

	public WeatherDisplayable(Display display) {
		super();
		this.width = display.getWidth();
		this.height = display.getHeight();
		this.heightCenter = (int) (this.height * this.HEIGHT_DIVISION_RATIO);
		this.weatherType.addObserver(this);
		this.temperature.addObserver(this);
		this.temperatureFormat.addObserver(this);
		this.temperatureFeel.addObserver(this);
		this.minTemp.addObserver(this);
		this.maxTemps.addObserver(this);
		this.currentDatetime.addObserver(this);
		this.humidity.addObserver(this);
		this.pressure.addObserver(this);
		this.coordinate.addObserver(this);
		this.wind.addObserver(this);
		this.sunrise.addObserver(this);
		this.sunset.addObserver(this);
		int centerDiameter = (int) (Math.min(this.height, this.width) * 0.4);

		// Weather type
		WeatherContainer weatherContainer = new WeatherContainer(this.weatherType);
		weatherContainer.setBound((this.width / 2) - (centerDiameter / 2), this.heightCenter - centerDiameter / 2);
		weatherContainer.setDiameter(centerDiameter);
		weatherContainer.setBackgroundColor(this.BACKGROUND_COLOR);
		weatherContainer.setBorderColor(this.PRIMARY_VARIANT_COLOR);
		this.containers[0] = weatherContainer;

		// DATE TIME
		CurrentTimeView currentTimeView = new CurrentTimeView(this.currentDatetime);
		currentTimeView.setBound(12, 0);
		currentTimeView.setFontColor(this.ON_PRIMARY_COLOR);
		currentTimeView.setBackgroundColor(this.PRIMARY_COLOR);
		this.containers[1] = currentTimeView;

		int leftColumnXEnd = (this.width / 2) - (centerDiameter / 2) - 0;
		int detailColumnCellHeight = (this.height - this.heightCenter) / 3;

		// HUMIDITY
		WeatherDetailSection<Double> humidityDetailView = new WeatherDetailSection<>(this.humidity,
				"/images/icons/humidity.png");
		humidityDetailView.setBound(0, this.heightCenter, leftColumnXEnd, detailColumnCellHeight);
		humidityDetailView.setFontColor(this.ON_BACKGROUND_COLOR);
		humidityDetailView.setBackgroundColor(this.BACKGROUND_COLOR);
		humidityDetailView.setTransformer(new Function<Double, String>() {

			@Override
			public String compute(Double value) {
				return ((int) value.doubleValue()) + " %";
			}
		});
		this.containers[2] = humidityDetailView;

		// PRESSURE
		WeatherDetailSection<Double> pressureDetailView = new WeatherDetailSection<>(this.pressure,
				"/images/icons/pressure.png");
		pressureDetailView.setBound(0, this.heightCenter + detailColumnCellHeight, leftColumnXEnd,
				detailColumnCellHeight);
		pressureDetailView.setFontColor(this.ON_BACKGROUND_COLOR);
		pressureDetailView.setBackgroundColor(this.BACKGROUND_COLOR);
		pressureDetailView.setTransformer(new Function<Double, String>() {

			@Override
			public String compute(Double value) {
				return ((int) value.doubleValue()) + " kPa";
			}
		});
		this.containers[3] = pressureDetailView;

		// LOCATION
		WeatherDetailSection<Coordinate> locationDetailView = new WeatherDetailSection<>(this.coordinate,
				"/images/icons/location.png");
		locationDetailView.setBound(0, this.heightCenter + (detailColumnCellHeight * 2), leftColumnXEnd,
				detailColumnCellHeight);
		locationDetailView.setFontColor(this.ON_BACKGROUND_COLOR);
		locationDetailView.setBackgroundColor(this.BACKGROUND_COLOR);
		locationDetailView.setTransformer(new Function<Coordinate, String>() {

			@Override
			public String compute(Coordinate value) {
				return new StringBuffer().append(value.getLatitude()).append(" ,").append(value.getLongitude())
						.toString();
			}
		});
		this.containers[4] = locationDetailView;

		int rightColumnXStart = (this.width / 2) + (centerDiameter / 2) + 25;
		int rightColumnCellWidth = this.width - rightColumnXStart;
		// WIND
		WeatherDetailSection<Wind> windDetailView = new WeatherDetailSection<>(this.wind, "/images/icons/wind.png");
		windDetailView.setBound(rightColumnXStart, this.heightCenter, rightColumnCellWidth, detailColumnCellHeight);
		windDetailView.setFontColor(this.ON_BACKGROUND_COLOR);
		windDetailView.setBackgroundColor(this.BACKGROUND_COLOR);
		windDetailView.setTransformer(new Function<Wind, String>() {

			@Override
			public String compute(Wind value) {
				return new StringBuffer().append(value.getSpeed()).append(" m/s ")
						.append(value.getCardinalDirection().getAbbreviation()).toString();
			}
		});
		this.containers[5] = windDetailView;

		// SUNRISE
		WeatherDetailSection<Long> sunriseDetailView = new WeatherDetailSection<>(this.sunrise,
				"/images/icons/sunrise.png");
		sunriseDetailView.setBound(rightColumnXStart, this.heightCenter + detailColumnCellHeight, rightColumnXStart,
				detailColumnCellHeight);
		sunriseDetailView.setFontColor(this.ON_BACKGROUND_COLOR);
		sunriseDetailView.setBackgroundColor(this.BACKGROUND_COLOR);
		sunriseDetailView.setTransformer(new Function<Long, String>() {

			@Override
			public String compute(Long value) {
				DateTime sunrise = new DateTime(value.longValue() * 1000);
				return new StringBuffer().append(sunrise.getHourOfDay()).append(":").append(sunrise.getMinutes())
						.toString();
			}
		});
		this.containers[6] = sunriseDetailView;

		// SUNSET
		WeatherDetailSection<Long> sunsetDetailView = new WeatherDetailSection<>(this.sunset,
				"/images/icons/sunset.png");
		sunsetDetailView.setBound(rightColumnXStart, this.heightCenter + (detailColumnCellHeight * 2),
				rightColumnCellWidth, detailColumnCellHeight);
		sunsetDetailView.setFontColor(this.ON_BACKGROUND_COLOR);
		sunsetDetailView.setBackgroundColor(this.BACKGROUND_COLOR);
		sunsetDetailView.setTransformer(new Function<Long, String>() {

			@Override
			public String compute(Long value) {
				DateTime sunsetDateTime = new DateTime(value.longValue() * 1000);
				return new StringBuffer().append(sunsetDateTime.getHourOfDay()).append(":")
						.append(sunsetDateTime.getMinutes()).toString();
			}
		});
		this.containers[7] = sunsetDetailView;
		// Temperature
		TemperatureContainer temperatureContainer = new TemperatureContainer(this.temperature, this.temperatureFeel,
				this.minTemp, this.maxTemps, this.temperatureFormat);
		temperatureContainer.setBound((this.width / 2) - (centerDiameter / 2) - 15,
				this.heightCenter + (centerDiameter / 2), centerDiameter + 30, -1);
		temperatureContainer.setFontColor(this.ON_BACKGROUND_COLOR);
		temperatureContainer.setBackgroundColor(this.BACKGROUND_COLOR);
		this.containers[8] = temperatureContainer;

		TemperatureFormatButton temperatureFormatButton = new TemperatureFormatButton(this.temperatureFormat);
		temperatureFormatButton.setBound(this.width - 46, 0, 46, 46);
		temperatureFormatButton.setBackgroundColor(this.PRIMARY_COLOR);
		temperatureFormatButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				switchTemperatureFormat();
			}
		});
		this.containers[9] = temperatureFormatButton;
	}

	/**
	 *
	 */
	protected void switchTemperatureFormat() {
		if (this.uiEventListener != null) {
			this.uiEventListener.onUiEventTrigger(this.ON_TEMPERATURE_FORMAT_CHANGE);
		}
	}

	/**
	 * Sets the uiEventListener.
	 *
	 * @param uiEventListener
	 *            the uiEventListener to set.
	 */
	public void setUiEventListener(OnUiEventListener uiEventListener) {
		this.uiEventListener = uiEventListener;
	}

	@Override
	public boolean handleEvent(int event) {
		int type = Event.getType(event);
		if (type == Pointer.EVENT_TYPE) {
			int action = Buttons.getAction(event);
			if (action == Buttons.RELEASED) {
				Pointer pointer = (Pointer) Event.getGenerator(event);
				for (ObserverView observerView : this.containers) {
					System.out.println("RELEASED (" + pointer.getX() + ", " + pointer.getY() + ") -> view bound: "
							+ observerView.getBound().toString());
					if (observerView.getBound().contain(pointer.getX(), pointer.getY())) {
						observerView.handleEvent(event);
					}
				}
			}
		}

		return false;
	}

	@Override
	protected void render(GraphicsContext gc) {

		if (this.firstRender) {
			gc.setColor(this.PRIMARY_COLOR);
			Painter.fillRectangle(gc, 0, 0, this.width, this.heightCenter);
			gc.setColor(this.BACKGROUND_COLOR);
			Painter.fillRectangle(gc, 0, this.heightCenter, this.width, this.height);
			this.firstRender = false;
		}

		for (ObserverView<?> observerView : this.containers) {
			observerView.render(gc);
		}

	}

	@Override
	public void onSunriseChanged(long sunrise) {
		this.sunrise.set(Long.valueOf(sunrise));
	}

	@Override
	public void onSunsetChanged(long sunset) {
		this.sunset.set(Long.valueOf(sunset));
	}

	@Override
	public void onWeatherTypeChanged(WeatherTypes type) {
		this.weatherType.set(type);

	}

	@Override
	public void update() {
		requestRender();

	}

	@Override
	public void onDateTimeChanged(DateTime dateTime) {
		this.currentDatetime.set(dateTime);
	}

	@Override
	public void onTemperatureChanged(double temperature) {
		this.temperature.set(Double.valueOf(temperature));
	}

	@Override
	public void onFeltTemperatureChanged(double feltTemp) {
		this.temperatureFeel.set(Double.valueOf(feltTemp));
	}

	@Override
	public void onMinTemperatureChanged(double minTemp) {
		this.minTemp.set(Double.valueOf(minTemp));
	}

	@Override
	public void onMaxTemperatureChanged(double maxTemp) {
		this.maxTemps.set(Double.valueOf(maxTemp));
	}

	@Override
	public void onHumidityChanged(double humidity) {
		this.humidity.set(Double.valueOf(humidity));
	}

	@Override
	public void onPressureChanged(double pressure) {
		this.pressure.set(Double.valueOf(pressure));
	}

	@Override
	public void onCoordinateChanged(Coordinate coordinate) {
		this.coordinate.set(coordinate);
	}

	@Override
	public void onWindChanged(Wind wind) {
		this.wind.set(wind);
	}

	@Override
	public void onTemperatureFormatChanged(TemperatureFormat format) {
		this.temperatureFormat.set(format);
	}

}
