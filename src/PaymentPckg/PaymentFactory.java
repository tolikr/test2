package PaymentPckg;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import FileOperations.FileOperations;
import PaymentPckg.FieldNames.Fields;
import static AdditionalMethods.AdditionalMethods.*;

public class PaymentFactory {
	public static Payment getPaymentObject(String filename) throws IOException, ParseException {
		StringTokenizer st = FileOperations.ReadFromFile(filename);
		Payment payment = new Payment("", new Date(), "", 0);
		Map<String, String> definitions = new HashMap<String, String>();
		while (st.hasMoreTokens()) {
			String key = st.nextToken().trim();
			String value = st.hasMoreTokens() ? st.nextToken().trim(): "";
			definitions.put(key, value);
		}
		String docType = definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.docType));
		switch (docType) {
		case "Накладная":
			payment = new Waybill(
					definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.number)),
					getDateFromString(definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.date))),
					definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.username)),
					getDoubleFromString(definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.summ))),
					definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.currency)),
					getDoubleFromString(definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.course))),
					definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.goods)),
					getDoubleFromString(definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.amount))));
			break;
		case "Платежка":
			payment = new PaymentSystem(
					definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.number)),
					getDateFromString(definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.date))),
					definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.username)),
					getDoubleFromString(definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.summ))),
					definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.employee)));
			break;
		case "Заявка на оплату":
			payment = new ApplicationForPayment(
					definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.number)),
					getDateFromString(definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.date))),
					definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.username)),
					getDoubleFromString(definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.summ))),
					definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.contractor)),
					getDoubleFromString(definitions.get(FieldNames.FIELD_NAMES_Enum.get(Fields.commission))));
			break;
		}
		return payment;
	}

}
