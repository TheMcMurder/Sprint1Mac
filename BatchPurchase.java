package edu.byu.isys414.jmcmurdi.IntexII;

import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;

public class BatchPurchase extends Dialog {

	protected Object result;
	protected Shell shell;
	private Button btnsend;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public BatchPurchase(Shell parent, int style) {
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
		btnsend.setText("Sell Products");

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
		lblNewLabel.setText("This window will charge every customer who hasn't turned in their rental items and the item is 10 days past the due date.");

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
			int diffInDays = (int)( (today.getTime() - temprental.getDateDue().getTime()) / (1000 * 60 * 60 * 24) );
			System.out.println("Rental " + temprental.getId() + " Diff " + diffInDays);
			//System.out.println("LATE by " + diffInDays);
			//System.out.println("Rental");
			if (diffInDays >= 10) {
				System.out.println("More than 10 days");
				

				if (temprental.isReminderSent() == true) {
					System.out.println("Reminder Already sent");
					
					if (temprental.getDateIn() == null) {
						System.out.println("hasn't been turned in");
						try {
							Transaction t = BusinessObjectDAO.getInstance().searchForBO("Transaction", new SearchCriteria("id", temprental.getTransid()));
							// System.out.println(t.getCustomerid());
							Customer c = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("id", t.getCustomerid()));
							PProduct p = BusinessObjectDAO.getInstance().searchForBO("PProduct", new SearchCriteria("id", temprental.getForRentid()));
							Store s = BusinessObjectDAO.getInstance().searchForBO("Store", new SearchCriteria("id", p.getStoreid()));
							ConceptualRental cr = BusinessObjectDAO.getInstance().searchForBO("ConceptualRental", new SearchCriteria("id", p.getCprodid()));
							String custEmail = c.getEmail();
							String message = "This email is to inform you that you haven't returned the item you rented from mystuffsonline.com - a myer Photography company. " +
									" <br>It has been more than 10 days and you have been charged the replacement cost of the product";
							String subject = "Overdue Rental";
							String from = "DoNotReply@mystuffsonline.com";
							String fromname = "Robot";
							
							BatchEmail.send(from, fromname, custEmail, subject, message);
							temprental.setDateIn(today);
							p.setStatus("Sold");
							p.save();
							temprental.save();
							
							//Transaction here
							
							Transaction trans = BusinessObjectDAO.getInstance().create("Transaction");
							Sale sale = BusinessObjectDAO.getInstance().create("Sale");
							sale.setChargeamount(cr.getReplacementPrice());
							sale.setProdid(p.getId());
							sale.setQuantity(1);
							sale.setRevtype("Sale");
							sale.setStoreid(p.getStoreid());
							sale.setTransid(trans.getId());
							sale.save();
							Payment pay = BusinessObjectDAO.getInstance().create("Payment");
							pay.setPayamount(cr.getReplacementPrice() + (cr.getReplacementPrice()*s.getSalestaxrate()));
							pay.setPaychange(0);
							pay.setType("Sale");
							pay.save();
							
							//Commission
							//JournalEntry
							JournalEntry je = BusinessObjectDAO.getInstance().create("JournalEntry");
							je.setTransdate(today);

							trans.setPaymentid(pay.getId());
							trans.setCustomerid(c.getId());
							
							trans.setSubtotal(cr.getReplacementPrice());
							trans.setTax(cr.getReplacementPrice()*s.getSalestaxrate());
							trans.setTotal(pay.getPayamount());
							trans.setTransdate(today);
							
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
									double salestotal = 0;
									double renttotal = 0;

									for (RevSource rs3 : trans.getRevsources()) {
										if (rs3.getRevtype().equals("Sale")) {
											salestotal += rs3.getChargeamount();
										} else {
											renttotal += rs3.getChargeamount();
										}
									}
									if (salestotal > 0) {
										dc.setAmount(salestotal);
										dc.setGlName("Sales Revenue");
										dc.setIsDebit(false);
										dc.setJournalEntryid(je.getId());
										dc.save();
									}
									if (renttotal > 0) {
										DebitCredit dc1 = BusinessObjectDAO.getInstance().create("DebitCredit");
										dc1.setAmount(salestotal);
										dc1.setGlName("Rental Revenue");
										dc1.setIsDebit(false);
										dc1.setJournalEntryid(je.getId());
										dc1.save();
									}

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
							}
							trans.setJournalentryid(je.getId());
							trans.save();
							
							

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
