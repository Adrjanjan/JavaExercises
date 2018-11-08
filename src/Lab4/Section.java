package Lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    private String title;
    private List<Paragraph> paragraphs = new ArrayList<>();

    public Section(String title) {
        this.title = title;
    }

    public Section addParagraph(Paragraph paragraph) {
        this.paragraphs.add(paragraph);
        return this;
    }

    public Section addParagraph(String paragraphText){
        paragraphs.add(new Paragraph(paragraphText));
        return this;
    }

    //TODO przekazywać poziom zagłębienia do znaczników <h_>
    void writeHTML(PrintStream out){
        out.print("<h2>\n");
        for (Paragraph item : paragraphs) {
            item.writeHTML(out);
            out.print("<br />\n");
        }
        out.print("</h2>\n");
    }

}
