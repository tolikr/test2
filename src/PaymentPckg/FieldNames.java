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
		paymentTypes.put(DocumentTypes.ApplicationForPayment, "������ �� ������");
		paymentTypes.put(DocumentTypes.PaymentSystem, "��������");
		paymentTypes.put(DocumentTypes.Waybill, "���������");
		paymentTypes.put(DocumentTypes.Default, "����� ��������");
	}
	public static final EnumMap<Fields, String> FIELD_NAMES_Enum = 
			new EnumMap<Fields, String>(Fields.class);
	static {
		FIELD_NAMES_Enum.put(Fields.docType, "��� ���������");
		FIELD_NAMES_Enum.put(Fields.number, "�����");
		FIELD_NAMES_Enum.put(Fields.date, "����");
		FIELD_NAMES_Enum.put(Fields.username, "������������");
		FIELD_NAMES_Enum.put(Fields.summ, "�����");
		FIELD_NAMES_Enum.put(Fields.currency, "������");
		FIELD_NAMES_Enum.put(Fields.course, "���� ������");
		FIELD_NAMES_Enum.put(Fields.goods, "�����");
		FIELD_NAMES_Enum.put(Fields.amount, "����������");
		FIELD_NAMES_Enum.put(Fields.contractor, "����������");
		FIELD_NAMES_Enum.put(Fields.commission, "���������");
		FIELD_NAMES_Enum.put(Fields.employee, "���������");
		
	}
}
