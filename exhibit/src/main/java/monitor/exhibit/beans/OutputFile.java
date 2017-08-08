package monitor.exhibit.beans;

import java.util.Date;
import java.util.List;

public class OutputFile {
	
	private String name;
	private Date date;
	private List<String> dependencies;
	
	public OutputFile(String name, Date date, List<String> dependencies) {
		this.name = name;
		this.date = date;
		this.dependencies = dependencies;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getDate() {
		return date;
	}
	
	public List<String> getDependencies() {
		return dependencies;
	}
	
}
