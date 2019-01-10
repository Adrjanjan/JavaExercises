package Kolokwium;

import Lab6.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Zad1 {

    public static void main(String args[]) {

        CSVReader reader = null;
        try {
            reader = new CSVReader("ibuk_wykaz_pozycji.csv", ";", true);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        a) Wczytaj dane z pliku do pamięci
//
//        b) Podaj ile książek wydano w kolejnych latach (policz książki z kolejnych lat i wypisz)
//
//        c) Wypisz wszystkie książki wydane przez Wydawnictwo Naukowe PWN
//
//        d) Podaj ile książek należy do każdej z kategorii

        Map<Integer, Integer> yearToNumOfBooks = new HashMap<>();
        List<String> booksByPWN = new ArrayList<>();
        Map<String, Integer> numOfBooksOfCategory = new HashMap<>();


        while (reader.next()) {
            int rokWydania = reader.getInt("Rok wydania");
            if (!yearToNumOfBooks.containsKey(rokWydania)) {
                yearToNumOfBooks.put(rokWydania, 1);
            } else {
                yearToNumOfBooks.put(rokWydania, yearToNumOfBooks.get(rokWydania) + 1);
            }

            String wydawnictwo = reader.get("Wydawnictwo");
            if (wydawnictwo.equals("Wydawnictwo Naukowe PWN")) {
                booksByPWN.add(reader.get("Tytuł"));
            }

            String category = reader.get("Kategoria");
            if (!numOfBooksOfCategory.containsKey(category)) {
                numOfBooksOfCategory.put(category, 1);
            } else {
                numOfBooksOfCategory.put(category, numOfBooksOfCategory.get(category) + 1);
            }
        }
        System.out.println("------Rok -> Ile książek-------------------------------------------------------------");
        for (Integer year : yearToNumOfBooks.keySet()) {
            System.out.println(year + " " + yearToNumOfBooks.get(year));
        }

        System.out.println("------Ksiażki od PWN-------------------------------------------------------------");
        booksByPWN.forEach(System.out::println);

        System.out.println("------Kategoria -> Ile ksiażek-------------------------------------------------------------");
        for (String category : numOfBooksOfCategory.keySet()) {
            System.out.println(category + " " + numOfBooksOfCategory.get(category));
        }
    }
}