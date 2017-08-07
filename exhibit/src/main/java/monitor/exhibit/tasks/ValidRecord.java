package monitor.exhibit.tasks;

import monitor.exhibit.Warehouse;
import monitor.exhibit.utilities.ApplicationContext;

public class ValidRecord implements Runnable {
	Warehouse wh;
	
	public ValidRecord(Warehouse wh) {
		this.wh = wh;
	}
	@Override
	public void run() {
		while(true) {
			wh.insertValidRecord(ApplicationContext.getValidRecords().elementAt(0));
			ApplicationContext.getValidRecords().remove(0);
		}
	}
}
