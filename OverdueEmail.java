package edu.byu.isys414.jmcmurdi.IntexII;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class OverdueEmail extends Dialog {

	protected Object result;
	protected Shell shell;
	private Button btnsend;
	

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public OverdueEmail(Shell parent, int style) {
		super(parent, style);

		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.APPLICATION_MODAL);
		shell.setSize(531, 589);
		shell.setText(getText());
		shell.setLayout(new BorderLayout(0, 0));

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		Button btnCancel = new Button(composite_1, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cancelclicked();
			}
		});
		btnCancel.setText("Cancel");

		Composite composite_3 = new Composite(composite_1, SWT.NONE);

		btnsend = new Button(composite_1, SWT.NONE);
		btnsend.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Sendclicked();
			}
		});
		btnsend.setText("Send Email");

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.WEST);

		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.NORTH);

		Composite composite_4 = new Composite(shell, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.EAST);

		Composite composite_5 = new Composite(shell, SWT.NONE);
		composite_5.setLayoutData(BorderLayout.CENTER);
		composite_5.setLayout(new BorderLayout(0, 0));

		Composite composite_6 = new Composite(composite_5, SWT.NONE);
		composite_6.setLayoutData(BorderLayout.NORTH);

		Composite composite_7 = new Composite(composite_5, SWT.NONE);
		composite_7.setLayoutData(BorderLayout.SOUTH);

		Label lblNewLabel = new Label(composite_5, SWT.BORDER | SWT.WRAP);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Lucida Grande", 33, SWT.NORMAL));
		lblNewLabel.setLayoutData(BorderLayout.CENTER);
		lblNewLabel.setText("This window will send an email to every customer who hasn't turned in their rental items and the item is past the due date.");

	}

	protected void Sendclicked() {
		btnsend.setEnabled(false);
		List<BusinessObject> rentals = null;
		try {
			rentals = BusinessObjectDAO.getInstance().searchForAll("Rental");
		} catch (DataException e) {
			e.printStackTrace();
		}
		for (BusinessObject bo : rentals) {
			Rental temprental = (Rental) bo;
			Date today = new Date();
			if (temprental.getDateDue().compareTo(today) <= 0) {

				if (temprental.isReminderSent() != true) {
					
					if (temprental.getDateIn() == null) {

						try {
							Transaction t = BusinessObjectDAO.getInstance().searchForBO("Transaction", new SearchCriteria("id", temprental.getTransid()));
							// System.out.println(t.getCustomerid());
							Customer c = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("id", t.getCustomerid()));
							String custEmail = c.getEmail();
							String message = "This email is to remind you that you haven't returned the item you rented from mystuffsonline.com - a myer Photography company. " +
									" <br>After 10 days you will be charged the replacement cost of the product";
							String subject = "Overdue Rental";
							String from = "DoNotReply@mystuffsonline.com";
							String fromname = "Robot";
							
							BatchEmail.send(from, fromname, custEmail, subject, message);
							temprental.setReminderSent(true);
							temprental.save();
							
							//send(String fromAddress, String fromName, String toAddress, String subject, String msg)

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

		}

		shell.dispose();

	}

	private void cancelclicked() {
		shell.dispose();
	}
}
