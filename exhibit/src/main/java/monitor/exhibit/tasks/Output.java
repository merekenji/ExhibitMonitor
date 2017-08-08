package monitor.exhibit.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

import monitor.exhibit.beans.OutputFile;
import monitor.exhibit.beans.Record;
import monitor.exhibit.dao.RecordDAO;

public class Output extends TimerTask {

	RecordDAO recordDAO;
	OutputFile outputFile;
	
	public Output(RecordDAO recordDAO, OutputFile outputFile) {
		this.recordDAO = recordDAO;
		this.outputFile = outputFile;
	}
	
	@Override
	public void run() {
		File file = new File("src/test/resources/archive/" + outputFile.getName());
		int count = 0;
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			for(String fileName : outputFile.getDependencies()) {
				List<Record> records = recordDAO.getValidRecord(fileName);
				for(Record record : records) {
					bw.write(record.getRecord());
					bw.newLine();
					count++;
				}
			    //new File("src/test/resources/process/" + fileName).delete();
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
				if(fw != null) {
					fw.close();
				}
				if(count == 0) {
					file.delete();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}