package edu.byu.isys414.jmcmurdi.IntexII;
import java.util.Calendar;
import java.util.Date;


/**
 * A General Ledger Object
 * @author Justin McMurdie
 * @version 1.1
 */
public class GeneralLedger extends BusinessObject {

	

	@BusinessObjectField
	private String type = null;
	@BusinessObjectField
	private String accountName = null;
	@BusinessObjectField
	private double balance = 0;


	/** Creates a new instance of this object */
	public GeneralLedger(String id) {
		super(id);
	}// constructor


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
		setDirty();
	}


	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}


	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
		setDirty();
	}


	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}


	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
		setDirty();
	}


}

