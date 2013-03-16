package edu.byu.isys414.jmcmurdi.IntexII;

public class ConceptualRental extends CProduct{
	
	@BusinessObjectField
	private double pricePerDay = 0;
	@BusinessObjectField
	private double replacementPrice = 0;
		
	
	/** Creates a new instance of this object */
	public ConceptualRental(String id) {
		super(id);
	}// constructor


	public double getPricePerDay() {
		return pricePerDay;
	}


	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
		setDirty();
	}


	public double getReplacementPrice() {
		return replacementPrice;
	}


	public void setReplacementPrice(double replacementPrice) {
		this.replacementPrice = replacementPrice;
		setDirty();
	}

}
