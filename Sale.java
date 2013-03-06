package edu.byu.isys414.jmcmurdi.IntexII;



/**
 * A Sale object
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class Sale extends RevSource {

	

	@BusinessObjectField
	private int quantity = 0;
	@BusinessObjectField
	private String storeid = null;
	@BusinessObjectField
	private String prodid = null;
	
	/** Creates a new instance of this object */
	public Sale(String id) {
		super(id);
	}// constructor

	/**
	 * @return the quantity
	 */
	public int getquantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setquantity(int quantity) {
		this.quantity = quantity;
		setDirty();
	}

	/**
	 * @return the storeid
	 */
	public String getstoreid() {
		return storeid;
	}

	/**
	 * @param storeid the storeid to set
	 */
	public void setstoreid(String storeid) {
		this.storeid = storeid;
		setDirty();
	}
	/**
	 * @return the prodid
	 */
	public String getprodid() {
		return prodid;
	}

	/**
	 * @param prodid the prodid to set
	 */
	public void setprodid(String prodid) {
		this.prodid = prodid;
		setDirty();
	}


	



}