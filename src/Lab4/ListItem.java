package Lab4;

import java.io.PrintStream;

public class ListItem {
    private String content;

    ListItem(String content) {
        this.content = content;
    }

    public void writeHTML(PrintStream out){
        out.printf("<li>%s</li>\n", content);
    }


}
