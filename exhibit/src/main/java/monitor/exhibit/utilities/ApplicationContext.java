package monitor.exhibit.utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import monitor.exhibit.beans.InputFile;
import monitor.exhibit.beans.OutputFile;
import monitor.exhibit.beans.Record;

public class ApplicationContext {

	protected static List<InputFile> inputFiles;
	protected static List<OutputFile> outputFiles;
	protected static Map<String, Date> processedFiles;
	protected static Vector<Record> validRecords;
	protected static Vector<Record> invalidRecords;
	
	static {
		inputFiles = new ArrayList<>();
		outputFiles = new ArrayList<>();
		processedFiles = new HashMap<>();
		validRecords = new Vector<>();
		invalidRecords = new Vector<>();
	}
	
	private ApplicationContext() {
	}
	
	public static void addInputFile(InputFile inputFile) {
		inputFiles.add(inputFile);
	}
	
	public static List<InputFile> getInputFiles() {
		return inputFiles;
	}
	
	public static void addOutputFile(OutputFile outputFile) {
		outputFiles.add(outputFile);
	}
	
	public static List<OutputFile> getOutputFiles() {
		return outputFiles;
	}
	
	public static void addProcessedFile(String fileName, Date date) {
		processedFiles.put(fileName, date);
	}
	
	public static Date getDateFromProcessedFile(String fileName) {
		return processedFiles.get(fileName);
	}
	
	public static void addValidRecord(Record record) {
		validRecords.add(record);
	}
	
	public static Vector<Record> getValidRecords() {
		return validRecords;
	}
	
	public static void addInvalidRecord(Record record) {
		invalidRecords.add(record);
	}
	
	public static Vector<Record> getInvalidRecords() {
		return invalidRecords;
	}
	
}
