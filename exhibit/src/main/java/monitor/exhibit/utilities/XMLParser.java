package monitor.exhibit.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import monitor.exhibit.beans.Field;
import monitor.exhibit.beans.InputFile;
import monitor.exhibit.beans.OutputFile;

public class XMLParser {
	
	private static Document doc;
	private static String path;
	
	private XMLParser() {
		//to prevent object creation
	}
	public static void parse(String p) {
		path = p;
		parseXMLFile();
		readXMLFile();
	}
	public static void parseXMLFile() {
		File file = new File(path);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(file);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	public static void readXMLFile() {
		if(doc != null) {
			Element docEle = doc.getDocumentElement();
			readInputFiles(docEle.getElementsByTagName("inputfile"));
			readOutputFiles(docEle.getElementsByTagName("outputfile"));
		}
	}
	public static void readInputFiles(NodeList nl) {
		if(nl != null && nl.getLength() > 0) {
			for(int i=0; i<nl.getLength(); i++) {
				Element ele = (Element) nl.item(i);
				String fileName = ele.getAttribute("name");
				String time = ele.getAttribute("time");
				String gracePeriod = ele.getAttribute("grace-period");
				String[] timeSplit = time.split(":");
				int minute = Integer.parseInt(timeSplit[1]) + Integer.parseInt(gracePeriod);
				int hour = Integer.parseInt(timeSplit[0]);
				while(minute >= 60) {
					hour += 1;
					minute -= 60;
				}
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, hour);
				cal.set(Calendar.MINUTE, minute);
				cal.set(Calendar.SECOND, 0);
				Date date = cal.getTime();
				List<Field> fields = readStructure(ele.getElementsByTagName("structure"));
				ApplicationContext.addInputFile(new InputFile(fileName, date, fields));
			}
		}
	}
	public static List<Field> readStructure(NodeList nl) {
		List<Field> fields = new ArrayList<>();
		if(nl != null && nl.getLength() > 0) {
			for(int i=0; i<nl.getLength(); i++) {
				Element ele = (Element) nl.item(i);
				for(int j=0; j<ele.getChildNodes().getLength(); j++) {
					fields.add(readField(ele.getChildNodes().item(j)));
				}
			}
		}
		return fields;
	}
	public static Field readField(Node node) {
		String name = null;
		String type = null;
		if(node != null && node.getNodeType() == Node.ELEMENT_NODE) {
			for(int i=0; i<node.getAttributes().getLength(); i++) {
				if(node.getAttributes().item(i).getNodeName().equals("name")) {
					name = node.getAttributes().item(i).getNodeValue();
				} else if(node.getAttributes().item(i).getNodeName().equals("type")) {
					type = node.getAttributes().item(i).getNodeValue();
				}
			}
		}
		return new Field(name, type);
	}
	public static void readOutputFiles(NodeList nl) {
		if(nl != null && nl.getLength() > 0) {
			for(int i=0; i<nl.getLength(); i++) {
				Element ele = (Element) nl.item(i);
				String fileName = ele.getAttribute("name");
				String time = ele.getAttribute("time");
				String[] timeSplit = time.split(":");
				int minute = Integer.parseInt(timeSplit[1]);
				int hour = Integer.parseInt(timeSplit[0]);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, hour);
				cal.set(Calendar.MINUTE, minute);
				cal.set(Calendar.SECOND, 0);
				Date date = cal.getTime();
				List<String> dependencies = new ArrayList<>();
				for(int j=0; j<ele.getChildNodes().getLength(); j++) {
					dependencies.add(readDependency(ele.getChildNodes().item(j)));
				}
				ApplicationContext.addOutputFile(new OutputFile(fileName, date, dependencies));
			}
		}
	}
	public static String readDependency(Node node) {
		String dependency = null;
		if(node != null && node.getNodeType() == Node.ELEMENT_NODE) {
			for(int i=0; i<node.getAttributes().getLength(); i++) {
				if(node.getAttributes().item(i).getNodeName().equals("dependency")) {
					dependency = node.getAttributes().item(i).getNodeValue();
				}
			}
		}
		return dependency;
	}
}
