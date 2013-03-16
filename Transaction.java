package edu.byu.isys414.jmcmurdi.IntexII;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

/**
 * A Transaction object
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class Transaction extends BusinessObject {



	
	@BusinessObjectField
	private String paymentid = null;
	@BusinessObjectField
	private String journalentryid = null;	
	@BusinessObjectField
	private String customerid = null;
	@BusinessObjectField
	private String commid = null;
	@BusinessObjectField
	private String empid = null;
	@BusinessObjectField
	private String storeid = null;
	@BusinessObjectField
	private Date transdate = null;
	@BusinessObjectField
	private double subtotal = 0;
	@BusinessObjectField
	private double tax = 0;
	@BusinessObjectField
	private double total = 0;
	@BusinessObjectField
	private double comtotal = 0;

	private ArrayList <RevSource>revsources = new ArrayList <RevSource>();


	
	
	/** Creates a new instance of this object */
	public Transaction(String id) {
		super(id);
	}// constructor




	/**
	 * @return the paymentid
	 */
	public String getPaymentid() {
		return paymentid;
	}




	/**
	 * @param paymentid the paymentid to set
	 */
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
		setDirty();

	}




	/**
	 * @return the journalentryid
	 */
	public String getJournalentryid() {
		return journalentryid;
	}




	/**
	 * @param journalentryid the journalentryid to set
	 */
	public void setJournalentryid(String journalentryid) {
		this.journalentryid = journalentryid;
		setDirty();
	}




	/**
	 * @return the customerid
	 */
	public String getCustomerid() {
		return customerid;
	}




	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
		setDirty();
	}




	/**
	 * @return the commid
	 */
	public String getCommid() {
		return commid;
	}




	/**
	 * @param commid the commid to set
	 */
	public void setCommid(String commid) {
		this.commid = commid;
		setDirty();
	}

	/**
	 * @return the empid
	 */
	public String getEmpid() {
		return empid;
	}




	/**
	 * @param empid the empid to set
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
		setDirty();
	}




	/**
	 * @return the storeid
	 */
	public String getStoreid() {
		return storeid;
	}




	/**
	 * @param storeid the storeid to set
	 */
	public void setStoreid(String storeid) {
		this.storeid = storeid;
		setDirty();
	}




	/**
	 * @return the transdate
	 */
	public Date getTransdate() {
		return transdate;
	}




	/**
	 * @param transdate the transdate to set
	 */
	public void setTransdate(Date transdate) {
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(transdate);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();

	    this.transdate = realDate;
		setDirty();
	}




	/**
	 * @return the subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}




	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
		setDirty();
	}




	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}




	/**
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
		setDirty();
	}




	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}




	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
		setDirty();
	}




	/**
	 * @return the revsources
	 */
	public ArrayList<RevSource> getRevsources() {
		return revsources;
	}




	/**
	 * @param revsources the revsources to set
	 */
	public void setRevsources(ArrayList<RevSource> revsources) {
		this.revsources = revsources;
	}

	/**
	* @param one revsource to add to list
	*/
	public void addRevSource(RevSource source){
		this.revsources.add(source);
	}




	/**
	 * @return the comtotal
	 */
	public double getComtotal() {
		return comtotal;
	}




	/**
	 * @param comtotal the comtotal to set
	 */
	public void setComtotal(double comtotal) {
		this.comtotal += comtotal;
	}


	


}