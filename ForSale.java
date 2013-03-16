package edu.byu.isys414.jmcmurdi.IntexII;

public class ForSale extends PProduct{
	
	@BusinessObjectField
	private String usedStatus = null;
	
	/** Creates a new instance of this object */
	public ForSale(String id) {
		super(id);
	}// constructor

	public String getUsedStatus() {
		return usedStatus;
	}

	public void setUsedStatus(String usedStatus) {
		this.usedStatus = usedStatus;
		setDirty();
	}
}
