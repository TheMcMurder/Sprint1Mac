package edu.byu.isys414.jmcmurdi.IntexII;


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

public class RentalPrompt extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text Itemid;
	private Text available;
	private Text whorenting;
	private Text ppd;
	private Text txtcost;
	private Text txtRemindOurGuests;
	private int days;
	private Product p = null;;
	private String custname = null;
	private PProduct pprod = null;
	private Spinner spinner;
	

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public RentalPrompt(Shell parent, int style, Product p, String custstring) {
		super(parent, style);
		this.p = p;
		this.custname = custstring;
		
		setText("SWT Dialog");
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
		composite.setLayout(new GridLayout(2, false));
		
		Label lblRentalItem = new Label(composite, SWT.NONE);
		lblRentalItem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRentalItem.setText("Rental Item:");
		
		Itemid = new Text(composite, SWT.BORDER);
		Itemid.setEditable(false);
		Itemid.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		Itemid.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblAvailable = new Label(composite, SWT.NONE);
		lblAvailable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAvailable.setText("Available:");
		
		available = new Text(composite, SWT.BORDER);
		available.setEditable(false);
		available.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		available.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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
		
		Button btnAddToCart = new Button(composite_1, SWT.NONE);
		btnAddToCart.setText("Add to Cart");
		
		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.CENTER);
		composite_2.setLayout(new GridLayout(3, false));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		
		Label lblCustomer = new Label(composite_2, SWT.NONE);
		lblCustomer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCustomer.setText("Customer Renting Item:");
		
		whorenting = new Text(composite_2, SWT.BORDER);
		whorenting.setEditable(false);
		whorenting.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		whorenting.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		
		Label lblDesiredNumberOf = new Label(composite_2, SWT.NONE);
		lblDesiredNumberOf.setText("Desired Number of Days:");
		
		this.spinner = new Spinner(composite_2, SWT.BORDER);
		spinner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				computecost();
			}
		});
		spinner.setMaximum(10);
		spinner.setMinimum(1);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		
		Label lblPricePerDay = new Label(composite_2, SWT.NONE);
		lblPricePerDay.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPricePerDay.setText("Price Per Day:");
		
		ppd = new Text(composite_2, SWT.BORDER);
		ppd.setEditable(false);
		ppd.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		ppd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		
		txtRemindOurGuests = new Text(composite_2, SWT.BORDER | SWT.WRAP | SWT.CENTER);
		txtRemindOurGuests.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		txtRemindOurGuests.setText("Remind our guests that all items not returned within the alloted rental period will incur additional charges and all items 10 days late will then incur a replacement charge in addition to the rental charges");
		txtRemindOurGuests.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 3));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		
		Label lblCost = new Label(composite_2, SWT.NONE);
		lblCost.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCost.setText("Cost:");
		
		txtcost = new Text(composite_2, SWT.BORDER);
		txtcost.setEditable(false);
		txtcost.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		txtcost.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		
		Button btnCompute = new Button(composite_2, SWT.NONE);
		btnCompute.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				computecost();
			}
		});
		btnCompute.setText("compute");
		setvalues();

	}
	private void cancelclicked(){
		shell.dispose();
	}
	private void setvalues(){
		whorenting.setText(custname); 
		try {
			pprod = BusinessObjectDAO.getInstance().searchForBO("PProduct", new SearchCriteria("id", p.getId()));
			Itemid.setText(pprod.getName());
			available.setText(pprod.getStatus());
			CProduct tempcprod = BusinessObjectDAO.getInstance().searchForBO("CProduct", new SearchCriteria("id", pprod.getCprodid()));
			ConceptualRental crental = BusinessObjectDAO.getInstance().searchForBO("ConceptualRental", new SearchCriteria("id", tempcprod.getId()));
			if (crental != null){
				computecost();
			//	txtcost.setText(cost +"");
			//ppd.setText(CostperDay + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void computecost() {
		try {
			pprod = BusinessObjectDAO.getInstance().searchForBO("PProduct", new SearchCriteria("id", p.getId()));
			Itemid.setText(pprod.getName());
			available.setText(pprod.getStatus());
			CProduct tempcprod = BusinessObjectDAO.getInstance().searchForBO("CProduct", new SearchCriteria("id", pprod.getCprodid()));
			ConceptualRental crental = BusinessObjectDAO.getInstance().searchForBO("ConceptualRental", new SearchCriteria("id", tempcprod.getId()));
			if (crental != null){
				Double CostperDay = crental.getPricePerDay();
				Double dayswanted = (double)spinner.getSelection();
				Double cost = CostperDay * dayswanted;
				txtcost.setText(cost +"");
			ppd.setText(CostperDay + "");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}



