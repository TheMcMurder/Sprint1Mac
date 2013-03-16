package edu.byu.isys414.jmcmurdi.IntexII;

//import java.math.double;
import java.util.Date;


/**
 * A Revenue Soruce object
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class RevSource extends BusinessObject {

	/*
	*id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
chargeamount  NUMERIC(8,2),
revtype       VARCHAR(250)
);
	*/

	

	@BusinessObjectField
	private double chargeamount = 0;
	@BusinessObjectField
	private String revtype = null;
	@BusinessObjectField
	private String transid = null;
	
	/** Creates a new instance of this object */
	public RevSource(String id) {
		super(id);
	}// constructor

	/**
	 * @return the chargeamount
	 */
	public double getChargeamount() {
		return chargeamount;
	}

	/**
	 * @param chargeamount the chargeamount to set
	 */
	public void setChargeamount(double chargeamount) {
		this.chargeamount = chargeamount;
		setDirty();
	}

	/**
	 * @return the revtype
	 */
	public String getRevtype() {
		return revtype;
	}

	/**
	 * @param revtype the revtype to set
	 */
	public void setRevtype(String revtype) {
		this.revtype = revtype;
		setDirty();
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
		setDirty();
	}

}