package utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class ValidationRule {
	
	public static Date validateDate(String date)
	{
		LocalDate dob = LocalDate.parse(date);
		
		int year = Period.between(dob, LocalDate.now()).getYears();
		if(year>21)
			return Date.valueOf(date);
		return null;
		
	}

}
