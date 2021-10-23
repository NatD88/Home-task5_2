package ru.dataart.academy.java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipInputStream;

public class Calculator {
    /**
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */

    public Integer getNumberOfChar(String zipFilePath, char character) {
        //Task implementation
        int count = 0;
        try (ZipInputStream stream = new ZipInputStream(new FileInputStream(zipFilePath));
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            while (stream.getNextEntry() != null) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == character)
                            count++;
                    }
                }
                stream.closeEntry();
            }
        } catch (IOException e) {
            System.out.println(zipFilePath + " - incorrect path!!");
        }
        return count;
    }
    /**
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */

    public Integer getMaxWordLength(String zipFilePath) {
        //Task implementation
        int maxLen = 0;
        try (ZipInputStream stream = new ZipInputStream(new FileInputStream(zipFilePath));
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            while (stream.getNextEntry() != null) {
                String line;
                int lineLen = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    lineLen = getMaxWordLengthInLine(line);
                    if (lineLen > maxLen)
                        maxLen = lineLen;
                }
                stream.closeEntry();
            }
        } catch (IOException e) {
            System.out.println(zipFilePath + " - incorrect path!!");
        }
        return maxLen;
    }

    public Integer getMaxWordLengthInLine(String line) {
        String[] words = line.split(" ");
        String word = Arrays.stream(words)
                .max(Comparator.comparingInt(String::length))
                .get();
        return word.length();
    }
}
