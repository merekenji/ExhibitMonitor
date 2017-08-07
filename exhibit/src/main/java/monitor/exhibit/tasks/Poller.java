package monitor.exhibit.tasks;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import monitor.exhibit.beans.InputFile;
import monitor.exhibit.utilities.ApplicationContext;

public class Poller implements Runnable{
	@Override
	public void run() {
		while(true) {
			File dir = new File("src/test/resources/input");
			for(File file : dir.listFiles()) {
				if(isValidFile(file) && !isDuplicate(file) && isOnTime(file)) {
					Thread worker = new Thread(new Worker(file));
					worker.setName("Worker-" + file.getName());
					worker.start();
				}
			}
		}
	}
	public boolean isValidFile(File file) {
		for(InputFile inputFile : ApplicationContext.getInputFiles()) {
			if(inputFile.getName().equals(file.getName())) {
				return true;
			}
		}
		return false;
	}
	public boolean isDuplicate(File file) {
		Date date = ApplicationContext.getDateFromProcessedFile(file.getName());
		if(date == null) {
			return false;
		}
		Calendar today = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (today.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && today.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR));
	}
	public boolean isOnTime(File file) {
		for(InputFile inputFile : ApplicationContext.getInputFiles()) {
			if(file.getName().equals(inputFile.getName()) && file.lastModified() < inputFile.getDate().getTime()) {
				return true;
			}
		}
		return false;
	}
}