package monitor.exhibit.utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import monitor.exhibit.beans.InputFile;
import monitor.exhibit.beans.OutputFile;

public class ApplicationContext {

	protected static List<InputFile> inputFiles;
	protected static List<OutputFile> outputFiles;
	protected static Map<String, Date> processedFiles;
	
	static {
		inputFiles = new ArrayList<>();
		outputFiles = new ArrayList<>();
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
	public static Date getDateFromProcessedFile(String fileName) {
		return processedFiles.get(fileName);
	}
}
