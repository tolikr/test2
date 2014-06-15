package PaymentPckg;

import java.util.EnumMap;

public final class FieldNames {
	enum Fields {
		docType,
		number,
		date,
		username,
		summ,
		currency,
		course,
		goods,
		amount,
		contractor,
		commission,
		employee
	}

	public static final EnumMap<DocumentTypes,String> paymentTypes = 
			new EnumMap<DocumentTypes, String>(DocumentTypes.class);
	static {
		paymentTypes.put(DocumentTypes.ApplicationForPayment, "Заявка на оплату");
		paymentTypes.put(DocumentTypes.PaymentSystem, "Платежка");
		paymentTypes.put(DocumentTypes.Waybill, "Накладная");
		paymentTypes.put(DocumentTypes.Default, "Общий документ");
	}
	public static final EnumMap<Fields, String> FIELD_NAMES_Enum = 
			new EnumMap<Fields, String>(Fields.class);
	static {
		FIELD_NAMES_Enum.put(Fields.docType, "Тип документа");
		FIELD_NAMES_Enum.put(Fields.number, "Номер");
		FIELD_NAMES_Enum.put(Fields.date, "Дата");
		FIELD_NAMES_Enum.put(Fields.username, "Пользователь");
		FIELD_NAMES_Enum.put(Fields.summ, "Сумма");
		FIELD_NAMES_Enum.put(Fields.currency, "Валюта");
		FIELD_NAMES_Enum.put(Fields.course, "Курс Валюты");
		FIELD_NAMES_Enum.put(Fields.goods, "Товар");
		FIELD_NAMES_Enum.put(Fields.amount, "Количество");
		FIELD_NAMES_Enum.put(Fields.contractor, "Контрагент");
		FIELD_NAMES_Enum.put(Fields.commission, "Коммиссия");
		FIELD_NAMES_Enum.put(Fields.employee, "Сотрудник");
		
	}
}
