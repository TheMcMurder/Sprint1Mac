package edu.byu.isys414.jmcmurdi.IntexII;

//import java.math.double;


/**
 * A Debit or Credit object
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class DebitCredit extends BusinessObject {

//TODO
	//I need to implement ENUMS but for now Debit is true and Credit is false

	

	@BusinessObjectField
	private String journalEntryid = null;
	@BusinessObjectField
	private Boolean isDebit = false;
	@BusinessObjectField
	private String glName = null;
	@BusinessObjectField
	private double amount = 0;
	
	/** Creates a new instance of this object */
	public DebitCredit(String id) {
		super(id);
	}// constructor

	/**
	 * @return the journalEntryid
	 */
	public String getJournalEntryid() {
		return journalEntryid;
	}

	/**
	 * @param journalEntryid the journalEntryid to set
	 */
	public void setJournalEntryid(String journalEntryid) {
		this.journalEntryid = journalEntryid;
		setDirty();
	}

	/**
	 * @return the debitCred
	 */
	public Boolean isIsDebit() {
		return isDebit;
	}

	/**
	 * @param debitCred the debitCred to set
	 */
	public void setIsDebit(Boolean isDebit) {
		this.isDebit = isDebit;
		setDirty();
	}

	/**
	 * @return the glName
	 */
	public String getGlName() {
		return glName;
	}

	/**
	 * @param glName the glName to set
	 */
	public void setGlName(String glName) {
		this.glName = glName;
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
		setDirty();
	}





}