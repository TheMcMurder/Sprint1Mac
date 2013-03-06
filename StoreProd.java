package edu.byu.isys414.jmcmurdi.IntexII;


import java.util.Date;


/**
 * A Store Product object
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class StoreProd extends BusinessObject {

/*

quantityleft  INTEGER,

);
*/


	@BusinessObjectField
	private int quantityleft = 0;
	@BusinessObjectField
	private String cprodid = null;
	@BusinessObjectField
	private String storeid = null;
	@BusinessObjectField
	private String shelflocation = null;
	
	
	/** Creates a new instance of this object */
	public StoreProd(String id) {
		super(id);
	}// constructor


	/**
	 * @return the quantityleft
	 */
	public int getQuantityleft() {
		return quantityleft;
	}


	/**
	 * @param quantityleft the quantityleft to set
	 */
	public void setQuantityleft(int quantityleft) {
		this.quantityleft = quantityleft;
		setDirty();
	}


	/**
	 * @return the cprodid
	 */
	public String getCprodid() {
		return cprodid;
	}


	/**
	 * @param cprodid the cprodid to set
	 */
	public void setCprodid(String cprodid) {
		this.cprodid = cprodid;
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
	 * @return the shelflocation
	 */
	public String getShelflocation() {
		return shelflocation;
	}


	/**
	 * @param shelflocation the shelflocation to set
	 */
	public void setShelflocation(String shelflocation) {
		this.shelflocation = shelflocation;
		setDirty();
	}


}