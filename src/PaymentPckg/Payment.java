package PaymentPckg;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import FileOperations.FileOperations;
import PaymentPckg.FieldNames.Fields;

// Класс содержит общие свойства платежных документов
public class Payment extends ModelObject {
	public Payment(String number,
			Date date,
			String username,
			double summ){
		this.setNumber(number);
		this.setDate(date);
		this.setUsername(username);
		this.setSumm(summ);
	}
	/**
	 * Номер
	 */
	private String number;
	/**Дата*/
	private Date date;
	/**Пользователь*/
	private String username;
	/**Сумма*/
	private double summ;
	/**Данные для главной формы*/
	private String data;
	/**Помечено в таблице*/
	private boolean checked;
	
	
	/**Показать все содержимое*/
	public String GetProperties() {
		String result;
		result = FieldNames.FIELD_NAMES_Enum.get(Fields.number) + ": " + getNumber() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		result += FieldNames.FIELD_NAMES_Enum.get(Fields.date) + ": " + getFormatDate() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		result += FieldNames.FIELD_NAMES_Enum.get(Fields.username) + ": " + getUsername() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		result += FieldNames.FIELD_NAMES_Enum.get(Fields.summ) + ": " + getSumm() + 
				AdditionalMethods.AdditionalMethods.NewLine;
		return result;
	}
	/**Выдать строку для сохранения в файл*/
	private String getDataToFile(){
		String result;
		result = FieldNames.FIELD_NAMES_Enum.get(Fields.docType) + ": " + this + 
				AdditionalMethods.AdditionalMethods.NewLine;
		result += GetProperties();
		return result;
	}
	
	/**Сохранить в файл
	 * @throws IOException */
	public void SaveToFile(String fileName) throws IOException{
		FileOperations.SaveToFile(fileName, getDataToFile());
	}
		
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		firePropertyChange("number", this.number, this.number = number);
	}
	public String getFormatDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		return dateFormat.format(this.date);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		firePropertyChange("date", this.date, this.date = date);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		firePropertyChange("username", this.username, this.username = username);
	}
	/**
	 * @return the summ
	 */
	public double getSumm() {
		return summ;
	}
	/**
	 * @param summ the summ to set
	 */
	public void setSumm(double summ) {
		firePropertyChange("summ", this.summ, this.summ = summ);
	}

	/**
	 * @return the data
	 */
	public String getData() {
		data = this + " от " + getFormatDate() + " номер " + this.number;
		return data;
	}

	public String toString(){
		return FieldNames.paymentTypes.get(DocumentTypes.Default);
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = !this.checked;
	}
}
