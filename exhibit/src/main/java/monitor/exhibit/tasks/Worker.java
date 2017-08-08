package monitor.exhibit.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import monitor.exhibit.beans.Field;
import monitor.exhibit.beans.InputFile;
import monitor.exhibit.beans.Record;
import monitor.exhibit.utilities.ApplicationContext;

public class Worker implements Runnable {
	
	private File file;
	
	public Worker(File file) {
		this.file = file;
	}
	
	@Override
	public void run() {
		BufferedReader br = null;
		FileReader fr = null;
		String line = "";
		int rowNum = 1;
		
		try {
			fr = new FileReader(file.getAbsolutePath());
			br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null) {
				String[] data = line.split(",");
				boolean valid = isValid(data);
				Calendar cal = Calendar.getInstance();
				Date date = cal.getTime();
				
				if(valid) {
					synchronized(ApplicationContext.getValidRecords()) {
						ApplicationContext.addValidRecord(new Record(file.getName(), rowNum, date, line));
						ApplicationContext.getValidRecords().notifyAll();
					}
				} else {
					synchronized(ApplicationContext.getInvalidRecords()) {
						ApplicationContext.addInvalidRecord(new Record(file.getName(), rowNum, date, line));
						ApplicationContext.getInvalidRecords().notifyAll();
					}
				}
				
				rowNum++;
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isValid(String[] data) {
		for(InputFile f : ApplicationContext.getInputFiles()) {
			if(f.getName().equals(file.getName()) && data.length == f.getFields().size()) {
				return isValidFields(data, f.getFields());
			}
		}
		
		return false;
	}
	
	public boolean isValidFields(String[] data, List<Field> fields) {
		for(int i=0; i<fields.size(); i++) {
			if(fields.get(i).getType().equals("int")) {
				try {
					Integer.parseInt(data[i]);
				} catch(NumberFormatException e) {
					return false;
				}
			} else if(fields.get(i).getType().equals("date")) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
					format.parse(data[i]);
				} catch(ParseException e) {
					return false;
				}
			} else if(fields.get(i).getType().equals("double")) {
				try {
					Double.parseDouble(data[i]);
				} catch(NumberFormatException e) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}