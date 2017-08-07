package monitor.exhibit.tasks;

import monitor.exhibit.dao.RecordDAO;
import monitor.exhibit.utilities.ApplicationContext;

public class InvalidRecord implements Runnable {
	private RecordDAO recordDAO;
	
	public InvalidRecord(RecordDAO recordDAO) {
		this.recordDAO = recordDAO;
	}
	@Override
	public void run() {
		while(true) {
			recordDAO.insertInvalidRecord(ApplicationContext.getInvalidRecords().elementAt(0));
		}
	}
}
