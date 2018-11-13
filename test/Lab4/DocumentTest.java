package Lab4;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DocumentTest {

    @Test
    public void testWriteHTML() {
        String photoUrl = "abc.jpg";


        Document cv = new Document("Jeż Jerzy - CV");
        cv.addPhoto(photoUrl);
        cv.addSection("Wykształcenie", 2)
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności", 2)
                .addParagraph(
                        new ParagraphWithList()
                                .setContent("Umiejętności")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        cv.writeHTML(printStream);

        String result;

        result = outputStream.toString();

        assertTrue(result.contains("Jeż Jerzy"));
        assertTrue(result.contains("<html>"));
        assertTrue(result.contains("src=\""+photoUrl+"\""));
        assertTrue(result.contains("<ul>\n\t\t<li>C</li>"));

    }
}