package edu.byu.isys414.jmcmurdi.IntexII;



/**
 * A Payment
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class Payment extends BusinessObject {


	@BusinessObjectField
	private double payamount = 0;
	@BusinessObjectField
	private double paychange = 0;
	@BusinessObjectField
	private String type = null;

	/** Creates a new instance of this object */
	public Payment(String id) {
		super(id);
	}// constructor

	/**
	 * @return the payamount
	 */
	public double getPayamount() {
		return payamount;
	}

	/**
	 * @param payamount
	 *            the payamount to set
	 */
	public void setPayamount(double payamount) {
		this.payamount = payamount;
		setDirty();
	}

	/**
	 * @return the paychange
	 */
	public double getPaychange() {
		return paychange;
	}

	/**
	 * @param paychange
	 *            the paychange to set
	 */
	public void setPaychange(double paychange) {
		this.paychange = paychange;
		setDirty();
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
		setDirty();
	}

}