package edu.byu.isys414.jmcmurdi.IntexII;

import java.util.Date;

public class Rental extends RevSource{

	@BusinessObjectField
	private Date dateIn = null;
	
	@BusinessObjectField
	private Date dateOut = null;
	
	@BusinessObjectField
	private Date dateDue = null;
	
	@BusinessObjectField
	private boolean reminderSend = false;
	
	@BusinessObjectField
	private int workOrderNum = 0;
	

	
	//Date in - Date out
	//Date due
	//Work Order #
	//remindersend(boolean)
	
	
	/** Creates a new instance of this object */
	public Rental(String id) {
		super(id);
	}// constructor



	public Date getDateIn() {
		return dateIn;
		
	}



	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
		setDirty();
	}



	public Date getDateOut() {
		return dateOut;
	}



	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
		setDirty();
	}



	public Date getDateDue() {
		return dateDue;
	}



	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
		setDirty();
	}



	public boolean isReminderSend() {
		return reminderSend;
	}



	public void setReminderSend(boolean reminderSend) {
		this.reminderSend = reminderSend;
		setDirty();
	}



	public int getWorkOrderNum() {
		return workOrderNum;
	}



	public void setWorkOrderNum(int workOrderNum) {
		this.workOrderNum = workOrderNum;
		setDirty();
	}
}
