package edu.byu.isys414.jmcmurdi.IntexII;

// java.math.double;
import java.util.Date;
import java.util.Calendar;


/**
 * A Commission object
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class Commission extends BusinessObject {

/*
CREATE TABLE commission (
id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
empid         VARCHAR(40) REFERENCES employee(id),
comdate       TIMESTAMP
);
*/

	

	@BusinessObjectField
	private String empid = null;
	@BusinessObjectField
	private Date comdate = null;
	@BusinessObjectField
	private double amount = 0;
	
	/** Creates a new instance of this object */
	public Commission(String id) {
		super(id);
	}// constructor

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
	 * @return the comdate
	 */
	public Date getComdate() {
		return comdate;
	}

	/**
	 * @param comdate the comdate to set
	 */
	public void setComdate(Date comdate) {
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(comdate);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();

	   	this.comdate = realDate;
		setDirty();
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}



}