package monitor.exhibit.tasks;

import java.util.Vector;

import monitor.exhibit.beans.Record;
import monitor.exhibit.dao.RecordDAO;
import monitor.exhibit.utilities.ApplicationContext;

public class InvalidRecord implements Runnable {
	private RecordDAO recordDAO;
	Vector<Record> records;

	public InvalidRecord(RecordDAO recordDAO, Vector<Record> records) {
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
				recordDAO.insertInvalidRecord();
			}
		}
	}
}
