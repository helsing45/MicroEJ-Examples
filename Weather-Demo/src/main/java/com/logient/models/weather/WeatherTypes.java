/**
 *
 */
package com.logient.models.weather;

/**
 *
 */
public enum WeatherTypes {
	UNKNOWN(0, ""), THUNDERSTORM_WITH_LIGHT_RAIN(200, "11d"), THUNDERSTORM_WITH_RAIN(201,
			"11d"), THUNDERSTORM_WITH_HEAVY_RAIN(202, "11d"), LIGHT_THUNDERSTORM(210, "11d"), THUNDERSTORM(211,
					"11d"), HEAVY_THUNDERSTORM(212, "11d"), RAGGED_THUNDERSTORM(221,
							"11d"), THUNDERSTORM_WITH_LIGHT_DRIZZLE(230, "11d"), THUNDERSTORM_WITH_DRIZZLE(231,
									"11d"), THUNDERSTORM_WITH_HEAVY_DRIZZLE(232, "11d"), LIGHT_INTENSITY_DRIZZLE(300,
											"09d"), DRIZZLE(301, "09d"), HEAVY_INTENSITY_DRIZZLE(302,
													"09d"), LIGHT_INTENSITY_DRIZZLE_RAIN(310, "09d"), DRIZZLE_RAIN(311,
															"09d"), HEAVY_INTENSITY_DRIZZLE_RAIN(312,
																	"09d"), SHOWER_RAIN_AND_DRIZZLE(313,
																			"09d"), HEAVY_SHOWER_RAIN_AND_DRIZZLE(314,
																					"09d"), SHOWER_DRIZZLE(321,
																							"09d"), LIGHT_RAIN(500,
																									"10d"), MODERATE_RAIN(
																											501,
																											"10d"), HEAVY_INTENSITY_RAIN(
																													502,
																													"10d"), VERY_HEAVY_RAIN(
																															503,
																															"10d"), EXTREME_RAIN(
																																	504,
																																	"10d"), FREEZING_RAIN(
																																			511,
																																			"13d"), LIGHT_INTENSITY_SHOWER_RAIN(
																																					520,
																																					"09d"), SHOWER_RAIN(
																																							521,
																																							"09d"), HEAVY_INTENSITY_SHOWER_RAIN(
																																									522,
																																									"09d"), RAGGED_SHOWER_RAIN(
																																											531,
																																											"09d"), LIGHT_SNOW(
																																													600,
																																													"13d"), SNOW(
																																															601,
																																															"13d"), HEAVY_SNOW(
																																																	602,
																																																	"13d"), SLEET(
																																																			611,
																																																			"13d"), LIGHT_SHOWER_SLEET(
																																																					612,
																																																					"13d"), SHOWER_SLEET(
																																																							613,
																																																							"13d"), LIGHT_RAIN_AND_SNOW(
																																																									615,
																																																									"13d"), RAIN_AND_SNOW(
																																																											616,
																																																											"13d"), LIGHT_SHOWER_SNOW(
																																																													620,
																																																													"13d"), SHOWER_SNOW(
																																																															621,
																																																															"13d"), HEAVY_SHOWER_SNOW(
																																																																	622,
																																																																	"13d"), MIST(
																																																																			701,
																																																																			"50d"), SMOKE(
																																																																					711,
																																																																					"50d"), HAZE(
																																																																							721,
																																																																							"50d"), DUST_WHIRLS(
																																																																									731,
																																																																									"50d"), FOG(
																																																																											741,
																																																																											"50d"), SAND(
																																																																													751,
																																																																													"50d"), DUST(
																																																																															761,
																																																																															"50d"), VOLCANIC_ASH(
																																																																																	762,
																																																																																	"50d"), SQUALLS(
																																																																																			771,
																																																																																			"50d"), TORNADO(
																																																																																					781,
																																																																																					"50d"), CLEAR_SKY(
																																																																																							800,
																																																																																							"01d"), FEW_CLOUDS(
																																																																																									801,
																																																																																									"02d"), SCATTERED_CLOUDS(
																																																																																											802,
																																																																																											"03d"), BROKEN_CLOUDS(
																																																																																													803,
																																																																																													"03d"), OVERCAST_CLOUDS(
																																																																																															804,
																																																																																															"03d");

	private int id;
	private String iconId;

	/**
	 * @param id
	 * @param iconId
	 */
	private WeatherTypes(int id, String iconId) {
		this.id = id;
		this.iconId = iconId;
	}

	public static WeatherTypes findById(int id) {
		for (WeatherTypes type : WeatherTypes.values()) {
			if (type.id == id) {
				return type;
			}
		}
		throw new IllegalArgumentException(id + " is not a known id of WeatherTypes");
	}

	/**
	 * Gets the id.
	 *
	 * @return the id.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Gets the iconId.
	 *
	 * @return the iconId.
	 */
	public String getIconId() {
		return this.iconId;
	}

}
