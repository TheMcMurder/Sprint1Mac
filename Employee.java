/////////////////////////////////////////////////////////////////
///   This file is an example of an Object Relational Mapping in
///   the ISys Core at Brigham Young University.  Students
///   may use the code as part of the 413 course in their
///   milestones following this one, but no permission is given
///   to use this code is any other way.  Since we will likely
///   use this code again in a future year, please DO NOT post
///   the code to a web site, share it with others, or pass
///   it on in any way.

package edu.byu.isys414.jmcmurdi.IntexII;

import java.util.Calendar;
//import java.math.double;
import java.util.Date;

/**
 * An employee. Example BO that has inheritance.
 * 
 * @author Conan C. Albrecht <conan@warp.byu.edu>
 * @version 1.1
 */
public class Employee extends BusinessObject {

	@BusinessObjectField
	private String firstname = null;
	@BusinessObjectField
	private String lastname = null;
	@BusinessObjectField
	private String phone = null;
	@BusinessObjectField
	private java.util.Date birthDate = null;
	@BusinessObjectField
	private java.util.Date hiredate = null;
	@BusinessObjectField
	private String username = null;
	@BusinessObjectField
	private double salary = 0;
	@BusinessObjectField
	private double favoriteNumber = 0.0;
	@BusinessObjectField
	private int IQ = 0;
	@BusinessObjectField
	private double distance = 0;
	@BusinessObjectField
	private String assignedStore = null;

	/** Creates a new instance of BusinessObject */
	public Employee(String id) {
		super(id);
	}// constructor

	/**
	 * Returns the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 * 
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
		setDirty();
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
		setDirty();
	}

	/**
	 * @return the favoriteNumber
	 */
	public double getFavoriteNumber() {
		return favoriteNumber;
	}

	/**
	 * @param favoriteNumber
	 *            the favoriteNumber to set
	 */
	public void setFavoriteNumber(double favoriteNumber) {
		this.favoriteNumber = favoriteNumber;
		setDirty();
	}

	/**
	 * @return the IQ
	 */
	public int getIQ() {
		return IQ;
	}

	/**
	 * @param IQ
	 *            the IQ to set
	 */
	public void setIQ(int IQ) {
		this.IQ = IQ;
		setDirty();
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
		setDirty();
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
		setDirty();
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
		setDirty();
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
		setDirty();
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(java.util.Date birthDate) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(birthDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		java.util.Date realDate = calendar.getTime();

		this.birthDate = realDate;

		setDirty();
	}

	/**
	 * @return the hiredate
	 */
	public Date getHiredate() {
		return hiredate;
	}

	/**
	 * @param hiredate
	 *            the hiredate to set
	 */
	public void setHiredate(java.util.Date hiredate) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(hiredate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		java.util.Date realDate = calendar.getTime();

		this.hiredate = realDate;
		setDirty();
	}

	/**
	 * @return the assignedStore
	 */
	public String getAssignedStore() {
		return assignedStore;
	}

	/**
	 * @param assignedStore
	 *            the assignedStore to set
	 */
	public void setAssignedStore(String assignedStore) {
		this.assignedStore = assignedStore;
		setDirty();
	}

}// class
