package monitor.exhibit.beans;

import java.util.Date;

public class Record {
	private String fileName;
	private int rowNum;
	private Date date;
	private String record;
	
	public Record(String fileName, int rowNum, Date date, String record) {
		this.fileName = fileName;
		this.rowNum = rowNum;
		this.date = date;
		this.record = record;
	}
	public String getFileName() {
		return fileName;
	}
	public int getRowNum() {
		return rowNum;
	}
	public Date getDate() {
		return date;
	}
	public String getRecord() {
		return record;
	}
}
