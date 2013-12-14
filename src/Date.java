/**
 * Custom class to represent Dates more appropriately than integers.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class Date {
	/**
	 * The original date in ddmmyyyy form.
	 */
	private int inputDate;
	/**
	 * The extracted day.
	 */
	private int day;
	/**
	 * The extracted month.
	 */
	private int month;
	/**
	 * The extracted year.
	 */
	private int year;
	/**
	 * Date form as a printable string in dd/mm/yyyy form.
	 */
	private String date;

	/**
	 * Constructor for the date.
	 *
	 * @param inputDate an input date in the format ddmmyyyy
	 */
	public Date(int inputDate) {
		this.inputDate = inputDate;
		// check the inputDate is in dd/mm/yyyy form
		// in case the first digit is 0 (ie the day is less than 10),
		// allow for d/mm/yyyy form
		if (inputDate < 1000000 || inputDate > 99999999) {
			throw new IllegalArgumentException(
					"Date must be in dd/mm/yyyy form.");
		} else {
			// extract the year (last 4 digits of inputDate)
			year = inputDate % 10000;
			// extract the month (3rd and 4th digits of inputDate)
			month = inputDate / 10000 % 100;
			// extract the day (1st and 2nd digits of inputDate)
			day = inputDate / 1000000;

			// check the extracted month is valid
			if (month > 12 || month == 0) {
				throw new IllegalArgumentException(
						"Month must be between 1 to 12 inclusive.");
			}

			// determine how many days are in the extracted month
			int monthDays = monthDaysHelper(month);

			// check the extracted day is valid
			if (day > monthDays) {
				throw new IllegalArgumentException(
						"The day exceeds the amount in the month.");
			}

			// if all valid, store date in a more human readable dd/mm/yyyy
			// format
			date = formDate(day, month, year);
		}
	}

	/**
	 * Helper method to determine how many days are in the given month.
	 *
	 * @param month int between 1 and 12 inclusive (no checks though)
	 *
	 * @return int the amount of days
	 */
	private int monthDaysHelper(int month) {
		//set it initially to 31, most likely amount of days
		int monthDays = 31;
		//scan the month
		switch (month) {
			//if its February:
			case 2:
				if (year % 4 == 0) {
					monthDays = 29;
				} else {
					monthDays = 28;
				}
				break;
			//if its April:
			case 4:
				monthDays = 30;
				break;
			//if its June:
			case 6:
				monthDays = 30;
				break;
			//if its September:
			case 9:
				monthDays = 30;
				break;
			//if its November:
			case 11:
				monthDays = 30;
				break;
			//for all other months, do nothing. (days set to 31)
			default:
				break;
		}
		//return the amount of days
		return monthDays;
	}

	/**
	 * Universal helper method to give a readable dd/mm/yyyy format of a date.
	 *
	 * @param d the day
	 * @param m the month
	 * @param y the year
	 *
	 * @return the formatted string
	 */
	public static String formDate(int d, int m, int y) {
		String a = d + "/" + m + "/" + y;
		return a;
	}

	/**
	 * Getter method for the formatted date in dd/mm/yyyy format.
	 *
	 * @return the formatted string
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Getter method for the initial input date in ddmmyyyy format.
	 *
	 * @return an int of the input date
	 */
	public int getInputDate() {
		return inputDate;
	}

	/**
	 * Getter method for the formatted date in yyyymmdd format.
	 * Useful for sorting dates.
	 *
	 * @return the formatted date in an integer.
	 */
	public int getYYYYMMDDDate() {
		return 10000 * year + 100 * month + day;
	}
}
