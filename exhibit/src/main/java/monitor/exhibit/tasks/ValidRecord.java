package monitor.exhibit.tasks;

import monitor.exhibit.ValidWarehouse;
import monitor.exhibit.utilities.ApplicationContext;

public class ValidRecord implements Runnable {
	ValidWarehouse wh;
	
	public ValidRecord(ValidWarehouse wh) {
		this.wh = wh;
	}
	@Override
	public void run() {
		while(true) {
			wh.insertRecord(ApplicationContext.getValidRecords().elementAt(0));
			ApplicationContext.getValidRecords().remove(0);
		}
	}
}
