package Lab4;

public class CV {

    public static void main(String[] args) {

        Document cv = new Document("Jan Kowalski - CV");
        cv.addPhoto("...");
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

        cv.writeHTML(System.out);

        Document cv2 = new Document();
        cv2.addPhoto("abc.jpg");
        cv2.writeHTML(System.out);

    }
}



