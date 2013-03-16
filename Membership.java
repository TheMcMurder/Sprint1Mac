package edu.byu.isys414.jmcmurdi.IntexII;

public class Membership extends BusinessObject {


	@BusinessObjectField
	private String custId = null;
	@BusinessObjectField
	private String creditCard = null;
	

	/** Creates a new instance of this object */
	public Membership(String id) {
		super(id);
	}// constructor


	public String getCustId() {
		return custId;
	}


	public void setCustId(String custId) {
		this.custId = custId;
		setDirty();
	}


	public String getCreditCard() {
		return creditCard;
	}


	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
		setDirty();
	}
	

}
