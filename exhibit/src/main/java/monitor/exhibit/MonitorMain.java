package monitor.exhibit;

import java.io.File;

import monitor.exhibit.tasks.Poller;
import monitor.exhibit.utilities.XMLParser;

public class MonitorMain {
	public static void main(String[] args) {
		File configXML = new File("src/test/resources/config.xml");
		XMLParser.parse(configXML.getAbsolutePath());
		Thread poller = new Thread(new Poller());
		poller.setName("Poller");
		poller.start();
		
		
	}
}
