package Lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();
    String[] current;


    CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) {
            parseHeader();
        }
    }

    public CSVReader(String filename, boolean hasHeader) throws IOException {
        this(filename, ",", hasHeader);
    }

    public CSVReader(String filename, String delimiter) throws IOException {
        this(filename, delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(filename, ",", true);
    }

    private void parseHeader() throws IOException {
        String line = reader.readLine();

        if (line == null)
            return;

        String[] header = line.split(delimiter);
        for (int i = 0; i < header.length; ++i) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }

    boolean next() {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            return false;
        }

        if (line == null || line.isEmpty())
            return false;

        current = line.split(delimiter);
        return true;
    }

    int getInt(String colname) {
        return Integer.parseInt(current[columnLabelsToInt.get(colname)]);
    }

    public int getInt(int columnIndex) {
        return Integer.parseInt(current[columnIndex]);
    }

    String get(String colname) {
        return current[columnLabelsToInt.get(colname)];
    }

    String get(int columnIndex) {
        return current[columnIndex];
    }

    double getDouble(String colname) {
        return Double.parseDouble(current[columnLabelsToInt.get(colname)]);
    }

    double getDouble(int columnIndex) {
        return Double.parseDouble(current[columnIndex]);
    }

    long getLong(String colname) {
        return Long.parseLong(current[columnLabelsToInt.get(colname)]);
    }

    long getLong(int columnIndex) {
        return Long.parseLong(current[columnIndex]);
    }

    List<String> getColumnLabels() {
        return new ArrayList<>(columnLabels);
    }

    int getRecordLength() {
        return current.length;
    }

    boolean isMissing(int columnIndex) {
        return current[columnIndex].isEmpty();
    }

    boolean isMissing(String columnLabel) {
        return isMissing(columnLabelsToInt.get(columnLabel));
    }

    //TODO Testy + Cudzysłowy + getTime + getDate
    //

}
