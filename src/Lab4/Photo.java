package Lab4;

import java.io.PrintStream;

public class Photo {
    private String url;

    Photo(String url){
        this.url =url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"CV Photo\" height=\"42\" width=\"42\"/>\n",url);
    }

}
