package Lab4;

import java.io.PrintStream;

public class Paragraph {
    private String content;

    Paragraph(String paragraphText) {
        this.content = paragraphText;
    }

    public Paragraph() {}

    public Paragraph setContent(String content) {
        this.content = content;
        return this;
    }

    //TODO
    public void writeHTML(PrintStream out){
        out.printf("<div>%s</div>\n", content);
    }

}
