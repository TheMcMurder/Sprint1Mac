package edu.byu.isys414.jmcmurdi.IntexII;

import java.util.Calendar;
import java.util.Date;


/**
 * A Physical Product object
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class PProduct extends Product {




	@BusinessObjectField
	private double pprodcomrate = 0;
	@BusinessObjectField
	private String serialnum = null;
	@BusinessObjectField
	private String shelflocation = null;
	@BusinessObjectField
	private String status = null;
	@BusinessObjectField
	private double cost = 0;
	@BusinessObjectField
	private Date datepuchased = null;
	@BusinessObjectField
	private String cprodid = null;
	@BusinessObjectField
	private String storeid = null;
	@BusinessObjectField
	private String ppname = null;

	
	
	/** Creates a new instance of this object */
	public PProduct(String id) {
		super(id);
	}// constructor


	/**
	 * @return the pprodcomrate
	 */
	public double getPprodcomrate() {
		return pprodcomrate;
	}


	/**
	 * @param pprodcomrate the pprodcomrate to set
	 */
	public void setPprodcomrate(double pprodcomrate) {
		this.pprodcomrate = pprodcomrate;
		setDirty();
	}


	/**
	 * @return the serialnum
	 */
	public String getSerialnum() {
		return serialnum;
	}


	/**
	 * @param serialnum the serialnum to set
	 */
	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
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


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
		setDirty();
	}


	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}


	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
		setDirty();
	}


	/**
	 * @return the datepuchased
	 */
	public Date getDatepuchased() {
		return datepuchased;
	}


	/**
	 * @param datepuchased the datepuchased to set
	 */
	public void setDatepuchased(Date datepuchased) {
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(datepuchased);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();



		this.datepuchased = realDate;
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


	public String getPpname() {
		return ppname;
	}


	public void setPpname(String ppname) {
		this.ppname = ppname;
		setDirty();
	}


	

	


}