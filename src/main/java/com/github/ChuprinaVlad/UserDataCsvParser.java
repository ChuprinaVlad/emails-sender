package com.github.ChuprinaVlad;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

public class UserDataCsvParser {


    public List<UserData> parse() {
        var fileCsv = getClass().getClassLoader().getResource("emails.csv");
        // TODO: write CSV parsing logic - https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
        //  and remove this return statement
        File file = null;
        try {
            file = Paths.get(fileCsv.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            return new CsvToBeanBuilder(new FileReader(file))
                    .withType(UserData.class)
                    .withSkipLines(1)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("errors related to accessing the file",e);
        }
    }
}
