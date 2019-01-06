package Lab11;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloaderExample {

    private static String[] toDownload = {
            "http://home.agh.edu.pl/pszwed/wyklad-c/01-jezyk-c-intro.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/02-jezyk-c-podstawy-skladni.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/03-jezyk-c-instrukcje.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/04-jezyk-c-funkcje.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/05-jezyk-c-deklaracje-typy.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/06-jezyk-c-wskazniki.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/07-jezyk-c-operatory.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/08-jezyk-c-lancuchy-znakow.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/09-jezyk-c-struktura-programow.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/10-jezyk-c-dynamiczna-alokacja-pamieci.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/11-jezyk-c-biblioteka-we-wy.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/preprocesor-make-funkcje-biblioteczne.pdf",
    };

    //    private static int counter = 0;
    private static AtomicInteger counter = new AtomicInteger(0);
    private static Semaphore sem = new Semaphore(0);

    public static void main(String[] args) {
        DownloaderExample downloader = new DownloaderExample();
        sequentialDownloader();
        try {
            concurrentDownload();
            concurrentDownload2();
            concurrentDownload3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void sequentialDownloader() {
        double start = System.nanoTime() / 1e9;
        for (String url : toDownload) {
            new Downloader(url).run();
        }
        double end = System.nanoTime() / 1e9;
        System.out.printf(Locale.US, "Time of 1st processing: %fs\n", end - start);
    }

    static void concurrentDownload() {
        double start = System.nanoTime() / 1e9;
        for (String url : toDownload) {
            new Thread(new Downloader(url)).start();
        }
        double end = System.nanoTime() / 1e9;
        System.out.printf(Locale.US, "Time 2nd of processing: %fs\n", end - start);
    }
    // Wypisywany czas nie jest poprawny, poniewaz wypisany czas jest to czas dotarcia tam watku glownego, choc wspolbiezne watki sie jeszcze nie zakonczyly


    static void concurrentDownload2() {

        double start = System.nanoTime() / 1e9;
        for (String url : toDownload) {
            new Thread(new Downloader(url)).start();
        }
        while (counter.get() != toDownload.length) {
            try {
                Thread.sleep(10);
                Thread.yield();
            } catch (InterruptedException ignored) {
            }
        }
        double end = System.nanoTime() / 1e9;
        System.out.printf(Locale.US, "Time of 3rd processing: %fs\n", end - start);
    }

    static void concurrentDownload3() throws InterruptedException {
        double start = System.nanoTime() / 1e9;

        for (String url : toDownload) {
            new Thread(new Downloader(url)).start();

        }
        sem.acquire(toDownload.length);
        double end = System.nanoTime() / 1e9;
        System.out.printf(Locale.US, "Time of 4th processing: %fs\n", end - start);
    }


    private static class Downloader implements Runnable {
        private final String url;

        Downloader(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            Pattern pattern = Pattern.compile(".*c/(.*)");
            Matcher matcher = pattern.matcher(url);
            matcher.matches();
            String filename = matcher.group(1);

            try (InputStream in = new URL(url).openStream(); FileOutputStream out = new FileOutputStream(filename)) {

//                int aByte;
//                while ((aByte = in.read()) != -1) {   //1st read
//                    out.write(aByte);     //1st write
//                }
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                counter.incrementAndGet();
                sem.release();
                System.out.println("Done " + filename);

            } catch (FileNotFoundException e) {
                System.err.printf("Unable to open file '%1$s': %2$s",
                        url, e.getMessage());
                e.printStackTrace();
            } catch (MalformedURLException e) {
                System.err.printf("Not correct URL '%1$s': %2$s",
                        url, e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
