package monitor.exhibit.tasks;

import monitor.exhibit.utilities.ApplicationContext;

public class ValidRecord implements Runnable {
	@Override
	public synchronized void run() {
		while(ApplicationContext.getValidRecords().isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
