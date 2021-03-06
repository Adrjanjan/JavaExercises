package Lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    private String title;
    private Photo photo;
    private List<Section> sections = new ArrayList<>();

    public Document() {
    }

    public Document(String title) {
        this.title = title;
    }

    public Document(String title, Photo photo, List<Section> sections) {
        this.title = title;
        this.photo = photo;
        this.sections = sections;
    }

    public String getTitle() {
        return title;
    }

    public Document setTitle(String title) {
        this.title = title;
        return this;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Document addPhoto(Photo photo) {
        this.photo = photo;
        return this;
    }

    public void addPhoto(String photo) {
        this.photo = new Photo(photo);
    }

    public List<Section> getSections() {
        return sections;
    }

    public Document setSections(List<Section> sections) {
        this.sections = sections;
        return this;
    }

    public Document addSection(Section section){
        sections.add(section);
        return this;
    }

    public Section addSection(String sectionTitle, int h_lvl){
        Section section = new Section(sectionTitle, h_lvl);
        sections.add(section);
        return section;
    }

    void writeHTML(PrintStream out){
        out.print("<html>\n\t<head>\n");
        out.printf("\t\t<title>%s</title>\n", title);
        out.println("\t</head>\n\t<body>");
        out.printf("\t\t<h1>%s</h1>\n\t\t<div class=\"face\">\n\t\t\t", title);
        photo.writeHTML(out);
        out.print("\t\t</div>\n");
        for (Section sect:sections) {
            out.print('\t');
            sect.writeHTML(out);
        }
        out.print("\t</body>\n</html>\n");
    }

}
