package edu.byu.isys414.jmcmurdi.IntexII;

public class Picture extends BusinessObject {
	
	
	
	@BusinessObjectField
	private String custid = null;
	@BusinessObjectField
	private String caption = null;
	@BusinessObjectField
	private String picname = null;
	@BusinessObjectField
	private String printorderid = null;
	@BusinessObjectField
	private String pic = null;
	
	/** Creates a new instance of this object */
	public Picture(String id) {
		super(id);
	}// constructor

	/**
	 * @return the custid
	 */
	public String getCustid() {
		return custid;
	}

	/**
	 * @param custid the custid to set
	 */
	public void setCustid(String custid) {
		this.custid = custid;
		setDirty();
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
		setDirty();
	}

	/**
	 * @return the picname
	 */
	public String getPicname() {
		return picname;
	}

	/**
	 * @param picname the picname to set
	 */
	public void setPicname(String picname) {
		this.picname = picname;
		setDirty();
	}

	/**
	 * @return the printorderid
	 */
	public String getPrintorderid() {
		return printorderid;
	}

	/**
	 * @param printorderid the printorderid to set
	 */
	public void setPrintorderid(String printorderid) {
		this.printorderid = printorderid;
		setDirty();
	}

	/**
	 * @return the pic
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * @param pic the pic to set
	 */
	public void setPic(String pic) {
		this.pic = pic;
		setDirty();
	}
	
	

}
