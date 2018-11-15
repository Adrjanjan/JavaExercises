package Lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    private String title;
    private List<Paragraph> paragraphs = new ArrayList<>();
    private int h_lvl =1;

    public Section(String title, int h_lvl) {
        this.title = title;
        this.h_lvl = h_lvl;
    }

    public Section addParagraph(Paragraph paragraph) {
        this.paragraphs.add(paragraph);
        return this;
    }

    public Section addParagraph(String paragraphText){
        paragraphs.add(new Paragraph(paragraphText));
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("\t<h%d>%s</h%d>\n\t\t<div class=\"section\">\n", h_lvl, title, h_lvl);
        for (Paragraph item : paragraphs) {
            out.print("\t\t\t");
            item.writeHTML(out);
            out.print("\t\t\t<br/>\n");
        }
        out.print("\t\t</div>\n");
    }

}
