package misl.testcode;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MinusDate {
	public static void main(String[] args) {
		LocalDate from = LocalDate.parse("2017-05-26", DateTimeFormatter.ISO_LOCAL_DATE).withDayOfMonth(1);
		LocalDate to = LocalDate.parse("2018-06-28", DateTimeFormatter.ISO_LOCAL_DATE).withDayOfMonth(1);
		Period diff = Period.between(from,to);
		
		System.out.println(diff);
		
		long monthsBetween = ChronoUnit.MONTHS.between(from,to);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		System.out.println(from.format(format)+", Diff:"+monthsBetween);
		
		System.out.println("Loop");
		for (int i = 0; i <= monthsBetween; i++) {
			System.out.println(from.plusMonths(i).format(format));
		}
	}

}
