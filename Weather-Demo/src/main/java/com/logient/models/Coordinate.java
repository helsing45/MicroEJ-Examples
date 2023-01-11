/**
 *
 */
package com.logient.models;

/**
 *
 */
public class Coordinate {
	private final double longitude;
	private final double latitude;

	/**
	 * @param longitude
	 * @param latitude
	 */
	public Coordinate(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude.
	 */
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude.
	 */
	public double getLatitude() {
		return this.latitude;
	}

	@Override
	public String toString() {
		return "Coordinate [longitude=" + this.longitude + ", latitude=" + this.latitude + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
			return false;
		}
		if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
			return false;
		}
		return true;
	}

}
