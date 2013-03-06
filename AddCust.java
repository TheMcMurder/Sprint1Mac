package edu.byu.isys414.jmcmurdi.IntexII;

import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddCust extends Dialog {

	protected Object result;
	protected Shell shlNewCust;
	private Text fName;
	private Text lName;
	private Text email;
	private Text phone;
	private Text address;
	private Text zip;
	private Combo stateCombo;
	private Customer cust = null;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddCust(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlNewCust.open();
		shlNewCust.layout();
		Display display = getParent().getDisplay();
		while (!shlNewCust.isDisposed()) {
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
		shlNewCust = new Shell(getParent(), SWT.APPLICATION_MODAL);
		shlNewCust.setSize(531, 589);
		shlNewCust.setText(getText());
		shlNewCust.setLayout(new BorderLayout(0, 0));
		
		Composite composite_1 = new Composite(shlNewCust, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exitpopup();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		btnNewButton.setText("Cancel");
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		
		Button btnNewButton_2 = new Button(composite_1, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Customersaveclicked();
			}

			
		});
		btnNewButton_2.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		btnNewButton_2.setText("Save");
		
		Composite composite = new Composite(shlNewCust, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(2, false));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblCustomerName = new Label(composite, SWT.NONE);
		lblCustomerName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCustomerName.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		lblCustomerName.setText("First Name:");
		
		fName = new Text(composite, SWT.BORDER);
		fName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblLastName = new Label(composite, SWT.NONE);
		lblLastName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLastName.setText("Last Name:");
		lblLastName.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		
		lName = new Text(composite, SWT.BORDER);
		lName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblEmail = new Label(composite, SWT.NONE);
		lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmail.setText("Email:");
		lblEmail.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		
		email = new Text(composite, SWT.BORDER);
		email.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblPhone = new Label(composite, SWT.NONE);
		lblPhone.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPhone.setText("Phone:");
		lblPhone.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		
		phone = new Text(composite, SWT.BORDER);
		phone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblAddress = new Label(composite, SWT.NONE);
		lblAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAddress.setText("Address:");
		lblAddress.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		
		address = new Text(composite, SWT.BORDER);
		address.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblState = new Label(composite, SWT.NONE);
		lblState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblState.setText("State:");
		lblState.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		
		stateCombo = new Combo(composite, SWT.READ_ONLY);
		stateCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		populateCombo();
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblZip = new Label(composite, SWT.NONE);
		lblZip.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblZip.setText("Zip:");
		lblZip.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		
		zip = new Text(composite, SWT.BORDER);
		zip.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}
	private void Customersaveclicked() {
		// TODO Auto-generated method stub
		try {
			Customer c = BusinessObjectDAO.getInstance().create("Customer");
			c.setFirstName(fName.getText());
			c.setLastName(lName.getText());
			c.setEmail(email.getText());
			c.setPhone(phone.getText());
			c.setAddress(address.getText());
			c.setZip(zip.getText());
			c.setState(stateCombo.getText());
			c.save();
			cust = c;
			
			exitpopup();
			
		} catch (DataException e) {
			
			e.printStackTrace();
		}
		
	}

	private void exitpopup() {
		shlNewCust.dispose();
		
	}

	private void populateCombo() {
		int i = 0;
		
		stateCombo.add("Alabama", i); i++;
		stateCombo.add("Alaska", i); i++;
		stateCombo.add("Arizona", i); i++;
		stateCombo.add("Arkansas", i); i++;
		stateCombo.add("California", i); i++;
		stateCombo.add("Colorado", i); i++;
		stateCombo.add("Connecticut", i); i++;
		stateCombo.add("Delaware", i); i++;
		stateCombo.add("Dist of Columbia", i); i++;
		stateCombo.add("Florida", i); i++;
		stateCombo.add("Georgia", i); i++;
		stateCombo.add("Hawaii", i); i++;
		stateCombo.add("Idaho", i); i++;
		stateCombo.add("Illinois", i); i++;
		stateCombo.add("Indiana", i); i++;
		stateCombo.add("Iowa", i); i++;
		stateCombo.add("Kansas", i); i++;
		stateCombo.add("Kentucky", i); i++;
		stateCombo.add("Louisiana", i); i++;
		stateCombo.add("Maine", i); i++;
		stateCombo.add("Maryland", i); i++;
		stateCombo.add("Massachusetts", i); i++;
		stateCombo.add("Michigan", i); i++;
		stateCombo.add("Minnesota", i); i++;
		stateCombo.add("Mississippi", i); i++;
		stateCombo.add("Missouri", i); i++;
		stateCombo.add("Montana", i); i++;
		stateCombo.add("Nebraska", i); i++;
		stateCombo.add("Nevada", i); i++;
		stateCombo.add("New Hampshire", i); i++;
		stateCombo.add("New Jersey", i); i++;
		stateCombo.add("New Mexico", i); i++;
		stateCombo.add("New York", i); i++;
		stateCombo.add("North Carolina", i); i++;
		stateCombo.add("North Dakota", i); i++;
		stateCombo.add("Ohio", i); i++;
		stateCombo.add("Oklahoma", i); i++;
		stateCombo.add("Oregon", i); i++;
		stateCombo.add("Pennsylvania", i); i++;
		stateCombo.add("Rhode Island", i); i++;
		stateCombo.add("South Carolina", i); i++;
		stateCombo.add("South Dakota", i); i++;
		stateCombo.add("Tennessee", i); i++;
		stateCombo.add("Texas", i); i++;
		stateCombo.add("Utah", i); i++;
		stateCombo.add("Vermont", i); i++;
		stateCombo.add("Virginia", i); i++;
		stateCombo.add("Washington", i); i++;
		stateCombo.add("West Virginia", i); i++;
		stateCombo.add("Wisconsin", i); i++;
		stateCombo.add("Wyoming", i); i++;
		
	}

	/**
	 * @return the cust
	 */
	public Customer getCust() {
		return cust;
	}

	/**
	 * @param cust the cust to set
	 */
	public void setCust(Customer cust) {
		this.cust = cust;
	}
}
