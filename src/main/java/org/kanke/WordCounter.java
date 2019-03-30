package org.kanke;

import lombok.extern.slf4j.Slf4j;
import org.kanke.services.WordFile;
import org.kanke.services.WordFileFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.Scanner;

@Slf4j
public class WordCounter {

    public static void main(String[] args) {

        System.out.println("\n\n***** ^_^ Welcome to Clever org.kanke.services.WordFile Counter ^_^ ******\n");

        System.out.println("Kindly enter a wordFile path below to continue #^_^# :");

        WordFileFactory wordFileFactory = new WordFileFactory();
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        printProgramOutput(wordFile);

    }

    static void printProgramOutput(WordFile wordFile) {
        try {
            wordFile.countWords().entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .forEachOrdered(wordEntry -> System.out.println("\n" + wordEntry.getKey() + ": " + wordEntry.getValue()));
        } catch (NoSuchFileException ex) {

            //add logger to file
            log.error(String.valueOf(ex));
            System.out.println("\nDoes this file exist? please enter a valid path and try again -_-\n");
        } catch (FileNotFoundException ex) {

            //add logger to file
            log.error(String.valueOf(ex));
            System.out.println("\nCan't find file, please enter a valid path and try again -_-\n");
        } catch (IOException ex) {

            //add logger to file
            log.error(String.valueOf(ex));
            System.out.println("\nProblems reading from file, the error has been logged. please check file and try again later -_-\n");
        } catch (IllegalArgumentException ex) {

            //add logger to file
            log.error(String.valueOf(ex));
            System.out.println("Unsupported file type -_- ");
        }
    }
}

