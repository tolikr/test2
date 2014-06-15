package PaymentPckg;

import java.util.Date;

import PaymentPckg.FieldNames.Fields;


public class PaymentSystem extends Payment {
	public PaymentSystem(String number,
			Date date,
			String username,
			double summ,
			String employee){
		super(number, date, username, summ);
		this.setEmployee(employee);
	}
	// Сотрудник
	private String employee;
	// Показать все содержимое
	public String GetProperties() {
		String result = super.GetProperties();
		result += FieldNames.FIELD_NAMES_Enum.get(Fields.employee) + ": " + getEmployee() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		return result;
	}
	public String toString() {
		return FieldNames.paymentTypes.get(DocumentTypes.PaymentSystem);
	}
	/**
	 * @return the employee
	 */
	private String getEmployee() {
		return employee;
	}
	/**
	 * @param employee the employee to set
	 */
	private void setEmployee(String employee) {
		this.employee = employee;
	}
}
