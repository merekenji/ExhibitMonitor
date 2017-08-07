package monitor.exhibit.tasks;

import monitor.exhibit.utilities.ApplicationContext;

public class InvalidRecord implements Runnable {
	@Override
	public void run() {
		while(ApplicationContext.getValidRecords().isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
