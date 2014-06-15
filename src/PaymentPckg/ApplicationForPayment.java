package PaymentPckg;

import java.util.Date;

import PaymentPckg.FieldNames.Fields;

public class ApplicationForPayment extends Payment {
	public ApplicationForPayment(String number,
			Date date,
			String username,
			double summ,
			String contractor,
			double commission){
		super(number, date, username, summ);
		this.setContractor(contractor);
		this.setCommission(commission);
	}
	// Контрагент
	private String contractor;
	// Коммиссия
	private double commission;
	// Показать все содержимое
	public String GetProperties() {
		String result = super.GetProperties();
		result += FieldNames.FIELD_NAMES_Enum.get(Fields.contractor) + ": " + getContractor() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		result += FieldNames.FIELD_NAMES_Enum.get(Fields.commission) + ": " + getCommission() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		return result;
	}
	public String toString() {
		return FieldNames.paymentTypes.get(DocumentTypes.ApplicationForPayment);
	}
	/**
	 * @return the contractor
	 */
	private String getContractor() {
		return contractor;
	}
	/**
	 * @param contractor the contractor to set
	 */
	private void setContractor(String contractor) {
		this.contractor = contractor;
	}
	/**
	 * @return the commission
	 */
	private double getCommission() {
		return commission;
	}
	/**
	 * @param commission the commission to set
	 */
	private void setCommission(double commission) {
		this.commission = commission;
	}
}
