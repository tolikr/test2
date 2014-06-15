package AdditionalMethods;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public final class AdditionalMethods {
	/** Получить double из String */
	public static double getDoubleFromString(String value) {
		return Double.parseDouble(value);
	}

	/** Получить дату из String 
	 * @throws ParseException */
	public static Date getDateFromString(String value) throws ParseException {
		DateFormat df = DateFormat.getDateInstance();
		Date d = df.parse(value);
		
		return d;
	}

	// Начало новой строки
	public static final String NewLine = "\n";
}
