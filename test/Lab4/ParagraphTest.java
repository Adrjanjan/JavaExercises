package Lab4;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ParagraphTest {

    @Test
    public void testWriteHTML() {

        Paragraph p = new Paragraph("First_Skills").setContent("New_Skills");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        p.writeHTML(ps);

        String result = os.toString();

        assertTrue(result.contains("New_Skills"));
        assertTrue(result.contains("<div>"));
        assertTrue(result.contains("</div>"));

    }
}