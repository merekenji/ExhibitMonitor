package monitor.exhibit;

import java.io.File;
import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import monitor.exhibit.beans.OutputFile;
import monitor.exhibit.dao.RecordDAO;
import monitor.exhibit.tasks.InvalidRecord;
import monitor.exhibit.tasks.Output;
import monitor.exhibit.tasks.Poller;
import monitor.exhibit.tasks.ValidRecord;
import monitor.exhibit.utilities.ApplicationContext;
import monitor.exhibit.utilities.XMLParser;

public class MonitorMain {
	
	public static void main(String[] args) {
		File configXML = new File("src/test/resources/config.xml");
		XMLParser.parse(configXML.getAbsolutePath());
		Thread poller = new Thread(new Poller());
		poller.setName("Poller");
		poller.start();
		
		RecordDAO recordDAO = new RecordDAO();
		
		Thread addValidRecord = new Thread(new ValidRecord(recordDAO, ApplicationContext.getValidRecords()));
		addValidRecord.setName("AddValidRecord");
		addValidRecord.start();
		
		Thread addInvalidRecord = new Thread(new InvalidRecord(recordDAO, ApplicationContext.getInvalidRecords()));
		addInvalidRecord.setName("AddInvalidRecord");
		addInvalidRecord.start();
		
		if(!ApplicationContext.getOutputFiles().isEmpty()) {
			for(OutputFile outputFile : ApplicationContext.getOutputFiles()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(outputFile.getDate());
				Timer timer = new Timer("Timer-" + outputFile.getName());
				timer.schedule(new Output(recordDAO, outputFile), 20000, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
			}
		}
	}
	
}
