package Management;

public class Date {
	private int day;
	private int month;
	private int year;

	public Date(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public Date(String line) {
		String[] linedate = line.split("/");
		this.day = Integer.parseInt(linedate[0]);
		this.month = Integer.parseInt(linedate[1]);
		this.year = Integer.parseInt(linedate[2]);

	}

	public static boolean equalsDay(Date date1, Date date2) {
		if (date1.day == date2.day && date1.month == date2.month) {
			return true;
		}
		return false;
	}
	public Date copy() {
		return new Date(day, month, year);
	}

	public String toString() {
		return day + "/" + month + "/" + year;
	}

	// CHECKING THAT FEBRUARY IS NOW 28-29 OVER THE YEARS --- CHECKING REMAINING
	// MONTHS 30-31
	// CHECKING THE MONTHS BETWEEN 1-12 AND DAYS 1-31 / 30
	public int getDay() {
		return day;
	}

	public int numberOfDays() {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			return 31;
		else if (month == 4 || month == 6 || month == 9 || month == 11)
			return 30;
		else if ((month == 2) && (year % 4 == 0))
			return 29;
		else
			return 28;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	// **********THE FUNCTION THAT MAKES US GO TO THE NEXT DAY**********
	public Date oneDayAfter() {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10) {
			if (day == 31) {
				day = 1;
				month++;
			} else
				day++;
		} else if (month == 2) {// february
			if (year % 4 == 0 && day == 29) {
				day = 1;
				month++;
			} else if (year % 4 != 0 && day == 28) {
				day = 1;
				month++;
			} else
				day++;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day == 30) {
				day = 1;
				month++;
			} else
				day++;
		} else {// december
			if (day == 31) {
				day = 1;
				month = 1;
				year++;
			} else
				day++;
		}
		return this;
	}
}