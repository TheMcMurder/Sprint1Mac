package edu.byu.isys414.jmcmurdi.IntexII;

//import java.math.double;
import java.util.Date;


/**
 * A Product object
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class Product extends BusinessObject {


	@BusinessObjectField
	private double prodPrice = 0;
	@BusinessObjectField
	private String prodType = null;
	@BusinessObjectField 
	private int prodnum = 0;
	@BusinessObjectField
	private String name = null;
	
	/** Creates a new instance of this object */
	public Product(String id) {
		super(id);
	}// constructor

	/**
	 * @return the prodPrice
	 */
	public double getProdPrice() {
		return prodPrice;
	}

	/**
	 * @param prodPrice the prodPrice to set
	 */
	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
		setDirty();
	}

	/**
	 * @return the prodType
	 */
	public String getProdType() {
		return prodType;
	}

	/**
	 * @param prodType the prodType to set
	 */
	public void setProdType(String prodType) {
		this.prodType = prodType;
		setDirty();
	}

	/**
	 * @return the prodnum
	 */
	public int getProdnum() {
		return prodnum;
	}

	/**
	 * @param prodnum the prodnum to set
	 */
	public void setProdnum(int prodnum) {
		this.prodnum = prodnum;
		setDirty();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setDirty();
	}



	



}