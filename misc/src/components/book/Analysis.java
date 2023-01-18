package components.book; // Remove this

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Analysis {

    public static void main(String[] args) {
        /*
         * Get file name. Create book objects and save them to books. Catching
         * file not found.
         */
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter a filename: ");
        String fileName = scnr.nextLine();
        List<Book> books = new ArrayList<Book>();
        try {
            File textFile = new File(fileName);
            Scanner textScanner = new Scanner(textFile);
            String line = textScanner.nextLine();
            while (textScanner.hasNext()) {
                line = textScanner.nextLine();
                String[] lineArray = line.split(",");
                Book book = new Book(lineArray);
                books.add(book);
            }
            textScanner.close();

            printHistogram(books);
            printSummary(books);

        } catch (FileNotFoundException e) {
            System.out.println("ERROR - File " + fileName + " not found");
        }
        scnr.close();
    }

    private static void printHistogram(List<Book> books) {
        System.out.println("Histogram of Amazon BestSeller Ratings");
        System.out.println("--------------------------------------");
        List<Double> ratingsList = new ArrayList<Double>();
        ratingsList.add(null);

        double currentRating = Math.round(getStartRating(books) * 100.0)
                / 100.0;

        while (currentRating <= 5.0) {
            System.out.print(currentRating + " ");
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                if (book.userRating() == currentRating) {
                    System.out.print("*");
                }
            }
            currentRating = Math.round((currentRating + 0.1) * 100.0) / 100.0;
            System.out.println();
        }
        System.out.println("--------------------------------------");
    }

    private static void printSummary(List<Book> books) {
        System.out.println("Total books rated: " + books.size());
        System.out.println("Median score: " + getMedian(books));
        System.out.println("Average score: " + getAverage(books));
        System.out
                .println("Standard deviation: " + getStandardDeviation(books));
    }

    private static double getStartRating(List<Book> books) {
        double result = 5.0;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.userRating() < result) {
                result = book.userRating();
            }
        }
        return result - 0.1;
    }

    private static double getMedian(List<Book> books) {
        double result = 0.0;
        List<Double> scores = new ArrayList<Double>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            scores.add(book.userRating());
        }
        Collections.sort(scores);
        if (scores.size() % 2 == 0) {
            result = (scores.get((scores.size() / 2))
                    + scores.get((scores.size() / 2) + 1)) / 2;
        } else {
            result = scores.get((scores.size() / 2) + 1);
        }
        return result;
    }

    private static double getAverage(List<Book> books) {
        double result = 0.0;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            result += book.userRating();
        }
        return result / books.size();
    }

    private static double getStandardDeviation(List<Book> books) {
        double result = 0.0;
        double summation = 0.0;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            summation += (book.userRating() - getAverage(books))
                    * (book.userRating() - getAverage(books));
        }
        result = summation / books.size();
        result = Math.sqrt(result);
        return result;
    }

}
