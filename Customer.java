package edu.byu.isys414.jmcmurdi.IntexII;


/**
 * a Customer
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class Customer extends BusinessObject {

	

	@BusinessObjectField
	private String firstName = null;
	@BusinessObjectField
	private String lastName = null;
	@BusinessObjectField
	private String email = null;
	@BusinessObjectField
	private String phone = null;
	@BusinessObjectField
	private String address = null;
	@BusinessObjectField
	private String state = null;
	@BusinessObjectField
	private String zip = null;
	@BusinessObjectField
	private String password = null;
	@BusinessObjectField
	private boolean verified = false;
	@BusinessObjectField
	private String validation = null;

	/** Creates a new instance of this object */
	public Customer(String id) {
		super(id);
	}// constructor

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		setDirty();
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
		setDirty();
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
		setDirty();
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
		setDirty();
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
		setDirty();
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
		setDirty();
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
		setDirty();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		setDirty();
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
		setDirty();
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
		setDirty();
	}

	

}