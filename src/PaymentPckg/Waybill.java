package PaymentPckg;

import java.util.Date;

import PaymentPckg.FieldNames.Fields;


public class Waybill extends Payment {
	public Waybill(String number,
			Date date,
			String username,
			double summ,
			String currency,
			double course,
			String goods,
			double amount){
		super(number, date, username, summ);
		this.setCurrency(currency);
		this.setCourse(course);
		this.setGoods(goods);
		this.setAmount(amount);
	}
	// Валюта
	private String currency;
	// Курс Валюты
	private double course;
	// Товар
	private String goods;
	// Количество
	private double amount;
	// Показать все содержимое
	public String GetProperties() {
		String result = super.GetProperties();
		result += FieldNames.FIELD_NAMES_Enum.get(Fields.currency) + ": " + getCurrency() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		result += FieldNames.FIELD_NAMES_Enum.get(Fields.course) + ": " + getCourse() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		result += FieldNames.FIELD_NAMES_Enum.get(Fields.goods) + ": " + getGoods() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		result += FieldNames.FIELD_NAMES_Enum.get(Fields.amount) + ": " + getAmount() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		return result;
	}
	public String toString() {
		return FieldNames.paymentTypes.get(DocumentTypes.Waybill);
	}
	/**
	 * @return the currency
	 */
	private String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	private void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the course
	 */
	private double getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	private void setCourse(double course) {
		this.course = course;
	}
	/**
	 * @return the goods
	 */
	private String getGoods() {
		return goods;
	}
	/**
	 * @param goods the goods to set
	 */
	private void setGoods(String goods) {
		this.goods = goods;
	}
	/**
	 * @return the amount
	 */
	private double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	private void setAmount(double amount) {
		this.amount = amount;
	}
}
