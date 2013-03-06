package edu.byu.isys414.jmcmurdi.IntexII;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Checkout extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text paid;
	private Text change;
	private double total;
	private boolean successful;
	private double changeamount;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Checkout(Shell parent, int style, double total) {
		super(parent, style);
		setText("SWT Dialog");
		this.total = total;
	}

	/**
	 * Open the dialog.
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
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.WEST);
		
		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.EAST);
		
		Composite composite_3 = new Composite(shell, SWT.NONE);
		composite_3.setLayoutData(BorderLayout.SOUTH);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button btnNewButton = new Button(composite_3, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exitpopup();
			}
		});
		btnNewButton.setText("Cancel");
		
		Composite composite_8 = new Composite(composite_3, SWT.NONE);
		
		Button btnNewButton_2 = new Button(composite_3, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exitpopup();
			}
		});
		btnNewButton_2.setText("Save");
		
		Composite composite_4 = new Composite(shell, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.CENTER);
		composite_4.setLayout(new BorderLayout(0, 0));
		
		Composite composite_6 = new Composite(composite_4, SWT.NONE);
		composite_6.setLayoutData(BorderLayout.CENTER);
		composite_6.setLayout(new BorderLayout(0, 0));
		
		Composite composite_10 = new Composite(composite_6, SWT.NONE);
		composite_10.setLayoutData(BorderLayout.CENTER);
		composite_10.setLayout(new GridLayout(2, false));
		new Label(composite_10, SWT.NONE);
		new Label(composite_10, SWT.NONE);
		
		Label lblTotalCost = new Label(composite_10, SWT.NONE);
		lblTotalCost.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTotalCost.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		lblTotalCost.setText("Total Cost:");
		
		text = new Text(composite_10, SWT.BORDER);
		text.setEditable(false);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_10, SWT.NONE);
		new Label(composite_10, SWT.NONE);
		
		Label lblAmountPaid = new Label(composite_10, SWT.NONE);
		lblAmountPaid.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAmountPaid.setText("Amount Paid:");
		lblAmountPaid.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		
		paid = new Text(composite_10, SWT.BORDER);
		paid.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_10, SWT.NONE);
		new Label(composite_10, SWT.NONE);
		new Label(composite_10, SWT.NONE);
		new Label(composite_10, SWT.NONE);
		
		Label lblChange = new Label(composite_10, SWT.NONE);
		lblChange.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChange.setText("Change:");
		lblChange.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		
		change = new Text(composite_10, SWT.BORDER);
		change.setEditable(false);
		change.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		change.setText("");
		change.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_10, SWT.NONE);
		new Label(composite_10, SWT.NONE);
		new Label(composite_10, SWT.NONE);
		
		Button btnNewButton_1 = new Button(composite_10, SWT.NONE);
		GridData gd_btnNewButton_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 139;
		gd_btnNewButton_1.heightHint = 57;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calculatechange();
			}
		});
		btnNewButton_1.setText("Caculate");
		
		Composite composite_7 = new Composite(composite_6, SWT.NONE);
		composite_7.setLayoutData(BorderLayout.NORTH);
		text.setText(total+"");

	}


	private void exitpopup() {
		shell.dispose();
	}


	private void calculatechange() {
		// TODO Auto-generated method stub
		try{
			Double amountpaid = Double.parseDouble(paid.getText());
			changeamount = amountpaid - total;
			
			this.change.setText(changeamount + "");
			this.successful = true;
			
		}catch (Exception e){
			errormessage("Only put numbers in the amount paid field");
		}
		
	}
	private void errormessage(String error) {
		String error_message = error;
		ErrorMessage2 em = new ErrorMessage2(shell, error_message, 0);
		em.open();
	    }

	/**
	 * @return the successful
	 */
	public boolean isSuccessful() {
		return successful;
	}

	/**
	 * @param successful the successful to set
	 */
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	/**
	 * @return the changeamount
	 */
	public double getChangeamount() {
		return changeamount;
	}

	/**
	 * @param changeamount the changeamount to set
	 */
	public void setChangeamount(double changeamount) {
		this.changeamount = changeamount;
	}

}
