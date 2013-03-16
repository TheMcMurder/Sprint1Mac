package edu.byu.isys414.jmcmurdi.IntexII;

public class ForRent extends PProduct{

	@BusinessObjectField
	private int timesRented = 0;
	
	/** Creates a new instance of this object */
	public ForRent(String id) {
		super(id);
	}// constructor

	public int getTimesRented() {
		return timesRented;
	}

	public void setTimesRented(int timesRented) {
		this.timesRented = timesRented;
		setDirty();
	}
}
