package Lab6;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import static org.junit.Assert.fail;


public class CSVReaderTest {


    //TODO
    @Test
    public void testReader() {
        CSVReader reader = null;
        try {
            reader = new CSVReader("titanic-part.csv", ",", true);
        } catch (FileNotFoundException e) {
            fail("File not found exception");
        } catch (IOException e) {
            fail("IO Exception");
        }


        while (reader.next()) {
            int id = reader.getInt("PassengerId");
            String name = reader.get("Name");
            double fare = reader.getDouble("Fare");

            System.out.printf(Locale.US, "%d %s %f", id, name, fare);
        }


    }
}