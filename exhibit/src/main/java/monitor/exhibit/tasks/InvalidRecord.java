package monitor.exhibit.tasks;

import monitor.exhibit.InvalidWarehouse;
import monitor.exhibit.utilities.ApplicationContext;

public class InvalidRecord implements Runnable {
	private InvalidWarehouse wh;
	
	public InvalidRecord(InvalidWarehouse wh) {
		this.wh = wh;
	}
	@Override
	public void run() {
		while(true) {
			wh.insertRecord(ApplicationContext.getInvalidRecords().elementAt(0));
			ApplicationContext.getInvalidRecords().remove(0);
		}
	}
}
