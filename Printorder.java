package edu.byu.isys414.jmcmurdi.IntexII;

public class Printorder extends BusinessObject {

	
	/** Creates a new instance of this object */
	public Printorder(String id) {
		super(id);
	}// constructor
	
		@BusinessObjectField
		private double quantity = 0;

		/**
		 * @return the quantity
		 */
		public double getQuantity() {
			return quantity;
		}

		/**
		 * @param quantity the quantity to set
		 */
		public void setQuantity(double quantity) {
			this.quantity = quantity;
			setDirty();
		}
		
		
		
		
	
}
