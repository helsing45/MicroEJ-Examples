/**
 *
 */
package com.logient.models.weather;

/**
 *
 */
public enum CardinalPoint {
	UNKNOWN(""), NORTH("N"), NORTH_BY_EAST("NbE"), NORTH_NORTHEAST("NNE"), NORTHEAST_BY_NORTH("NEbN"), NORTHEAST(
			"NE"), NORTHEAST_BY_EAST("NEbE"), EAST_NORTHEAST("ENE"), EAST_BY_NORTH("EbN"), EAST("E"), EAST_BY_SOUTH(
					"EbS"), EAST_SOUTHEAST("ESE"), SOUTHEAST_BY_EAST("SEbE"), SOUTHEAST("SE"), SOUTHEAST_BY_SOUTH(
							"SEbS"), SOUTH_SOUTHEAST("SSE"), SOUTH_BY_EAST("SbE"), SOUTH("S"), SOUTH_BY_WEST(
									"SbW"), SOUTH_SOUTHWEST("SSW"), SOUTHWEST_BY_SOUTH("SWbS"), SOUTHWEST(
											"SW"), SOUTHWEST_BY_WEST("SWbW"), WEST_SOUTHWEST("WSW"), WEST_BY_SOUTH(
													"WbS"), WEST("W"), WEST_BY_NORTH("WbN"), WEST_NORTHWEST(
															"WNW"), NORTHWEST_BY_WEST("NWbW"), NORTHWEST(
																	"NW"), NORTHWEST_BY_NORTH("NWbN"), NORTH_NORTHWEST(
																			"NNW"), NORTH_BY_WEST("NbW");

	private final String abbreviation;

	/**
	 * @param abbreviation
	 */
	private CardinalPoint(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * Gets the abbreviation.
	 *
	 * @return the abbreviation.
	 */
	public String getAbbreviation() {
		return this.abbreviation;
	}
}
