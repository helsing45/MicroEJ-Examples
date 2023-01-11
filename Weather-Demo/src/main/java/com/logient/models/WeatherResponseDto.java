/**
 *
 */
package com.logient.models;

import java.util.ArrayList;

/**
 *
 */
public class WeatherResponseDto {
	public Coordinate coordinate;
	public ArrayList<WeatherDto> weather;
	public String base;
	public WeatherStatsDto main;
	public int visibility;
	public WindDto wind;
	// public Clouds clouds;
	public int dt;
	public int timezone;
	public int id;
	public String name;
}
