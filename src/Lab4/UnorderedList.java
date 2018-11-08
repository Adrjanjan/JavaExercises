package Lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {

    private List<ListItem> items = new ArrayList<>();

    public UnorderedList() {}

    void addItem(ListItem item){
        this.items.add(item);
    }

    public void writeHTML(PrintStream out){
        out.print("<ul>\n");
        for (ListItem item : items) {
            item.writeHTML(out);
        }
        out.print("</ul>\n");
    }

}
