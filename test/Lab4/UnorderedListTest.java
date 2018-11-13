package Lab4;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class UnorderedListTest {

    @Test
    public void testWriteHTML() {

        UnorderedList ul = new UnorderedList().addItem("Kot").addItem("Pies").addItem("Żaba") ;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        ul.writeHTML(printStream);

        String result;

        result = outputStream.toString();

        assertTrue(result.contains("Kot"));
        assertTrue(result.contains("<li>Pies</li>"));
        assertTrue(result.contains("Żaba"));
        assertTrue(result.contains("<ul>\n\t\t<li>Kot</li>\n\t\t<li>Pies</li>\n\t\t<li>Żaba</li>"));
    }
}