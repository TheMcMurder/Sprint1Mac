package edu.byu.isys414.jmcmurdi.IntexII;


import java.util.Date;


/**
 * A Conceptual Product object
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class CProduct extends Product {

	/*
id            VARCHAR(40) PRIMARY KEY REFERENCES product(id),
prodname      VARCHAR(250),
description   VARCHAR(250),
manufacturer  VARCHAR(250),
avgcost       NUMERIC(8,2),
cprodcomrate  NUMERIC(5,4)
	.*/

	@BusinessObjectField
	private double avgCost = 0;
	@BusinessObjectField
	private String prodName = null;
	@BusinessObjectField
	private String description = null;
	@BusinessObjectField
	private String manufacturer = null;
	@BusinessObjectField
	private double cprodComRate = 0;
	
	/** Creates a new instance of this object */
	public CProduct(String id) {
		super(id);
	}// constructor

	/**
	 * @return the avgCost
	 */
	public double getAvgCost() {
		return avgCost;
	}

	/**
	 * @param avgCost the avgCost to set
	 */
	public void setAvgCost(double avgCost) {
		this.avgCost = avgCost;
		setDirty();
	}

	/**
	 * @return the prodName
	 */
	public String getProdName() {
		return prodName;
	}

	/**
	 * @param prodName the prodName to set
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
		setDirty();
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
		setDirty();
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
		setDirty();
	}

	/**
	 * @return the cprodComRate
	 */
	public double getCprodComRate() {
		return cprodComRate;
	}

	/**
	 * @param cprodComRate the cprodComRate to set
	 */
	public void setCprodComRate(double cprodComRate) {
		this.cprodComRate = cprodComRate;
		setDirty();
	}



	


	



}