/**
 *
 */
package com.logient.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 */
public class DateTime {
	private final long timeMillis;
	private final String weekDayFullname;
	private final int year;
	private final int month;
	private final int dayOfMonth;
	private final int hourOfDay;
	private final int minutes;
	private final int seconds;

	/**
	 *
	 */
	public DateTime() {
		this(System.currentTimeMillis());
	}

	/**
	 * @param timeMillis
	 */
	public DateTime(long timeMillis) {
		super();
		this.timeMillis = timeMillis;

		SimpleDateFormat ft = new SimpleDateFormat("EEEE::yyyy::MM::dd::HH::mm::ss");
		String nowFormatted = ft.format(new Date(timeMillis));

		String[] values = splitStr(nowFormatted, "::");
		this.weekDayFullname = values[0];
		this.year = Integer.parseInt(values[1]);
		this.month = Integer.parseInt(values[2]);
		this.dayOfMonth = Integer.parseInt(values[3]);
		this.hourOfDay = Integer.parseInt(values[4]) - 5;
		this.minutes = Integer.parseInt(values[5]);
		this.seconds = Integer.parseInt(values[6]);
	}

	/**
	 * Gets the timeMillis.
	 *
	 * @return the timeMillis.
	 */
	public long getTimeMillis() {
		return this.timeMillis;
	}

	/**
	 * Gets the weekDayFullname.
	 *
	 * @return the weekDayFullname.
	 */
	public String getWeekDayFullname() {
		return this.weekDayFullname;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year.
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month.
	 */
	public int getMonth() {
		return this.month;
	}

	/**
	 * Gets the dayOfMonth.
	 *
	 * @return the dayOfMonth.
	 */
	public int getDayOfMonth() {
		return this.dayOfMonth;
	}

	/**
	 * Gets the hourOfDay.
	 *
	 * @return the hourOfDay.
	 */
	public int getHourOfDay() {
		return this.hourOfDay;
	}

	/**
	 * Gets the minutes.
	 *
	 * @return the minutes.
	 */
	public int getMinutes() {
		return this.minutes;
	}

	/**
	 * Gets the seconds.
	 *
	 * @return the seconds.
	 */
	public int getSeconds() {
		return this.seconds;
	}

	private String[] splitStr(String str, String delim) {
		StringTokenizer stringTokenizer = new StringTokenizer(str, delim);
		String[] strArr = new String[stringTokenizer.countTokens()];

		int i = 0;
		while (stringTokenizer.hasMoreTokens()) {
			String currentToken = stringTokenizer.nextToken();
			strArr[i] = currentToken;
			i++;
		}
		return strArr;
	}

	@Override
	public String toString() {
		return "DateTime [timeMillis=" + this.timeMillis + ", weekDayFullname=" + this.weekDayFullname + ", year="
				+ this.year + ", month=" + this.month + ", dayOfMonth=" + this.dayOfMonth + ", hourOfDay="
				+ this.hourOfDay + ", minutes=" + this.minutes + ", seconds=" + this.seconds + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.dayOfMonth;
		result = prime * result + this.hourOfDay;
		result = prime * result + this.minutes;
		result = prime * result + this.month;
		result = prime * result + this.seconds;
		result = prime * result + (int) (this.timeMillis ^ (this.timeMillis >>> 32));
		result = prime * result + ((this.weekDayFullname == null) ? 0 : this.weekDayFullname.hashCode());
		result = prime * result + this.year;
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
		DateTime other = (DateTime) obj;
		if (this.dayOfMonth != other.dayOfMonth) {
			return false;
		}
		if (this.hourOfDay != other.hourOfDay) {
			return false;
		}
		if (this.minutes != other.minutes) {
			return false;
		}
		if (this.month != other.month) {
			return false;
		}
		if (this.seconds != other.seconds) {
			return false;
		}
		if (this.timeMillis != other.timeMillis) {
			return false;
		}
		if (this.weekDayFullname == null) {
			if (other.weekDayFullname != null) {
				return false;
			}
		} else if (!this.weekDayFullname.equals(other.weekDayFullname)) {
			return false;
		}
		if (this.year != other.year) {
			return false;
		}
		return true;
	}

}
