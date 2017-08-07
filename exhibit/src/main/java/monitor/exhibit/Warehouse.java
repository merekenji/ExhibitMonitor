package monitor.exhibit;

import monitor.exhibit.beans.Record;
import monitor.exhibit.dao.InvalidRecordDAO;
import monitor.exhibit.dao.ValidRecordDAO;

public class Warehouse {
	public synchronized void insertValidRecord(Record record) {
		ValidRecordDAO validRecordDAO = new ValidRecordDAO();
		validRecordDAO.insertRecord(record);
	}
	public synchronized void insertInvalidRecord(Record record) {
		InvalidRecordDAO invalidRecordDAO = new InvalidRecordDAO();
		invalidRecordDAO.insertRecord(record);
	}
}
