package com.example.demo.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Service
public class CsvReaderService {

	public List<String[]> readCsv(String filePath) throws IOException, CsvException {
		CSVReader reader = new CSVReader(new FileReader(filePath));
		List<String[]> csvData = reader.readAll();
		reader.close();
		return csvData;
	}
}