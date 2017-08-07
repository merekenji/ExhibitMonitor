package monitor.exhibit.tasks;

import java.util.Vector;

import monitor.exhibit.beans.Record;
import monitor.exhibit.dao.RecordDAO;
import monitor.exhibit.utilities.ApplicationContext;

public class ValidRecord implements Runnable {
	RecordDAO recordDAO = null;
	Vector<Record> records;

	public ValidRecord(RecordDAO recordDAO, Vector<Record> records) {
		this.recordDAO = recordDAO;
		this.records = records;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (records) {
				while (records.isEmpty()) {
					try {
						records.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				recordDAO.insertValidRecord();
			}
		}
	}
}
