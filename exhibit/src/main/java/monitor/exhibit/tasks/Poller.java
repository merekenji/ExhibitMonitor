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
			File[] files = dir.listFiles();
			if(files != null) {
				for(File file : dir.listFiles()) {
					if(isValidFile(file) && !isDuplicate(file) && isOnTime(file)) {
						File processDir = new File("src/test/resources/process/" + file.getName());
						boolean renamed = file.renameTo(processDir);
						Calendar cal = Calendar.getInstance();
						ApplicationContext.addProcessedFile(file.getName(), cal.getTime());
						if(!renamed) {
							System.out.println("Not Moved to Process Folder");
						}
						Thread worker = new Thread(new Worker(file));
						worker.setName("Worker-" + file.getName());
						worker.start();
					} else {
						boolean deleted = file.delete();
						if(!deleted) {
							System.out.println("Not Deleted");
						}
					}
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