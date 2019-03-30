package org.kanke;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVWordFile implements WordFile {

    private String fileName;

    CSVWordFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Map<String, Long> countWords() throws IOException {
        Map<String, Long> wordMap = new HashMap<>();

        CSVReader reader = new CSVReader(new FileReader(fileName));
            String[] nextLine;

            if (reader.readNext() == null) {
                System.out.println("\nSorry! this csv file is empty O_O \n");
            }
            while ((nextLine = reader.readNext()) != null) {
                    for (String csvLine : nextLine) {
                        String[] words = csvLine.split(",");
                        for (String word : words) {
                            if (wordMap.containsKey(word)) {
                                wordMap.put(word, wordMap.get(word) + 1);
                            } else {
                                wordMap.put(word, 1l);
                            }
                        }
                    }

            }

        return wordMap;
    }
}
