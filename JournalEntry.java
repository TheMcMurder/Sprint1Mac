package edu.byu.isys414.jmcmurdi.IntexII;
import java.util.Calendar;
import java.util.Date;


/**
 * An employee. Example BO that has inheritance.
 * 
 * @author Justin McMurdie
 * @version 1.1
 */
public class JournalEntry extends BusinessObject {

	

	@BusinessObjectField
	private Date transdate = null;
	

	/** Creates a new instance of this object */
	public JournalEntry(String id) {
		super(id);
	}// constructor


	/**
	 * @return the transdate
	 */
	public Date getTransdate() {
		return transdate;
	}


	/**
	 * @param transdate the transdate to set
	 */
	public void setTransdate(Date transdate) {
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(transdate);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();

	    this.transdate = realDate;
		setDirty();
	}
}

