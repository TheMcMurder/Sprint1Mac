package edu.byu.isys414.jmcmurdi.IntexII;

//import java.math.double;
import java.util.Date;

/**
 * A Store
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class Store extends BusinessObject {

	/*
	 * id VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id), managerid
	 * VARCHAR(40) REFERENCES employee(id), location VARCHAR(250), address
	 * VARCHAR(250), phone VARCHAR(50), salestaxrate NUMERIC(5,4), subnetid
	 * VARCHAR(250)
	 */

	@BusinessObjectField
	private String managerid = null;
	@BusinessObjectField
	private String location = null;
	@BusinessObjectField
	private String phone = null;
	@BusinessObjectField
	private String address = null;
	@BusinessObjectField
	private double salestaxrate = 0;
	@BusinessObjectField
	private String subnetid = null;

	/** Creates a new instance of this object */
	public Store(String id) {
		super(id);
	}// constructor

	/**
	 * @return the managerid
	 */
	public String getManagerid() {
		return managerid;
	}

	/**
	 * @param managerid the managerid to set
	 */
	public void setManagerid(String managerid) {
		this.managerid = managerid;
    setDirty();
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
    setDirty();
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
    setDirty();
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
    setDirty();
	}

	/**
	 * @return the salestaxrate
	 */
	public double getSalestaxrate() {
		return salestaxrate;
	}

	/**
	 * @param salestaxrate the salestaxrate to set
	 */
	public void setSalestaxrate(double salestaxrate) {
		this.salestaxrate = salestaxrate;
    setDirty();
	}

	/**
	 * @return the subnetid
	 */
	public String getSubnetid() {
		return subnetid;
	}

	/**
	 * @param subnetid the subnetid to set
	 */
	public void setSubnetid(String subnetid) {
		this.subnetid = subnetid;
    setDirty();
	}
}