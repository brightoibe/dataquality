/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.datamigration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import liquibase.util.csv.opencsv.CSVWriter;

/**
 * @author The Bright
 */
public class FileManager {
	
	private CSVWriter csvWriter;
	
	public FileManager() {
		
	}
	
	public void initializeWriter(String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(new File(fileName));
		csvWriter = new CSVWriter(fileWriter);
	}
	
	public void writeData(List<String[]> dataArr) {
		csvWriter.writeAll(dataArr);
	}
	
	public void closeWriter() throws IOException {
		if (csvWriter != null) {
			csvWriter.close();
		}
	}
	
	public void writeAndClose(List<String[]> dataArr, String fileName) throws IOException {
		initializeWriter(fileName);
		writeData(dataArr);
		
		closeWriter();
	}
}
