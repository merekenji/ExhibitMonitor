package monitor.exhibit.tasks;

import monitor.exhibit.Warehouse;
import monitor.exhibit.utilities.ApplicationContext;

public class InvalidRecord implements Runnable {
	private Warehouse wh;
	
	public InvalidRecord(Warehouse wh) {
		this.wh = wh;
	}
	@Override
	public void run() {
		while(true) {
			wh.insertInvalidRecord(ApplicationContext.getInvalidRecords().elementAt(0));
			ApplicationContext.getInvalidRecords().remove(0);
		}
	}
}
