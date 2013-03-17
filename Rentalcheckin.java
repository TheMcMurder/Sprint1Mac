package edu.byu.isys414.jmcmurdi.IntexII;

import java.text.SimpleDateFormat;
import java.util.Date;

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

public class Rentalcheckin extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtrentalitemname;
	private Text txtavailability;
	private Text txtcustname;
	private Text txtduedate;
	private Text txttodaysdate;
	private Text txtfees;
	private Text text_6;
	private Product p;
	private PProduct pprod;
	private Rental rental;
	private Transaction t;
	private Customer c;
	private RevSource rs;
	private ConceptualRental cr;
	SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	private double fees;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Rentalcheckin(Shell parent, int style, Product prod) {
		super(parent, style);
		setText("SWT Dialog");
		this.p = prod;
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
		

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new GridLayout(2, false));

		Label lblRentalItem = new Label(composite, SWT.NONE);
		lblRentalItem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRentalItem.setText("Rental Item:");

		txtrentalitemname = new Text(composite, SWT.BORDER);
		txtrentalitemname.setEditable(false);
		txtrentalitemname.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		txtrentalitemname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblAvailable = new Label(composite, SWT.NONE);
		lblAvailable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAvailable.setText("Available:");

		txtavailability = new Text(composite, SWT.BORDER);
		txtavailability.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		txtavailability.setEditable(false);
		txtavailability.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

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

		Button btnCheckin = new Button(composite_1, SWT.NONE);
		btnCheckin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Checkinclick();
			}

		
		});
		btnCheckin.setText("Check In");

		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.CENTER);
		composite_2.setLayout(new GridLayout(2, false));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);

		Label lblCustomer = new Label(composite_2, SWT.NONE);
		lblCustomer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCustomer.setText("Customer:");

		txtcustname = new Text(composite_2, SWT.BORDER);
		txtcustname.setEditable(false);
		txtcustname.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		txtcustname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);

		Label lblDueDate = new Label(composite_2, SWT.NONE);
		lblDueDate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDueDate.setText("Due Date:");

		txtduedate = new Text(composite_2, SWT.BORDER);
		txtduedate.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		txtduedate.setEditable(false);
		txtduedate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);

		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Today's Date:");

		txttodaysdate = new Text(composite_2, SWT.BORDER);
		txttodaysdate.setEditable(false);
		txttodaysdate.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		txttodaysdate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);

		Label lblFees = new Label(composite_2, SWT.NONE);
		lblFees.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFees.setText("Fees Charged:");

		txtfees = new Text(composite_2, SWT.BORDER);
		txtfees.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		txtfees.setEditable(false);
		txtfees.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);

		Label lblNotes = new Label(composite_2, SWT.NONE);
		lblNotes.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNotes.setText("Notes:");

		text_6 = new Text(composite_2, SWT.BORDER | SWT.WRAP);
		GridData gd_text_6 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_6.heightHint = 110;
		text_6.setLayoutData(gd_text_6);
		filldata();
	}

	private void cancelclicked() {
		shell.dispose();
	}

	private void filldata() {
		//System.out.println("running like a boss " + p.getProdType());
		String prodid = p.getId();

		if (p.getProdType().equals("rental")) {
			try {
				pprod = BusinessObjectDAO.getInstance().searchForBO("PProduct", new SearchCriteria("id", prodid));
				//System.out.println("Physical prod = " + pprod.getId() + " " + pprod.getStatus());
				String rentalstatus = pprod.getStatus();
				rental = BusinessObjectDAO.getInstance().searchForBO("Rental", new SearchCriteria("forrentid", prodid));
				rs = BusinessObjectDAO.getInstance().searchForBO("Rental", new SearchCriteria("id", rental.getId()));
				
				t = BusinessObjectDAO.getInstance().searchForBO("Transaction", new SearchCriteria("id", rs.getTransid()));
				//System.out.println(t.getCustomerid());
				c = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("id", t.getCustomerid()));
				cr = BusinessObjectDAO.getInstance().searchForBO("ConceptualRental", new SearchCriteria("id", pprod.getCprodid() ));
				//System.out.println(c.getId());
				
				Date today = new Date();
				txtavailability.setText(rentalstatus);
				//System.out.println(rentalstatus);

				//System.out.println(pprod.getPpname());
				//System.out.println(rental.getDateDue());
				//System.out.println(today);
				
				txtcustname.setText(c.getFirstName() + " " + c.getLastName());
				txtduedate.setText(SDF.format(rental.getDateDue()) + "");
				
				txttodaysdate.setText(SDF.format(today));
				txtrentalitemname.setText(pprod.getPpname());
				
				if (rental.getDateDue().compareTo(today)<=0){
					int diffInDays = (int)( (today.getTime() - rental.getDateDue().getTime()) / (1000 * 60 * 60 * 24) );
					//System.out.println("LATE by " + diffInDays);
					Double latefeeperday = cr.getPricePerDay();
					fees = latefeeperday * diffInDays;
					txtfees.setText(fees+ "");
					//Run charge method //TODO
					

				}else{
					txtfees.setText("0.00");
				}
				pprod.setStatus("available");

					
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	private void Checkinclick() {
		try {
			pprod.save();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}