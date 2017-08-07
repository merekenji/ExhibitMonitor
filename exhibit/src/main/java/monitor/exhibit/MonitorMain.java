package monitor.exhibit;

import java.io.File;

import monitor.exhibit.dao.RecordDAO;
import monitor.exhibit.tasks.InvalidRecord;
import monitor.exhibit.tasks.Poller;
import monitor.exhibit.tasks.ValidRecord;
import monitor.exhibit.utilities.XMLParser;

public class MonitorMain {
	public static void main(String[] args) {
		File configXML = new File("src/test/resources/config.xml");
		XMLParser.parse(configXML.getAbsolutePath());
		Thread poller = new Thread(new Poller());
		poller.setName("Poller");
		poller.start();
		
		RecordDAO recordDAO = new RecordDAO();
		
		Thread addValidRecord = new Thread(new ValidRecord(recordDAO));
		addValidRecord.setName("AddValidRecord");
		addValidRecord.start();
		
		Thread addInvalidRecord = new Thread(new InvalidRecord(recordDAO));
		addInvalidRecord.setName("AddInvalidRecord");
		addInvalidRecord.start();
	}
}
