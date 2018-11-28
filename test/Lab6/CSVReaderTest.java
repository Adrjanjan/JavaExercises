package Lab6;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.function.Function;

import static org.junit.Assert.*;


public class CSVReaderTest {

    @Test
    public void testReaderFromFileAllValues() {
        CSVReader reader = null;
        try {
            reader = new CSVReader("accelerator.csv", ";", true);
        } catch (FileNotFoundException e) {
            fail("File not found exception");
        } catch (IOException e) {
            fail("IO Exception - can't open file!");
        }
        //X;Y;Z;longitude;latitude;speed;time;label

        while (reader.next()) {
            double X = reader.getDouble(0);
            double Y = reader.getDouble("Y");
            double Z = reader.getDouble("Z");
            double longitude = reader.getDouble("longitude");
            double latitude = reader.getDouble("latitude");
            double speed = reader.getDouble("speed");
            double time = reader.getDouble("time");
            double label = reader.getDouble("label");


            System.out.println(Arrays.toString(new double[]{X, Y, Z, longitude, latitude, speed, time, label}));
        }
    }

    @Test
    public void testFromStringSource() {
        String text = "a,b,c\n123.4,567.8,91011.12";
        CSVReader reader = null;
        try {
            reader = new CSVReader(new StringReader(text), ",", true);
        } catch (IOException e) {
            e.printStackTrace();
            fail("IO Exception - can~'t read from StringReader!");
        }

        reader.next();
        double a = reader.getDouble(0);
        double a2 = reader.getDouble("a");

        double b = reader.getDouble(1);
        double b2 = reader.getDouble("b");

        String c = reader.get(2);
        String c2 = reader.get("c");

        assertEquals(123.4, a, 0.1);
        assertEquals(123.4, a2, 0.1);

        assertEquals(567.8, b, 0.1);
        assertEquals(567.8, b2, 0.1);

        assertEquals("91011.12", c);
        assertEquals("91011.12", c2);
    }

    @Test
    public void testNonExistingCols() {
        String text = "a,c\n123.4,91011.12";
        CSVReader reader = null;
        try {
            reader = new CSVReader(new StringReader(text), ",", true);
        } catch (IOException e) {
            e.printStackTrace();
            fail("IO Exception - can~'t read from StringReader!");
        }
        boolean first_failed = false;
        boolean second_failed = false;
        reader.next();
        try {
            double a = reader.getDouble("b");
        } catch (NullPointerException e) {
            first_failed = true;
        }

        try {
            double a2 = reader.getDouble(2);
        } catch (ArrayIndexOutOfBoundsException e) {
            second_failed = true;
        }
        if (first_failed && second_failed) return;

        fail();
    }

    @Test
    public void testMissingData() {
        String text = "a,b,c\n123.4,,91011.12";
        CSVReader reader = null;
        try {
            reader = new CSVReader(new StringReader(text), ",", true);
        } catch (IOException e) {
            e.printStackTrace();
            fail("IO Exception - can~'t read from StringReader!");
        }
        Double b;
        reader.next();

        if (!reader.isMissing("b")) {
            b = reader.getDouble("b");
        } else {
            b = null;
        }
        assertNull(b);
    }

    @Test
    public void testReturnedTypes() {
        String text = "123,999.99,12:45,1998-05-14,znaki,\"Jakiś,String,z,przecinkami,w,cudzysłowach\",";
        CSVReader reader = null;

        try {
            reader = new CSVReader(new StringReader(text), ",", false);
        } catch (IOException e) {
            e.printStackTrace();
            fail("IO Exception - can~'t read from StringReader!");
        }

        int first = 0;
        double second = 0;
        LocalTime third = null;
        LocalDate fourth = null;
        String fifth = null;
        String sixth = null;

        reader.next();
        for (int i = 0; i < reader.getRecordLength(); ++i) {
            if (!reader.isMissing(i)) {
                switch (i) {
                    case 0:
                        first = reader.getInt(i);
                        break;
                    case 1:
                        second = reader.getDouble(i);
                        break;
                    case 2:
                        third = reader.getTime(i);
                        break;
                    case 3:
                        fourth = reader.getDate(i);
                        break;
                    case 4:
                        fifth = reader.get(i);
                        break;
                    case 5:
                        sixth = reader.get(i);
                        break;
                }
            }
        }

        assertEquals(123, first);
        assertEquals(999.99, second, 0.001);
        assertNotNull(third);
        assertEquals("12:45", third.toString());
        assertNotNull(fourth);
        assertEquals("1998-05-14", fourth.toString());
        assertNotNull(fifth);
        assertEquals("znaki", fifth);
        assertNotNull(sixth);
        assertEquals("\"Jakiś,String,z,przecinkami,w,cudzysłowach\"", sixth);
    }

    private CSVReader reader;

    @Test
    public void titanicTest() {
        class Passenger {
            private Integer PassengerId, Survived, Pclass;
            private String Name, Sex;
            private Integer Age, SibSp, Parch;
            private String Ticket;
            private Double Fare;
            private String Cabin, Embarked;

            Passenger(Integer passengerId, Integer survived, Integer pclass, String name, String sex, Integer age, Integer sibSp, Integer parch, String ticket, Double fare, String cabin, String embarked) {
                PassengerId = passengerId;
                Survived = survived;
                Pclass = pclass;
                Name = name;
                Sex = sex;
                Age = age;
                SibSp = sibSp;
                Parch = parch;
                Ticket = ticket;
                Fare = fare;
                Cabin = cabin;
                Embarked = embarked;
            }

            @Override
            public String toString() {
                return "Pasenger{" +
                        "PassengerId=" + PassengerId +
                        ", Survived=" + Survived +
                        ", Pclass=" + Pclass +
                        ", Name='" + Name + '\'' +
                        ", Sex='" + Sex + '\'' +
                        ", Age=" + Age +
                        ", SibSp=" + SibSp +
                        ", Parch=" + Parch +
                        ", Ticket='" + Ticket + '\'' +
                        ", Fare=" + Fare +
                        ", Cabin='" + Cabin + '\'' +
                        ", Embarked='" + Embarked + '\'' +
                        '}';
            }
        }

        reader = null;
        try {
            reader = new CSVReader("titanic-part.csv", ",", true);
        } catch (IOException e) {
            e.printStackTrace();
            fail("IO Exception - can't open file!");
        }
        Passenger passenger;

        while (reader.next()) {
            passenger = new Passenger(getIfOk(reader::getInt, 0),
                    getIfOk(reader::getInt, 1),
                    getIfOk(reader::getInt, 2),
                    getIfOk(reader::get, 3),
                    getIfOk(reader::get, 4),
                    getIfOk(reader::getInt, 5),
                    getIfOk(reader::getInt, 6),
                    getIfOk(reader::getInt, 7),
                    getIfOk(reader::get, 8),
                    getIfOk(reader::getDouble, 9),
                    getIfOk(reader::get, 10),
                    getIfOk(reader::get, 11));
            System.out.println(passenger);
        }
    }

    private <T> T getIfOk(Function<Integer, T> function, int index) {
        if (reader.isMissing(index))
            return null;
        return function.apply(index);
    }
}