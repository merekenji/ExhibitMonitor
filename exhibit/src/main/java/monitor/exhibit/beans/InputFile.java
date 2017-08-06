package monitor.exhibit.beans;

import java.util.Date;
import java.util.List;

public class InputFile {
	private String name;
	private Date date;
	private List<Field> fields;
	
	public InputFile(String name, Date date, List<Field> fields) {
		this.name = name;
		this.date = date;
		this.fields = fields;
	}
	public String getName() {
		return name;
	}
	public Date getDate() {
		return date;
	}
	public List<Field> getFields() {
		return fields;
	}
}
