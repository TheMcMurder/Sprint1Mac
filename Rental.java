package edu.byu.isys414.jmcmurdi.IntexII;

import java.util.Calendar;
import java.util.Date;

public class Rental extends RevSource{

	@BusinessObjectField
	private Date dateIn = null;
	
	@BusinessObjectField
	private Date dateOut = null;
	
	@BusinessObjectField
	private Date dateDue = null;
	
	@BusinessObjectField
	private boolean reminderSent = false;
	
	@BusinessObjectField
	private int workOrderNum = 0;
	
	@BusinessObjectField
	private String forRentid= null;
	
	@BusinessObjectField
	private int numDays = 0;
	

	
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
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(dateIn);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();
		
		this.dateIn = dateIn;
		setDirty();
	}



	public Date getDateOut() {
		return dateOut;
	}



	public void setDateOut(Date dateOut) {
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(dateOut);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();
		
		this.dateOut = dateOut;
		setDirty();
	}



	public Date getDateDue() {
		return dateDue;
	}



	public void setDateDue(Date dateDue) {
		
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(dateDue);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();
		this.dateDue = dateDue;
		setDirty();
	}



	public boolean isReminderSent() {
		return reminderSent;
	}



	public void setReminderSent(boolean reminderSend) {
		this.reminderSent = reminderSend;
		setDirty();
	}



	public int getWorkOrderNum() {
		return workOrderNum;
	}



	public void setWorkOrderNum(int workOrderNum) {
		this.workOrderNum = workOrderNum;
		setDirty();
	}



	public String getForRentid() {
		return forRentid;
	}



	public void setForRentid(String forRentid) {
		this.forRentid = forRentid;
		setDirty();
	}



	public int getNumDays() {
		return numDays;
	}



	public void setNumDays(int numDays) {
		this.numDays = numDays;
		setDirty();
	}
}
