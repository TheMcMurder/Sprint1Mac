package edu.byu.isys414.jmcmurdi.IntexII;

import java.util.ArrayList;
import java.util.LinkedList;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class FindCust extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Combo criteriaCombo;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	public ArrayList <Customer> resultsList = null;
	public TableViewerColumn tcName;
	public TableViewerColumn tcPhone;
	private Combo ResultCombo;
	private int selectionindex;
	private Customer customer;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FindCust(Shell parent, int style) {
		super(parent, style);
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
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exitpopup();
			}
		});
		btnCancel.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		btnCancel.setText("Cancel");
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		
		Button btnGo = new Button(composite, SWT.NONE);
		btnGo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pullcustvalue();
			}
		});
		btnGo.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		btnGo.setText("Select");
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new GridLayout(3, false));
		new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		lblNewLabel.setText("Search By:");
		
		criteriaCombo = new Combo(composite_1, SWT.NONE);
		criteriaCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		PopulateCombo();
		
		Label lblSearchFor = new Label(composite_1, SWT.NONE);
		lblSearchFor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSearchFor.setText("Search For:");
		lblSearchFor.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		
		text = new Text(composite_1, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Button btnNewButton = formToolkit.createButton(composite_1, "Search", SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				runSearch();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblResults = new Label(composite_1, SWT.NONE);
		lblResults.setText("Results:");
		lblResults.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		lblResults.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblResults, true, true);
		
		ResultCombo = new Combo(composite_1, SWT.NONE);
		ResultCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(ResultCombo);
		formToolkit.paintBordersFor(ResultCombo);

	}


	private void runSearch() {
		ArrayList <Customer> tempList2 = new ArrayList<Customer>();
		resultsList = new ArrayList<Customer>();
		int comboSelection = criteriaCombo.getSelectionIndex();
		String searchfor = text.getText();
		String columnName = null;
		if(comboSelection == 0){
			columnName = "firstname";
		}
		else if (comboSelection == 1){
			columnName = "lastname";
		}
		else if (comboSelection == 2){
			columnName = "phone";
		}
		//new SearchCriteria(searchParameter, searchValue
		try {
			java.util.List<BusinessObject>tempList = BusinessObjectDAO.getInstance().searchForList("Customer", new SearchCriteria(columnName, searchfor));
			if(tempList != null){
				for (int i = 0; i < tempList.size(); i++){
					Customer tempcust = (Customer)tempList.get(i);
					tempList2.add(tempcust);
					//resultsList.add(tempcust);
				}
				//System.out.println(tempList2.size());
				int i = 0;
				for (Customer c : tempList2){
					//System.out.println(c.getFirstName() + " " + c.getLastName());
					resultsList.add(c);
					ResultCombo.add(c.getFirstName() + " " + c.getLastName());
					ResultCombo.setData(c.getFirstName() + " " + c.getLastName(), resultsList.get(i));
					//ResultCombo.setd
					//System.out.println(resultsList.get(i).getFirstName());
					i++;
					
					//ResultCombo.setData(c.getFirstName() + " " + c.getLastName() + " ", c);
				}
				//initAccountGui(tempList2);
	
				
			
				
			}
		} catch (DataException e) {
			
			e.printStackTrace();
		}
	}
	private void pullcustvalue(){
		//TODO
		selectionindex = ResultCombo.getSelectionIndex();
		customer = resultsList.get(selectionindex);
		//System.out.println("Class Customer object: "+customer.getFirstName());
		exitpopup();
	}
	private void PopulateCombo(){
		criteriaCombo.add("First Name", 0);
		criteriaCombo.add("Last Name", 1);
		criteriaCombo.add("Phone", 2);
	}
	private void exitpopup(){
		shell.dispose();
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
