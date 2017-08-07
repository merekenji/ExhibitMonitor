package monitor.exhibit.tasks;

import java.io.File;

public class Worker implements Runnable {
	File file;
	
	public Worker(File file) {
		this.file = file;
	}
	@Override
	public void run() {
		
	}
}