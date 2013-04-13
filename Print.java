package edu.byu.isys414.jmcmurdi.IntexII;

public class Print extends BusinessObject {
	
	/** Creates a new instance of this object */
	public Print(String id) {
		super(id);
	}// constructor
	
	//price, size, type, printorderid
	@BusinessObjectField
	private String type = null;
	@BusinessObjectField
	private String printorderid = null;
	@BusinessObjectField
	private String size = null;
	@BusinessObjectField
	private double price = 0;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the printorderid
	 */
	public String getPrintorderid() {
		return printorderid;
	}
	/**
	 * @param printorderid the printorderid to set
	 */
	public void setPrintorderid(String printorderid) {
		this.printorderid = printorderid;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	

}
