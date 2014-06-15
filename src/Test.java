import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;

import PaymentPckg.*;
import static AdditionalMethods.AdditionalMethods.*;

public class Test {
	/** ������� */
	static Display display;
	/** ������� ���� ��������� */
	static Shell shell;
	/** ���� �������� ��������� */
	Shell shCreateDocument;
	/** ���� ��������� ������� ��������� */
	Shell shWatchDocument;
	/** ������ ���������� */
	PaymentList paymentList = new PaymentList();
	/** ������� � ����������� */
	private static Table table;

	public Test() {
	}

	public void createControls(Composite parent) {
	}

	public Shell createShell() {
		// ������� ������ Display ��� ����� SWT
		// � �������� ������������ �������
		Display display = Display.getDefault();
		// ������� ���� ���������
		shell = new Shell(display);
		shell.setText("����");
		shell.setSize(550, 500);

		center(shell);
		initUI();

		shell.open();
		return shell;
	}

	// ������������� ���� �� ������ ������ ��� ������������
	public void center(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();
		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	// �������� ������ �� ������� �����
	public void initUI() {

		IObservableList input = BeanProperties.list(PaymentList.class,
				"payments").observe(paymentList);
		TableViewer viewer = new TableViewer(shell, SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION | SWT.CHECK);
		table = viewer.getTable();
		table.setBounds(10, 10, 320, 340);

		ViewerSupport.bind(viewer, input,
				BeanProperties.values(new String[] { "data" }));

		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					((Payment) event.item.getData()).setChecked(true);
				}
			}
		});

		Button waybill = new Button(shell, SWT.PUSH);
		waybill.setText("���������");
		waybill.setBounds(350, 20, 150, 30);
		waybill.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CreateDocument(DocumentTypes.Waybill);
			}
		});

		Button paymentsystem = new Button(shell, SWT.PUSH);
		paymentsystem.setText("��������");
		paymentsystem.setBounds(350, 70, 150, 30);
		paymentsystem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CreateDocument(DocumentTypes.PaymentSystem);
			}
		});

		Button applicationforpayment = new Button(shell, SWT.PUSH);
		applicationforpayment.setText("������ �� ������");
		applicationforpayment.setBounds(350, 120, 150, 30);
		applicationforpayment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CreateDocument(DocumentTypes.ApplicationForPayment);
			}
		});

		Button SaveBtn = new Button(shell, SWT.PUSH);
		SaveBtn.setText("���������");
		SaveBtn.setBounds(350, 170, 150, 30);
		SaveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (table.getSelectionIndices().length > 0) {
					createSaveToFile();
				}
			}
		});

		Button LoadBtn = new Button(shell, SWT.PUSH);
		LoadBtn.setText("���������");
		LoadBtn.setBounds(350, 220, 150, 30);
		LoadBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createLoadFromFile();
			}
		});

		Button WatchBtn = new Button(shell, SWT.PUSH);
		WatchBtn.setText("��������");
		WatchBtn.setBounds(350, 270, 150, 30);
		WatchBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (table.getSelectionIndices().length > 0) {
					Payment item = paymentList.getPayments().get(
							table.getSelectionIndices()[0]);
					CreateWatch(DocumentTypes.ApplicationForPayment, item);
				}
			}
		});

		Button delete = new Button(shell, SWT.PUSH);
		delete.setText("������� ����������");
		delete.setBounds(30, 380, 180, 30);
		delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymentList.removeMarked();
			}
		});

		Button quit = new Button(shell, SWT.PUSH);
		quit.setText("�����");
		quit.setBounds(350, 380, 150, 30);
		quit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.getDisplay().dispose();
				System.exit(0);
			}
		});
	}

	// ������� ���� ����� ��������
	void CreateDocument(DocumentTypes docType) {
		shCreateDocument = new Shell(display);
		shCreateDocument.setText(FieldNames.paymentTypes.get(docType));
		shCreateDocument.setSize(550, 570);
		center(shCreateDocument);
		initshCreateDocument(docType);
		shCreateDocument.open();

	}

	// �������� ������ �� ����� ����� ��������, ��������� ������� ������� �� ������
	void initshCreateDocument(DocumentTypes docType) {

		final DocumentTypes dt = docType;
		// shCreateDocument.setLayout(new GridLayout(1, false));
		Label numberLbl = new Label(shCreateDocument, SWT.LEFT);
		numberLbl.setText("�����:");
		numberLbl.setBounds(20, 20, 200, 30);
		final Text numberTxt = new Text(shCreateDocument, SWT.BORDER);
		numberTxt.setBounds(20, 50, 200, 30);

		Label dateLbl = new Label(shCreateDocument, SWT.LEFT);
		dateLbl.setText("����:");
		dateLbl.setBounds(20, 80, 200, 30);
		final Text dateTxt = new Text(shCreateDocument, SWT.BORDER);
		dateTxt.setBounds(20, 110, 200, 30);

		Label usernameLbl = new Label(shCreateDocument, SWT.LEFT);
		usernameLbl.setText("������������:");
		usernameLbl.setBounds(20, 140, 200, 30);
		final Text usernameTxt = new Text(shCreateDocument, SWT.BORDER);
		usernameTxt.setBounds(20, 170, 200, 30);

		Label summLbl = new Label(shCreateDocument, SWT.LEFT);
		summLbl.setText("�����:");
		summLbl.setBounds(20, 200, 200, 30);
		final Text summTxt = new Text(shCreateDocument, SWT.BORDER);
		summTxt.setBounds(20, 230, 200, 30);

		final Text contractorTxt = new Text(shCreateDocument, SWT.BORDER);
		final Text comissionTxt = new Text(shCreateDocument, SWT.BORDER);

		final Text employeeTxt = new Text(shCreateDocument, SWT.BORDER);

		final Text currencyTxt = new Text(shCreateDocument, SWT.BORDER);
		final Text courseTxt = new Text(shCreateDocument, SWT.BORDER);
		final Text goodsTxt = new Text(shCreateDocument, SWT.BORDER);
		final Text amountTxt = new Text(shCreateDocument, SWT.BORDER);

		final Text errorsTxt = new Text(shCreateDocument, SWT.MULTI
				| SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		errorsTxt.setBounds(250, 150, 250, 350);
		errorsTxt.setVisible(false);

		switch (dt) {
		case ApplicationForPayment:
			Label contractorLbl = new Label(shCreateDocument, SWT.LEFT);
			contractorLbl.setText("����������:");
			contractorLbl.setBounds(20, 260, 200, 30);
			contractorTxt.setBounds(20, 290, 200, 30);

			Label comissionLbl = new Label(shCreateDocument, SWT.LEFT);
			comissionLbl.setText("��������:");
			comissionLbl.setBounds(20, 320, 200, 30);
			comissionTxt.setBounds(20, 350, 200, 30);
			break;
		case PaymentSystem:
			Label employeeLbl = new Label(shCreateDocument, SWT.LEFT);
			employeeLbl.setText("���������:");
			employeeLbl.setBounds(20, 260, 200, 30);
			employeeTxt.setBounds(20, 290, 200, 30);
			break;
		case Waybill:
			Label currencyLbl = new Label(shCreateDocument, SWT.LEFT);
			currencyLbl.setText("������:");
			currencyLbl.setBounds(20, 260, 200, 30);
			currencyTxt.setBounds(20, 290, 200, 30);

			Label courseLbl = new Label(shCreateDocument, SWT.LEFT);
			courseLbl.setText("����:");
			courseLbl.setBounds(20, 320, 200, 30);
			courseTxt.setBounds(20, 350, 200, 30);

			Label goodsLbl = new Label(shCreateDocument, SWT.LEFT);
			goodsLbl.setText("�����:");
			goodsLbl.setBounds(20, 380, 200, 30);
			goodsTxt.setBounds(20, 410, 200, 30);

			Label amountLbl = new Label(shCreateDocument, SWT.LEFT);
			amountLbl.setText("����������:");
			amountLbl.setBounds(20, 440, 200, 30);
			amountTxt.setBounds(20, 470, 200, 30);
			break;
		default:
			break;
		}

		Button okBtn = new Button(shCreateDocument, SWT.PUSH);
		okBtn.setText("��");
		okBtn.setBounds(350, 20, 150, 30);
		okBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Payment paymentl;
				boolean showErrors = false;
				errorsTxt.setText("");
				
				String numberStr = numberTxt.getText();
				if (numberStr.isEmpty()) {
					showErrors = true;
					errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� ������.");
				}
				Date dateDt = new Date();
				try {
					dateDt = getDateFromString(dateTxt.getText());
				}
				catch (ParseException e1){
					showErrors = true;
					errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� ����.");
				}
				String usernameStr = usernameTxt.getText();
				if (usernameStr.isEmpty()) {
					showErrors = true;
					errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� ������������.");
				}
				
				double summDbl = 0;
				try {
					summDbl = Double.parseDouble(summTxt.getText());
				}
				catch (NumberFormatException e1) {
					showErrors = true;
					errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� �����.");
				}
				
				switch (dt) {
				case ApplicationForPayment:
					String contractorStr = contractorTxt.getText();
					if (contractorStr.isEmpty()) {
						showErrors = true;
						errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� �����������.");
					}
					double commisionDbl = 0;
					try {
						commisionDbl = Double.parseDouble(comissionTxt.getText());
					}
					catch (NumberFormatException e1) {
						showErrors = true;
						errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� ��������.");
					}
					paymentl = new ApplicationForPayment(numberStr, dateDt,
							usernameStr, summDbl, contractorStr,
							commisionDbl);
					break;
				case PaymentSystem:
					String employeeStr = employeeTxt.getText();
					if (employeeStr.isEmpty()) {
						showErrors = true;
						errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� ����������.");
					}
					paymentl = new PaymentSystem(numberStr, dateDt,
							usernameStr, summDbl, employeeStr);
					break;
				case Waybill:
					String currencyStr = currencyTxt.getText();
					if (currencyStr.isEmpty()) {
						showErrors = true;
						errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� ������.");
					}
					double courseDbl = 0;
					try {
						courseDbl = Double.parseDouble(courseTxt.getText());
					}
					catch (NumberFormatException e1) {
						showErrors = true;
						errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� �����.");
					}
					String goodsStr = goodsTxt.getText();
					if (goodsStr.isEmpty()) {
						showErrors = true;
						errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� ������.");
					}
					double amountDbl = 0;
					try {
						amountDbl = Double.parseDouble(amountTxt.getText());
					}
					catch (NumberFormatException e1) {
						showErrors = true;
						errorsTxt.setText(errorsTxt.getText() + "\n ��������� ������������ ����� ����������.");
					}
					paymentl = new Waybill(numberStr, dateDt, usernameStr,
							summDbl, currencyStr,
							courseDbl, goodsStr,amountDbl);
					break;
				default:
					paymentl = new Payment(numberStr, dateDt, usernameStr,
							summDbl);
					break;
				}
				if (!showErrors) {
					paymentList.addPayment(paymentl);
					shCreateDocument.close();
				} else {
					errorsTxt.setVisible(showErrors);
				}
			}
		});
		Button cancelBtn = new Button(shCreateDocument, SWT.PUSH);
		cancelBtn.setText("������");
		cancelBtn.setBounds(350, 70, 150, 30);
		cancelBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shCreateDocument.close();
			}
		});
	}

	/** ������� ���� ��������� */
	void CreateWatch(DocumentTypes docType, Payment item) {
		shWatchDocument = new Shell(display);
		shWatchDocument.setText(FieldNames.paymentTypes.get(docType));
		shWatchDocument.setSize(550, 500);
		center(shWatchDocument);
		initshCreateWatch(docType, item);
		shWatchDocument.open();
	}

	/** ��������� �������� ���� ��������� ��������� */
	void initshCreateWatch(DocumentTypes docType, Payment item) {
		Text dataTxt = new Text(shWatchDocument, SWT.MULTI | SWT.BORDER
				| SWT.WRAP | SWT.V_SCROLL);
		dataTxt.setBounds(10, 10, 320, 420);
		dataTxt.setText(item.GetProperties());

		Button okBtn = new Button(shWatchDocument, SWT.PUSH);
		okBtn.setText("��");
		okBtn.setBounds(370, 400, 150, 30);
		okBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shWatchDocument.close();
			}
		});
	}

	/** ��������� � ���� */
	void createSaveToFile() {
		final Shell shSaveToFile = new Shell(display);
		shSaveToFile.setText("��������� � ����");
		shSaveToFile.setSize(550, 350);
		center(shSaveToFile);

		Label fileNameLbl = new Label(shSaveToFile, SWT.LEFT);
		fileNameLbl.setText("������� ��� �����:");
		fileNameLbl.setBounds(20, 20, 300, 30);
		final Text fileNameTxt = new Text(shSaveToFile, SWT.BORDER);
		fileNameTxt.setBounds(20, 50, 300, 30);

		final Text errorsTxt = new Text(shSaveToFile, SWT.MULTI | SWT.BORDER
				| SWT.WRAP | SWT.V_SCROLL);
		errorsTxt.setBounds(20, 90, 300, 200);
		errorsTxt.setVisible(false);

		Button saveBtn = new Button(shSaveToFile, SWT.PUSH);
		saveBtn.setText("���������");
		saveBtn.setBounds(350, 200, 150, 30);
		saveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Payment item = paymentList.getPayments().get(
						table.getSelectionIndices()[0]);
				try {
					item.SaveToFile(fileNameTxt.getText());
					shSaveToFile.close();
				} catch (IOException e1) {
					errorsTxt
							.setText("��� ���������� ��������� ������.\n" + e1);
					errorsTxt.setVisible(true);
				}
			}
		});

		Button quit = new Button(shSaveToFile, SWT.PUSH);
		quit.setText("������");
		quit.setBounds(350, 240, 150, 30);
		quit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shSaveToFile.close();
			}
		});
		shSaveToFile.open();
	}

	/** ��������� �� ����� */
	void createLoadFromFile() {
		final Shell shLoadFromFile = new Shell(display);
		shLoadFromFile.setText("��������� �� �����");
		shLoadFromFile.setSize(550, 350);
		center(shLoadFromFile);

		Label fileNameLbl = new Label(shLoadFromFile, SWT.LEFT);
		fileNameLbl.setText("������� ��� �����:");
		fileNameLbl.setBounds(20, 20, 300, 30);
		final Text fileNameTxt = new Text(shLoadFromFile, SWT.BORDER);
		fileNameTxt.setBounds(20, 50, 300, 30);

		final Text errorsTxt = new Text(shLoadFromFile, SWT.MULTI | SWT.BORDER
				| SWT.WRAP | SWT.V_SCROLL);
		errorsTxt.setBounds(20, 90, 300, 200);
		errorsTxt.setVisible(false);

		Button saveBtn = new Button(shLoadFromFile, SWT.PUSH);
		saveBtn.setText("���������");
		saveBtn.setBounds(350, 200, 150, 30);
		saveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					paymentList.addPayment(PaymentFactory
							.getPaymentObject(fileNameTxt.getText()));
					shLoadFromFile.close();
				} catch (IOException e1) {
					errorsTxt
							.setText("��� ����� �� �����, ���� �� �� ����������.\n"
									+ e1);
					errorsTxt.setVisible(true);
				}
				catch (ParseException | NumberFormatException e2){
					errorsTxt
					.setText("��������� ������ ��� �������� �����, ��������� ������.\n"
							+ e2);
			errorsTxt.setVisible(true);
				}
			}
		});

		Button quit = new Button(shLoadFromFile, SWT.PUSH);
		quit.setText("������");
		quit.setBounds(350, 240, 150, 30);
		quit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shLoadFromFile.close();
			}
		});
		shLoadFromFile.open();
	}

	// ����� �����
	public static void main(String[] args) {
		new Test();
		//

		display = Display.getDefault();

		// In an RCP application, the threading Realm will be set for you
		// automatically by the Workbench. In an SWT application, you can do
		// this once, wrpping your binding method call.
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			@Override
			public void run() {

				Test t1 = new Test();
				t1.createShell();
				// The SWT event loop
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			}
		});

		//

	}
}
