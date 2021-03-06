package Lab4;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{
    private UnorderedList list = new UnorderedList();

    public ParagraphWithList() {
        super();
    }

    @Override
    public ParagraphWithList setContent(String content) {
        super.setContent(content);
        return this;
    }

    public ParagraphWithList addListItem(String item){
        list.addItem(new ListItem(item));
        return this;
    }

    public Paragraph addListItem(ListItem item){
        list.addItem(item);
        return this;
    }

    public void writeHTML(PrintStream out){
        out.print("\t\t<br />\n");
        list.writeHTML(out);
        out.print("\t\t\n");
    }

}
