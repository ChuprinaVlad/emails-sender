package com.github.ChuprinaVlad;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class UserDataCsvParser {

    public List<UserData> parse() {
        URL fileCsv = getClass().getClassLoader().getResource("emails.csv");
        // TODO: write CSV parsing logic - https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
        //  and remove this return statement
        try (CSVReader reader = new CSVReader(new FileReader(String.valueOf(fileCsv)))) {
            List<String[]> r = null;
            try {
                r = reader.readAll();
            } catch (CsvException e) { //error reading file
                throw new RuntimeException(e);//failed validator
            }
            r.forEach(x -> Arrays.toString(x));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // FileReader
        } catch (IOException e) {
            throw new RuntimeException(e); // FileReader
        }

        return null;
    }
}
