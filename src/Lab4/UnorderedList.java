package Lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {

    private List<ListItem> items = new ArrayList<>();

    public UnorderedList() {}

    UnorderedList addItem(ListItem item){
        this.items.add(item);
        return this;
    }

    UnorderedList addItem(String item){
        this.items.add(new ListItem(item));
        return this;
    }

    public void writeHTML(PrintStream out){
        out.print("\t<ul>\n");
        for (ListItem item : items) {
            item.writeHTML(out);
        }
        out.print("\t</ul>");
    }

}
