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
import org.eclipse.swt.widgets.Spinner;


public class FindProd extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Combo criteriaCombo;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	public ArrayList <CProduct> resultsList = null;
	public ArrayList <PProduct> presultsList = null;
	public TableViewerColumn tcName;
	public TableViewerColumn tcPhone;
	private Combo ResultCombo;
	private int selectionindex;
	private Product product;
	private String table;
	private CProduct cproduct;
	private PProduct pprodcut;
	private Spinner spinner;
	private int amountfromspinner;
	private boolean cprod = false;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FindProd(Shell parent, int style) {
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
		
		criteriaCombo = new Combo(composite_1, SWT.READ_ONLY);
		criteriaCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		criteriaCombo.select(0);
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
				//testingsearch();
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
		lblResults.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblResults.setText("Results:");
		lblResults.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		lblResults.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblResults, true, true);
		
		ResultCombo = new Combo(composite_1, SWT.NONE);
		ResultCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(ResultCombo);
		formToolkit.paintBordersFor(ResultCombo);
		new Label(composite_1, SWT.NONE);
		
		Label lblAmount = new Label(composite_1, SWT.RIGHT);
		lblAmount.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblAmount.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAmount.setAlignment(SWT.RIGHT);
		lblAmount.setText("Amount:");
		lblAmount.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		formToolkit.adapt(lblAmount, true, true);
		
		spinner = new Spinner(composite_1, SWT.BORDER);
		formToolkit.adapt(spinner);
		formToolkit.paintBordersFor(spinner);

	}
//	private void testingsearch(){
//		
//		try {
//			CProduct tempcprod = BusinessObjectDAO.getInstance().searchForBO("CProduct", new SearchCriteria("id", "prod1"));
//			System.out.println("Success");
//			System.out.println("TempCProd values: " + tempcprod.getId());
//		} catch (DataException e) {
//			
//			e.printStackTrace();
//		}
//		
//		
//	}


	private void runSearch() {
		ArrayList <CProduct> tempList2 = new ArrayList<CProduct>();
		ArrayList <PProduct> tempList3 = new ArrayList<PProduct>();
		resultsList = new ArrayList<CProduct>();
		int comboSelection = criteriaCombo.getSelectionIndex();
		String searchfor = text.getText();
		String columnName = null;
		if(comboSelection == 0){
			columnName = "prodnum";
			table = "product";
		}
		else if(comboSelection == 1){
			columnName = "serialnum";
			table = "pproduct";
		}
		//new SearchCriteria(searchParameter, searchValue
		try {
			java.util.List<BusinessObject>tempList = BusinessObjectDAO.getInstance().searchForList(table, new SearchCriteria(columnName, searchfor));
			if(tempList != null){
				for (int i = 0; i < tempList.size(); i++){
					//System.out.println("ProdList.size = " + tempList.size());
					Product tempprod = (Product)tempList.get(i);
					String prodid = tempprod.getId();
					//BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria(searchParameter, searchValue));
					if(comboSelection == 0){
						CProduct tempcprod = BusinessObjectDAO.getInstance().searchForBO("CProduct", new SearchCriteria("id", tempprod.getId()));
						cprod = true;
						tempList2.add(tempcprod);
					}else{
						PProduct temppprod = BusinessObjectDAO.getInstance().searchForBO("PProduct", new SearchCriteria("id", tempprod.getId()));
						System.out.println(temppprod.getId());
						cprod = false;
						tempList3.add(temppprod);
					}
					//System.out.println("CProduct test: " + tempcprod.getProdName());
					
				}
//				for (int i = 0; i < tempList.size(); i++){
//					CProduct tempprod = (CProduct)tempList.get(i);
//					tempList2.add(tempprod);
//					//resultsList.add(tempcust);
//				}
				//System.out.println(tempList2.size());
				int i = 0;
				if (comboSelection == 0) {
					for (CProduct p : tempList2) {
						// System.out.println(c.getFirstName() + " " +
						// c.getLastName());
						resultsList.add(p);
						ResultCombo.add(p.getProdName() + "");
						ResultCombo.setData(p.getProdnum() + "",
								resultsList.get(i));
						// ResultCombo.setd
						// System.out.println(resultsList.get(i).getFirstName());
						i++;

						// ResultCombo.setData(c.getFirstName() + " " +
						// c.getLastName() + " ", c);
					}
				}else{
					for(PProduct p : tempList3){
						presultsList.add(p);
						ResultCombo.add(p.getSerialnum() + "");
						ResultCombo.setData(p.getProdnum() + "",
								presultsList.get(i));
						// ResultCombo.setd
						// System.out.println(resultsList.get(i).getFirstName());
						i++;
					}
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
		product = resultsList.get(selectionindex);
		if (cprod){
			cproduct = resultsList.get(selectionindex);
		}
		else {
			System.out.println("error");
			pprodcut = presultsList.get(selectionindex);
			//pproduct = resultsList.get(selectionindex);
		}
		amountfromspinner = getSpinner();
		//System.out.println("Class Product: " + product.getId());
		exitpopup();
	}
	private void PopulateCombo(){
		criteriaCombo.add("Product Number", 0);
		//criteriaCombo.add("Serial Number", 1);
		criteriaCombo.select(0);
	}
	private void exitpopup(){
		shell.dispose();
	}

	/**
	 * @return the cproduct
	 */
	public CProduct getCproduct() {
		//System.out.println("find prod cprod: " + cproduct);
		return cproduct;
	}

	/**
	 * @param cproduct the cproduct to set
	 */
	public void setCproduct(CProduct cproduct) {
		this.cproduct = cproduct;
	}

	/**
	 * @return the pprodcut
	 */
	public PProduct getPprodcut() {
		//System.out.println("find prod pprod: " + pprodcut);
		return pprodcut;
	}

	/**
	 * @param pprodcut the pprodcut to set
	 */
	public void setPprodcut(PProduct pprodcut) {
		this.pprodcut = pprodcut;
	}

	/**
	 * @return the spinner
	 */
	public int getSpinner() {
		return spinner.getSelection();
	}

	/**
	 * @param spinner the spinner to set
	 */
	public void setSpinner(Spinner spinner) {
		this.spinner = spinner;
	}

	/**
	 * @return the amountfromspinner
	 */
	public int getAmountfromspinner() {
		return amountfromspinner;
	}

	/**
	 * @param amountfromspinner the amountfromspinner to set
	 */
	public void setAmountfromspinner(int amountfromspinner) {
		this.amountfromspinner = amountfromspinner;
	}



}
