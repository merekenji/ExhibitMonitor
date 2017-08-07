package monitor.exhibit.tasks;

import monitor.exhibit.dao.RecordDAO;
import monitor.exhibit.utilities.ApplicationContext;

public class ValidRecord implements Runnable {
	RecordDAO recordDAO = null;
	
	public ValidRecord(RecordDAO recordDAO) {
		this.recordDAO = recordDAO;
	}
	@Override
	public void run() {
		while(true) {
			recordDAO.insertValidRecord(ApplicationContext.getValidRecords().elementAt(0));
		}
	}
}
