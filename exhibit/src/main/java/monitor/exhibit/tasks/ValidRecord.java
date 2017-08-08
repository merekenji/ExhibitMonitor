package monitor.exhibit.tasks;

import java.util.Vector;

import monitor.exhibit.beans.Record;
import monitor.exhibit.dao.RecordDAO;

public class ValidRecord implements Runnable {
	
	private RecordDAO recordDAO = null;
	private Vector<Record> records;

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
				recordDAO.insertValidRecord(records.get(0));
			}
		}
	}
	
}
