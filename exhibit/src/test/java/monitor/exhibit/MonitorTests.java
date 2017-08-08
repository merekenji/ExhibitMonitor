package monitor.exhibit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import monitor.exhibit.utilities.ApplicationContext;
import monitor.exhibit.utilities.XMLParser;

public class MonitorTests {

	@Test
	public void parseXML() {
		File configXML = new File("src/test/resources/config.xml");
		XMLParser.parse(configXML.getAbsolutePath());
		assertEquals(3, ApplicationContext.getInputFiles().size());
		assertEquals(2, ApplicationContext.getOutputFiles().size());
	}

}
