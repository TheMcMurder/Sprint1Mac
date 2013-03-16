package edu.byu.isys414.jmcmurdi.IntexII;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowLayout;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.GridData;
//import org.eclipse.core.databinding.DataBindingContext;
//import org.eclipse.core.databinding.observable.value.IObservableValue;
//import org.eclipse.jface.databinding.swt.WidgetProperties;
//import org.eclipse.core.databinding.beans.PojoProperties;
//import org.eclipse.core.databinding.observable.Realm;
//import org.eclipse.jface.databinding.swt.SWTObservables;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.core.databinding.DataBindingContext;
//import org.eclipse.core.databinding.observable.value.IObservableValue;
//import org.eclipse.core.databinding.beans.PojoProperties;
//import org.eclipse.core.databinding.observable.Realm;
//import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;

import com.ibm.icu.text.DecimalFormat;

public class POSview {
	// //private DataBindingContext m_bindingContext;
	// for comparing dates
	SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

	/** The singleton instance of the class */
	private static POSview instance = null;

	/** Creates a new instance of POSVIEW */
	private POSview() {
	}

	/** Returns the singelton instance of the EmployeeDAO */
	public static synchronized POSview getInstance() {
		if (instance == null) {
			instance = new POSview();
		}// if
		return instance;
	}// Instance

	protected Shell shlPosView;
	private Text EmpName;
	private Text CustomerName;
	private Button btnItemLookup;
	private Composite EmpControls;
	private Composite CartInfo;
	private Composite PosViewMain;
	public String username;
	public String password;
	private Text Total;
	private Text Tax;
	private Text Subtotal;
	private Employee emp;
	private Customer cust;
	private String searchParameter;
	private String searchValue;
	private Menu menu;
	private Transaction trans;
	private CProduct cprod;
	private PProduct pprod;
	private Sale sale;
	private int itemquantity = 0;
	public Store store = null;
	private Table table;
	private double totalcosttocust = 0;
	private ArrayList<RevSource> salelist = null;
	private Commission comm;
	private Rental therental;

	// private Table table;
	private TableViewer prodTable;
	private TableViewerColumn tcProduct;
	private TableViewerColumn tcQuantity;
	private TableViewerColumn tcPrice;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		try {
			POSview window = new POSview();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
		// public void run() {
		// Display display = Display.getDefault();
		//
		// try {
		// POSview window = new POSview();
		// window.open();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// });
		// });
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPosView.open();
		shlPosView.layout();
		// Starting Loginopen function
		disablemenu();
		loginopen();

		while (!shlPosView.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void testsetEmployee() {
		try {
			this.emp = BusinessObjectDAO.getInstance().searchForBO("Employee", new SearchCriteria("username", "jmcmurdi"));
			this.trans = BusinessObjectDAO.getInstance().create("Transaction");
			this.sale = BusinessObjectDAO.getInstance().create("Sale");
			this.store = BusinessObjectDAO.getInstance().searchForBO("Store", new SearchCriteria("id", emp.getAssignedStore()));
			trans.setEmpid(emp.getId());
		} catch (DataException e) {

			e.printStackTrace();
		}
	}

	private void setEmployee(String empid) {
		try {
			this.emp = BusinessObjectDAO.getInstance().searchForBO("Employee", new SearchCriteria("username", empid));
			this.trans = BusinessObjectDAO.getInstance().create("Transaction");
			this.sale = BusinessObjectDAO.getInstance().create("Sale");
			this.store = BusinessObjectDAO.getInstance().searchForBO("Store", new SearchCriteria("id", emp.getAssignedStore()));
			trans.setEmpid(emp.getId());
			// System.out.println(emp.getFirstname());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		try {
			CreateDB.main(null);
		} catch (Exception dbe) {
			System.out.println("Db creation error");
		}

		shlPosView = new Shell();
		shlPosView.setTouchEnabled(true);
		shlPosView.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shlPosView.setSize(986, 698);
		shlPosView.setText("POS view");
		shlPosView.setLayout(new FillLayout(SWT.HORIZONTAL));

		menu = new Menu(shlPosView, SWT.BAR);
		shlPosView.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		MenuItem mntmNew = new MenuItem(menu_1, SWT.CASCADE);
		mntmNew.setText("New");

		Menu menu_6 = new Menu(mntmNew);
		mntmNew.setMenu(menu_6);

		MenuItem mntmEmployee = new MenuItem(menu_6, SWT.NONE);
		mntmEmployee.setEnabled(false);
		mntmEmployee.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO add employee
				// addcust();
			}
		});
		mntmEmployee.setText("Employee");

