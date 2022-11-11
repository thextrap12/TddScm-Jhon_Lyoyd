package com.example.FizzBuzzTDD.repository;

import com.example.FizzBuzzTDD.model.Employee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryFromCSV implements EmployeeRespository {


    @Override
    public List<Employee> getAllEmployees() throws Exception {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream is = classLoader.getResourceAsStream("datastore.csv");

            List<List<String>> csv = parseCsv(is, ',');


            return csv.stream().skip(1).map(csvRow -> new Employee(csvRow.get(0),
                    Integer.parseInt(csvRow.get(1)),
                    Double.valueOf(csvRow.get(2)),
                    csvRow.get(3))).toList();


        } catch (Exception e) {
            throw new Exception("Data not found");
        }


    }


    public static List<List<String>> parseCsv(InputStream csvInput, char csvSeparator) {

        // Prepare.
        BufferedReader csvReader = null;
        List<List<String>> csvList = new ArrayList<List<String>>();
        String csvRecord = null;

        // Process records.
        try {
            csvReader = new BufferedReader(new InputStreamReader(csvInput, StandardCharsets.UTF_8));
            while ((csvRecord = csvReader.readLine()) != null) {
                csvList.add(parseCsvRecord(csvRecord, csvSeparator));
            }
        } catch (IOException e) {
            throw new RuntimeException("Reading CSV failed.", e);
        } finally {
            if (csvReader != null)
                try {
                    csvReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return csvList;
    }

    private static List<String> parseCsvRecord(String record, char csvSeparator) {

        // Prepare.
        boolean quoted = false;
        StringBuilder fieldBuilder = new StringBuilder();
        List<String> fields = new ArrayList<String>();

        // Process fields.
        for (int i = 0; i < record.length(); i++) {
            char c = record.charAt(i);
            fieldBuilder.append(c);

            if (c == '"') {
                quoted = !quoted; // Detect nested quotes.
            }

            if ((!quoted && c == csvSeparator) // The separator ..
                    || i + 1 == record.length()) // .. or, the end of record.
            {
                String field = fieldBuilder.toString() // Obtain the field, ..
                        .replaceAll(csvSeparator + "$", "") // .. trim ending separator, ..
                        .replaceAll("^\"|\"$", "") // .. trim surrounding quotes, ..
                        .replace("\"\"", "\""); // .. and un-escape quotes.
                fields.add(field.trim()); // Add field to List.
                fieldBuilder = new StringBuilder(); // Reset.
            }
        }

        return fields;
    }

}
