package monitor.exhibit.tasks;

import java.io.File;

public class Poller implements Runnable{
	@Override
	public void run() {
		while(true) {
			File dir = new File("src/test/resources/input");
			for(File file : dir.listFiles()) {
				if(isValidFile(file) && !isDuplicate(file) && isOnTime(file)) {
					
				}
			}
		}
	}
	public boolean isValidFile(File file) {
		return false;
	}
	public boolean isDuplicate(File file) {
		return false;
	}
	public boolean isOnTime(File file) {
		return false;
	}
}
