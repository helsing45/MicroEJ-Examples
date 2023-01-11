/**
 *
 */
package com.logient.models.weather;

/**
 *
 */
public class Wind {
	private final double speed;
	private final double degree;
	private final double gust;
	private final CardinalPoint cardinalDirection;

	/**
	 * @param speed
	 * @param degree
	 * @param gust
	 */
	public Wind(double speed, double degree, double gust) {
		super();
		this.speed = speed;
		this.degree = degree;
		this.gust = gust;
		this.cardinalDirection = convertDegreeToCardinalDirection(degree);
	}

	private CardinalPoint convertDegreeToCardinalDirection(double directionInDegrees) {
		if ((directionInDegrees >= 348.75) && (directionInDegrees <= 360)
				|| (directionInDegrees >= 0) && (directionInDegrees <= 11.25)) {
			return CardinalPoint.NORTH;
		} else if ((directionInDegrees >= 11.25) && (directionInDegrees <= 33.75)) {
			return CardinalPoint.NORTH_NORTHEAST;
		} else if ((directionInDegrees >= 33.75) && (directionInDegrees <= 56.25)) {
			return CardinalPoint.NORTHEAST;
		} else if ((directionInDegrees >= 56.25) && (directionInDegrees <= 78.75)) {
			return CardinalPoint.EAST_NORTHEAST;
		} else if ((directionInDegrees >= 78.75) && (directionInDegrees <= 101.25)) {
			return CardinalPoint.EAST;
		} else if ((directionInDegrees >= 101.25) && (directionInDegrees <= 123.75)) {
			return CardinalPoint.EAST_SOUTHEAST;
		} else if ((directionInDegrees >= 123.75) && (directionInDegrees <= 146.25)) {
			return CardinalPoint.SOUTHEAST;
		} else if ((directionInDegrees >= 146.25) && (directionInDegrees <= 168.75)) {
			return CardinalPoint.SOUTH_SOUTHEAST;
		} else if ((directionInDegrees >= 168.75) && (directionInDegrees <= 191.25)) {
			return CardinalPoint.SOUTH;
		} else if ((directionInDegrees >= 191.25) && (directionInDegrees <= 213.75)) {
			return CardinalPoint.SOUTH_SOUTHWEST;
		} else if ((directionInDegrees >= 213.75) && (directionInDegrees <= 236.25)) {
			return CardinalPoint.SOUTHWEST;
		} else if ((directionInDegrees >= 236.25) && (directionInDegrees <= 258.75)) {
			return CardinalPoint.WEST_SOUTHWEST;
		} else if ((directionInDegrees >= 258.75) && (directionInDegrees <= 281.25)) {
			return CardinalPoint.WEST;
		} else if ((directionInDegrees >= 281.25) && (directionInDegrees <= 303.75)) {
			return CardinalPoint.WEST_NORTHWEST;
		} else if ((directionInDegrees >= 303.75) && (directionInDegrees <= 326.25)) {
			return CardinalPoint.NORTHWEST;
		} else if ((directionInDegrees >= 326.25) && (directionInDegrees <= 348.75)) {
			return CardinalPoint.NORTH_NORTHWEST;
		} else {
			return CardinalPoint.UNKNOWN;
		}
	}

	/**
	 * Gets the speed.
	 *
	 * @return the speed.
	 */
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Gets the degree.
	 *
	 * @return the degree.
	 */
	public double getDegree() {
		return this.degree;
	}

	/**
	 * Gets the gust.
	 *
	 * @return the gust.
	 */
	public double getGust() {
		return this.gust;
	}

	/**
	 * Gets the cardinalDirection.
	 *
	 * @return the cardinalDirection.
	 */
	public CardinalPoint getCardinalDirection() {
		return this.cardinalDirection;
	}

	@Override
	public String toString() {
		return "Wind [speed=" + this.speed + ", degree=" + this.degree + ", gust=" + this.gust + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.degree);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.gust);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.speed);
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
		Wind other = (Wind) obj;
		if (Double.doubleToLongBits(this.degree) != Double.doubleToLongBits(other.degree)) {
			return false;
		}
		if (Double.doubleToLongBits(this.gust) != Double.doubleToLongBits(other.gust)) {
			return false;
		}
		if (Double.doubleToLongBits(this.speed) != Double.doubleToLongBits(other.speed)) {
			return false;
		}
		return true;
	}

}