		MenuItem mntmStore = new MenuItem(menu_6, SWT.NONE);
		mntmStore.setEnabled(false);
		mntmStore.setText("Store");

		MenuItem mntmQuit = new MenuItem(menu_1, SWT.NONE);
		mntmQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loginopen();
			}
		});
		mntmQuit.setText("Quit");

		MenuItem mntmEdit = new MenuItem(menu, SWT.CASCADE);
		mntmEdit.setText("Edit");

		Menu menu_2 = new Menu(mntmEdit);
		mntmEdit.setMenu(menu_2);

		MenuItem mntmView = new MenuItem(menu, SWT.CASCADE);
		mntmView.setText("View");

		Menu menu_3 = new Menu(mntmView);
		mntmView.setMenu(menu_3);

		MenuItem mntmManagerview = new MenuItem(menu_3, SWT.NONE);
		mntmManagerview.setEnabled(false);
		mntmManagerview.setText("ManagerView");

		MenuItem mntmTools = new MenuItem(menu, SWT.CASCADE);
		mntmTools.setText("Tools");

		Menu menu_4 = new Menu(mntmTools);
		mntmTools.setMenu(menu_4);

		MenuItem mntmSearch = new MenuItem(menu_4, SWT.CASCADE);
		mntmSearch.setText("Search");

		Menu menu_7 = new Menu(mntmSearch);
		mntmSearch.setMenu(menu_7);

		MenuItem mntmCustomer = new MenuItem(menu_7, SWT.NONE);
		mntmCustomer.setText("Customer");
		mntmCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				findcustclicked();
			}
		});

		MenuItem mntmEmployee_1 = new MenuItem(menu_7, SWT.NONE);
		mntmEmployee_1.setEnabled(false);
		mntmEmployee_1.setText("Employee");

		MenuItem mntmProduct = new MenuItem(menu_7, SWT.NONE);
		mntmProduct.setText("Product");

		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");

		Menu menu_5 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_5);

		MenuItem mntmSaleView = new MenuItem(menu_5, SWT.NONE);
		mntmSaleView.setText("Sale View");

		MenuItem mntmManagerView = new MenuItem(menu_5, SWT.NONE);
		mntmManagerView.setEnabled(false);
		mntmManagerView.setText("Manager View");

		this.PosViewMain = new Composite(shlPosView, SWT.NONE);
		PosViewMain.setLayout(new FillLayout(SWT.HORIZONTAL));

		this.EmpControls = new Composite(PosViewMain, SWT.NONE);
		EmpControls.setLayout(new RowLayout(SWT.HORIZONTAL));

		Composite composite_2 = new Composite(EmpControls, SWT.NONE);
		composite_2.setLayout(null);
		composite_2.setLayoutData(new RowData(480, 40));

		Composite composite_14 = new Composite(EmpControls, SWT.NONE);
		composite_14.setLayoutData(new RowData(26, 113));

		Composite composite_3 = new Composite(EmpControls, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_3.setLayoutData(new RowData(422, 114));

		Button btnNewCust = new Button(composite_3, SWT.NONE);
		btnNewCust.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addcust();

			}

		});
		btnNewCust.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		btnNewCust.setText("New Customer");

		Composite composite_10 = new Composite(composite_3, SWT.NONE);

		Button btnFindCust = new Button(composite_3, SWT.NONE);
		btnFindCust.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				findcustclicked();
			}

		});
		btnFindCust.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		btnFindCust.setText("Find Customer");

		Composite composite_15 = new Composite(EmpControls, SWT.NONE);
		composite_15.setLayoutData(new RowData(28, 114));

		Composite composite_4 = new Composite(EmpControls, SWT.NONE);
		composite_4.setLayout(null);
		composite_4.setLayoutData(new RowData(481, 41));

		Composite composite_17 = new Composite(EmpControls, SWT.NONE);
		composite_17.setLayoutData(new RowData(26, 121));

		Composite composite_5 = new Composite(EmpControls, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_5.setLayoutData(new RowData(421, 121));

		btnItemLookup = new Button(composite_5, SWT.NONE);
		btnItemLookup.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lookupProduct();
			}
		});
		btnItemLookup.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		btnItemLookup.setText("Item Lookup");

		Composite composite_11 = new Composite(composite_5, SWT.NONE);

		Button btnEditItem = new Button(composite_5, SWT.NONE);
		btnEditItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editItemclicked();
			}
		});
		btnEditItem.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		btnEditItem.setText("Edit Item");

		Composite composite_16 = new Composite(EmpControls, SWT.NONE);
		composite_16.setLayoutData(new RowData(23, 118));

		Composite composite_7 = new Composite(EmpControls, SWT.NONE);
		composite_7.setLayoutData(new RowData(478, 43));
		composite_7.setLayout(null);

		Composite composite_18 = new Composite(EmpControls, SWT.NONE);
		composite_18.setLayoutData(new RowData(23, 114));

		Composite composite_8 = new Composite(EmpControls, SWT.NONE);
		composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_8.setLayoutData(new RowData(425, 110));

		Button btnEditCust = new Button(composite_8, SWT.NONE);
		btnEditCust.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		btnEditCust.setText("Edit Customer");

		Composite composite_13 = new Composite(composite_8, SWT.NONE);

		Button btnCheckout = new Button(composite_8, SWT.NONE);
		btnCheckout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkoutclicked();
			}
		});
		btnCheckout.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		btnCheckout.setText("Checkout");

		Composite composite_19 = new Composite(EmpControls, SWT.NONE);
		composite_19.setLayoutData(new RowData(21, 109));

		Composite composite_6 = new Composite(EmpControls, SWT.NONE);
		composite_6.setLayout(null);
		composite_6.setLayoutData(new RowData(477, 56));

		Composite composite_20 = new Composite(EmpControls, SWT.NONE);
		composite_20.setLayoutData(new RowData(19, SWT.DEFAULT));

		Composite composite_9 = new Composite(EmpControls, SWT.NONE);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_9.setLayoutData(new RowData(431, 75));

		Button btnLogout = new Button(composite_9, SWT.NONE);
		btnLogout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				logoutclicked();
			}

		});
		// btnLogout.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnLogout.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		btnLogout.setText("Logout");

		Composite composite_12 = new Composite(composite_9, SWT.NONE);

		Button btnManLogin = new Button(composite_9, SWT.NONE);
		btnManLogin.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		btnManLogin.setText("Manager Login");

		Composite composite_21 = new Composite(EmpControls, SWT.NONE);
		composite_21.setLayoutData(new RowData(22, SWT.DEFAULT));

		this.CartInfo = new Composite(PosViewMain, SWT.NONE);
		CartInfo.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(CartInfo, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new GridLayout(3, false));

		Label lblTeamMember = new Label(composite, SWT.NONE);
		lblTeamMember.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblTeamMember.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));
		lblTeamMember.setText("Team Member: ");

		EmpName = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		EmpName.setEditable(false);
		EmpName.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		GridData gd_EmpName = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_EmpName.widthHint = 320;
		EmpName.setLayoutData(gd_EmpName);
		EmpName.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));
		lblNewLabel.setText("Welcome");
		new Label(composite, SWT.NONE);

		CustomerName = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		CustomerName.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		CustomerName.setEditable(false);
		GridData gd_CustomerName = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_CustomerName.widthHint = 321;
		CustomerName.setLayoutData(gd_CustomerName);
		CustomerName.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));

		Composite composite_1 = new Composite(CartInfo, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new GridLayout(3, false));

		Label lblSubtotal = new Label(composite_1, SWT.NONE);
		lblSubtotal.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblSubtotal.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSubtotal.setAlignment(SWT.CENTER);
		GridData gd_lblSubtotal = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_lblSubtotal.widthHint = 264;
		lblSubtotal.setLayoutData(gd_lblSubtotal);
		lblSubtotal.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));
		lblSubtotal.setText("SubTotal");

		Subtotal = new Text(composite_1, SWT.BORDER);
		Subtotal.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		Subtotal.setEditable(false);
		GridData gd_Subtotal = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_Subtotal.heightHint = 34;
		Subtotal.setLayoutData(gd_Subtotal);

		Label lblTax = new Label(composite_1, SWT.NONE);
		lblTax.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTax.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblTax.setAlignment(SWT.CENTER);
		GridData gd_lblTax = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblTax.widthHint = 270;
		lblTax.setLayoutData(gd_lblTax);
		lblTax.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));
		lblTax.setText("Tax");
		new Label(composite_1, SWT.NONE);

		Tax = new Text(composite_1, SWT.BORDER);
		Tax.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		Tax.setEditable(false);
		GridData gd_Tax = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_Tax.heightHint = 32;
		Tax.setLayoutData(gd_Tax);

		Label lblTotal = new Label(composite_1, SWT.NONE);
		lblTotal.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTotal.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		GridData gd_lblTotal = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblTotal.widthHint = 270;
		lblTotal.setLayoutData(gd_lblTotal);
		lblTotal.setAlignment(SWT.CENTER);
		lblTotal.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));
		lblTotal.setText("Total");
		new Label(composite_1, SWT.NONE);

		Total = new Text(composite_1, SWT.BORDER);
		Total.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		Total.setEditable(false);
		GridData gd_Total = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_Total.heightHint = 32;
		Total.setLayoutData(gd_Total);

		this.prodTable = new TableViewer(CartInfo, SWT.BORDER | SWT.FULL_SELECTION);
		table = prodTable.getTable();
		table.setHeaderVisible(true);
		table.setLayoutData(BorderLayout.CENTER);

		this.tcProduct = new TableViewerColumn(prodTable, SWT.NONE);
		TableColumn tblclmnName = tcProduct.getColumn();
		tblclmnName.setWidth(216);
		tblclmnName.setText("Name");

		this.tcQuantity = new TableViewerColumn(prodTable, SWT.NONE);
		TableColumn tblclmnNumber = tcQuantity.getColumn();
		tblclmnNumber.setWidth(171);
		tblclmnNumber.setText("Number");

		this.tcPrice = new TableViewerColumn(prodTable, SWT.NONE);
		TableColumn tblclmnPrice = tcPrice.getColumn();
		tblclmnPrice.setWidth(100);
		tblclmnPrice.setText("Price");

		tcProduct.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element){
				RevSource rs1 = (RevSource) element;
				if (rs1.getRevtype().equals("Sale")){
					Sale sale1 = (Sale) element;
					Product prod1;
					try {
						prod1 = BusinessObjectDAO.getInstance().read(sale1.getProdid());
						return prod1.getName();
					} catch (DataException e) {
						e.printStackTrace();
					}
				}else if(rs1.getRevtype().equals("Rental")){
					Rental rent1 = (Rental) element;
					Product prod1;
					try {
						prod1 = BusinessObjectDAO.getInstance().read(rent1.getForRentid());
						return prod1.getName();
					} catch (DataException e) {
						e.printStackTrace();
					}
				}
				return null;
			}	
		});

		tcQuantity.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element){
				RevSource rs1 = (RevSource) element;
				if (rs1.getRevtype().equals("Sale")){
					Sale sale1 = (Sale) element;
					DecimalFormat df = new DecimalFormat("###");
					return df.format(sale1.getQuantity())+"";
				}else if(rs1.getRevtype().equals("Rental")){
					Rental rent1 = (Rental) element;
					DecimalFormat df = new DecimalFormat("###");
					return df.format(rent1.getNumDays())+"";
				}
				return "error";
//				
//				Sale sale1 = (Sale) element;
//				DecimalFormat df = new DecimalFormat("###");
//				return df.format(sale1.getQuantity())+"";
			}	
		});
		
		tcPrice.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element){
				
				RevSource rs1 = (RevSource) element;
				if (rs1.getRevtype().equals("Sale")){
					Sale sale1 = (Sale) element;
					DecimalFormat df = new DecimalFormat("###.##");
					df.setMinimumFractionDigits(2);
					return df.format(sale1.getChargeamount())+"";
				}else if(rs1.getRevtype().equals("Rental")){
					Rental rent1 = (Rental) element;
					DecimalFormat df = new DecimalFormat("###.##");
					df.setMinimumFractionDigits(2);
					return df.format(rent1.getChargeamount())+"";
				}
				return "error";
				
				
//				Sale sale1 = (Sale) element;
//				DecimalFormat df = new DecimalFormat("###.##");
//				df.setMinimumFractionDigits(2);
//				return df.format(sale1.getChargeamt())+"";
			}
		});
		prodTable.setContentProvider(new ArrayContentProvider());

		// m_bindingContext = initDataBindings();

	}
	
	/**
	 * method run when the edit item button is clicked
	 */
	private void editItemclicked(){
		
		
		//This is the code for the rental prompt and doesn't really belong here.
		Product p = null;
		try {
			p = BusinessObjectDAO.getInstance().searchForBO("Product", new SearchCriteria("id", "prod4"));
		} catch (DataException e) {
			e.printStackTrace();
		}
		if (cust != null){
		String custstring = cust.getFirstName() +  " " +cust.getLastName();
		checkoutrental(p, custstring);
		
		//temprental =  
		
		}
		//end doesn't belong code
	}
	/**
	 * Opens the checkout window and allows for adding a rental object to the transaction
	 */
	private void checkoutrental(Product p, String custstring){
		RentalPrompt rentprompt = new RentalPrompt(shlPosView, 0, p, custstring);
		rentprompt.open();
		Rental temprental = null;

		temprental = rentprompt.getRental();
		if(temprental != null){
//			System.out.println("Rental testing (POSView): ");
//			System.out.println("Rental Id: " + temprental.getId());
//			System.out.println("Rental Date Out: " + temprental.getDateOut());
//			System.out.println("Rental Date Due: " + temprental.getDateDue());
//			System.out.println("Rental Date in: " + temprental.getDateIn());
//			System.out.println("Reminder Sent: " + temprental.isReminderSent());
//			System.out.println("Workorder #: " + temprental.getWorkOrderNum());
//			System.out.println("ForRentID: " + temprental.getForRentid());
//			//salelist.add(rental);
			
			
			//Add it here
			temprental.setRevtype("Rental");
			temprental.setChargeamount(rentprompt.getCost());
			trans.addRevSource(temprental);
			
			trans.setStoreid(store.getId());
			Date today = new Date();

			String now = SDF.format(today);
			try {
				today = (SDF.parse(now));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			trans.setTransdate(today);
			// trans.setRevid(//revid)
			additemtolist();
			refreshpricevalues();
			//end add
			
		}
		
	}
	
	/**
	 * Opens the add Customer Dialog and field checks
	 */
	private void addcust() {
		AddCust addcust = new AddCust(shlPosView, 0);
		addcust.open();
		cust = addcust.getCust();

		populateCust();
	}

	/**
	 * Finds the customer from the database (or cache) and sets the customer object
	 */
	private void findcustclicked() {
		FindCust findcust = new FindCust(shlPosView, 0);
		findcust.open();

		cust = findcust.getCustomer();
		populateCust();
		// this.emp = BusinessObjectDAO.getInstance().searchForBO("Employee",new
		// SearchCriteria("username", empid));
		// BusinessObjectDAO.getInstance().searchForBO("Customer", new
		// SearchCriteria(searchParameter, searchValue));
	}

	/**
	 * This method populates the customer info in the GUI
	 */
	private void populateCust() {

		if (cust != null) {
			CustomerName.setText(cust.getFirstName() + " " + cust.getLastName());
			trans.setCustomerid(cust.getId());
		} else {
			errormessage("Error finding Customer Object");
		}
	}

	private void checkoutclicked() {
		Checkout checkout = new Checkout(shlPosView, 0, totalcosttocust);
		checkout.open();
		Boolean condition = checkout.isSuccessful();
		if (condition) {

			Date today = new Date();

			String now = SDF.format(today);
			try {
				today = SDF.parse(now);

			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			try {
				// System.out.println("DLSKJFLSDJFSLDJ");
				comm = BusinessObjectDAO.getInstance().create("Commission");
				comm.setComdate(today);
				comm.setEmpid(emp.getId());
				comm.setAmount(trans.getComtotal());
				comm.save();

			} catch (DataException e2) {
				e2.printStackTrace();
			}
			try {
				JournalEntry je = BusinessObjectDAO.getInstance().create("JournalEntry");
				je.setTransdate(today);

				je.save();
				for (int i = 0; i < 6; i++) {
					DebitCredit dc = BusinessObjectDAO.getInstance().create("DebitCredit");
					if (i == 0) {
						dc.setAmount(trans.getTotal());
						dc.setGlName("Cash");
						dc.setIsDebit(true);
						dc.setJournalEntryid(je.getId());
						dc.save();
					} else if (i == 1) {
						dc.setAmount(trans.getSubtotal());
						dc.setGlName("Sales Revenue");
						dc.setIsDebit(false);
						dc.setJournalEntryid(je.getId());
						dc.save();
					} else if (i == 2) {
						dc.setAmount(trans.getTax());
						dc.setGlName("Sales Tax");
						dc.setIsDebit(false);
						dc.setJournalEntryid(je.getId());
						dc.save();
					} else if (i == 3) {
						dc.setAmount(trans.getComtotal());
						dc.setGlName("Commission Expense");
						dc.setIsDebit(true);
						dc.setJournalEntryid(je.getId());
						dc.save();
					} else if (i == 4) {
						dc.setAmount(trans.getComtotal());
						dc.setGlName("Commission Payable");
						dc.setIsDebit(false);
						dc.setJournalEntryid(je.getId());
						dc.save();
					} else if (i == 5) {
						dc.setAmount(trans.getSubtotal());
						dc.setGlName("Inventory");
						dc.setIsDebit((false));
						dc.setJournalEntryid(je.getId());
						dc.save();
					}
					// TODO

					// System.out.println(now);
					try {
						today = SDF.parse(now);
						je.setTransdate(today);
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}
				for (RevSource rs : trans.getRevsources()) {
					rs.setTransid(trans.getId());
					rs.save();
				}
				Payment p = BusinessObjectDAO.getInstance().create("Payment");
				p.setPayamount(checkout.getAmountpaid());
				p.setPaychange(checkout.getChangeamount());
				p.setType("cash");
				p.save();
				trans.setPaymentid(p.getId());
				trans.setJournalentryid(je.getId());
				//System.out.println(comm.getId().length());
				trans.setCommid(comm.getId());
				//System.out.println(trans.getCommid().length());
				trans.save();

			} catch (DataException e) {
				e.printStackTrace();
			}
		}

	}

	private void lookupProduct() {
		FindProd findprod = new FindProd(shlPosView, 0);

		findprod.open();
		this.pprod = findprod.getPprodcut();
		this.cprod = findprod.getCproduct();
		// System.out.println("PPROD: " + pprod);
		// System.out.println("CPROD: " + cprod);
		this.itemquantity = findprod.getAmountfromspinner();
		// System.out.println("Itemq: " + itemquantity);
		if (pprod != null) {
			try {
				//RUN HERE IT"S TOTALLY SAFE
				sale = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

			// trans.addRevSource();
		} else if (cprod != null) {
			// trans.addRevSource();
			// System.out.println("CPROD!!!!!");
			Sale tempsale;
			try {
				tempsale = BusinessObjectDAO.getInstance().create("Sale");

				tempsale.setProdid(cprod.getId());
				tempsale.setQuantity(itemquantity);
				tempsale.setChargeamount(cprod.getProdPrice() * itemquantity);
				this.sale.setStoreid(store.getId());
				tempsale.setRevtype("Sale");
				tempsale.setStoreid(store.getId());
				trans.addRevSource(tempsale);
				trans.setComtotal(cprod.getCprodComRate() * cprod.getProdPrice() * itemquantity);
				trans.setStoreid(store.getId());
				Date today = new Date();

				String now = SDF.format(today);
				try {
					today = (SDF.parse(now));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				trans.setTransdate(today);
				// trans.setRevid(//revid)
				additemtolist();
				refreshpricevalues();
			} catch (DataException e) {
				e.printStackTrace();
			}
			// this.sale.set
		}
		pprod = null;
		cprod = null;
	}

	/**
	 * Function that actually disables the UI
	 */
	private void disableall() {
		PosViewMain.setVisible(false);
		PosViewMain.setEnabled(false);
	}

	/**
	 * function to re-enable the UI
	 */
	public void enableall() {
		PosViewMain.setVisible(true);
		PosViewMain.setEnabled(true);
	}

	/**
	 * Default Error Message for the application
	 * 
	 * @param error
	 */
	private void errormessage(String error) {
		String error_message = error;
		ErrorMessage2 em = new ErrorMessage2(shlPosView, error_message, 0);
		em.open();
	}

	/**
	 * reruns the loginopen function. Clears employee first.
	 */
	private void logoutclicked() {

		loginopen();
	}

	/**
	 * This method will disable the whole UI and open a login window. This way the data is still loading and creating objects while the user is logging in.
	 */
	private void loginopen() {
		// making all composites invisible to allow for login credentials
		disableall();
		LoginDialog logwin = new LoginDialog(shlPosView, 0);
		emp = null;
		cust = null;
		CustomerName.setText("");

		// delete this when you go live
		// //
		// testsetEmployee();
		// enableall();
		// EmpName.setText(emp.getFirstname() + " " + emp.getLastname());
		// trans.setEmpid(emp.getId());

		// uncomment this when you go live
		logwin.open();
		boolean tempbool = logwin.isSuccessful();
		String empid = logwin.getUsername();
		if (tempbool) {
			enableall();

			setEmployee(empid);
			// System.out.println("");
			EmpName.setText(emp.getFirstname() + " " + emp.getLastname());
			enablemenu();
		} else {
			shlPosView.dispose();
		}
	}

	private void refreshpricevalues() {
		/*
		 * Text Total; private Text Tax; private Text Subtotal;
		 */
		Total.setText("");
		Tax.setText("");
		Subtotal.setText("");

		double subtotalprice = 0;
		double taxprice = 0;
		double totalprice = 0;

		for (RevSource r : this.trans.getRevsources()) {
			subtotalprice += r.getChargeamount();

		}
		taxprice = store.getSalestaxrate() * subtotalprice;
		totalprice = subtotalprice + taxprice;
		totalcosttocust = totalprice;
		Total.setText(totalprice + "");
		Tax.setText(taxprice + "");
		Subtotal.setText(subtotalprice + "");
		trans.setTotal(totalprice);
		trans.setTax(taxprice);
		trans.setSubtotal(subtotalprice);
	}

	/**
	 * adds item to sale list on window
	 */
	private void additemtolist() {
		prodTable.setInput(trans.getRevsources());
		// System.out.println(trans.getComtotal());
	}

	private void enablemenu() {
		menu.setEnabled(true);
		menu.setVisible(true);
	}

	private void disablemenu() {
		menu.setEnabled(false);
		menu.setVisible(false);
	}

	private void enablemanagermenu() {

	}

	private void disablemanagermenu() {

	}
}
