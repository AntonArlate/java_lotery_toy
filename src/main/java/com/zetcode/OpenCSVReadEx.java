package com.zetcode;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OpenCSVReadEx {

    public static void main(String[] args) throws IOException,
            CsvException {

        var fileName = "src/main/resources/toys.csv";
        Path myPath = Paths.get(fileName);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (var br = Files.newBufferedReader(myPath,  StandardCharsets.UTF_8);
             var reader = new CSVReaderBuilder(br).withCSVParser(parser)
                     .build()) {

            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {

                for (String e : row) {

                    System.out.format("%s ", e);
                }

                System.out.println();
            }
        }

    }
}