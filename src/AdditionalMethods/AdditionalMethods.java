package AdditionalMethods;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public final class AdditionalMethods {
	/** �������� double �� String */
	public static double getDoubleFromString(String value) {
		return Double.parseDouble(value);
	}

	/** �������� ���� �� String 
	 * @throws ParseException */
	public static Date getDateFromString(String value) throws ParseException {
		DateFormat df = DateFormat.getDateInstance();
		Date d = df.parse(value);
		
		return d;
	}

	// ������ ����� ������
	public static final String NewLine = "\n";
}
