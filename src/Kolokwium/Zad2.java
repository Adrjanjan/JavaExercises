package Kolokwium;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


public class Zad2 {

    public static void main(String[] args) {
        String ad1Regex = "[\\s|\\r|\\,|\\.|\\-|\\!|\\—|\\?|\\;|\\:|\\”|\\„|\\…]+";
        String book = readFile("w-pustyni.txt", Charset.forName("cp1250"));
        String[] words = book.split(ad1Regex);

        Pair<String, Integer> result = mostFrequentWord(words);
        System.out.printf("Most frequent word is \"%s\": - %d times", result.getKey(), result.getValue());
    }

    static String readFile(String name, Charset charset) {
        StringBuilder s = new StringBuilder();
        try (BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(name), charset))) {
            for (; ; ) {
                int c = file.read();
                if (c < 0) break;
                s.append((char) c);
            }
            return s.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    static Pair<String, Integer> mostFrequentWord(String[] words) {

        Map<String, Integer> wordToNum = new HashMap<>();

        for (String word : words) {
            if (!wordToNum.containsKey(word)) {
                wordToNum.put(word, 1);
            } else {
                wordToNum.put(word, wordToNum.get(word) + 1);
            }
        }

        Integer max = 0;
        String mostFrequent = "";
        for (String word : words) {
            if (wordToNum.get(word) > max) {
                max = wordToNum.get(word);
                mostFrequent = word;
            }
        }

        return new Pair<>(mostFrequent, max);
    }
}
