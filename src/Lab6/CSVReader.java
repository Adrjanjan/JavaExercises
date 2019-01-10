package Lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CSVReader {
    private BufferedReader reader;
    private String delimiter;
    private boolean hasHeader;
    private List<String> columnLabels = new ArrayList<>();
    private Map<String, Integer> columnLabelsToInt = new HashMap<>();
    private String[] current;


    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        this.hasHeader = hasHeader;
        if (hasHeader) {
            parseHeader();
        }
    }

    CSVReader(String filename, boolean hasHeader) throws IOException {
        this(filename, ",", hasHeader);
    }

    CSVReader(String filename, String delimiter) throws IOException {
        this(filename, delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(filename, ",", true);
    }

    CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        if (hasHeader) {
            parseHeader();
        }
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

    public boolean next() {
        String line;
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

    public int getInt(String colname) {
        return Integer.parseInt(current[columnLabelsToInt.get(colname)]);
    }

    public int getInt(int columnIndex) {
        if (columnIndex > getRecordLength()) throw new NullPointerException();
        return Integer.parseInt(current[columnIndex]);
    }

    public String get(String colname) {
        return current[columnLabelsToInt.get(colname)];
    }

    public String get(int columnIndex) {
        if (columnIndex > getRecordLength()) throw new NullPointerException();
        return current[columnIndex];
    }

    public double getDouble(String colname) {
        return Double.parseDouble(current[columnLabelsToInt.get(colname)]);
    }

    public double getDouble(int columnIndex) {
        if (columnIndex > getRecordLength()) throw new NullPointerException();
        return Double.parseDouble(current[columnIndex]);
    }

    long getLong(String colname) {
        return Long.parseLong(current[columnLabelsToInt.get(colname)]);
    }

    public long getLong(int columnIndex) {
        if (columnIndex > getRecordLength()) throw new NullPointerException();
        return Long.parseLong(current[columnIndex]);
    }

    List<String> getColumnLabels() {
        return new ArrayList<>(columnLabels);
    }

    int getRecordLength() {
        return current.length;
    }

    public boolean isMissing(int columnIndex) {
        return columnIndex >= current.length || current[columnIndex].isEmpty();
    }

    public boolean isMissing(String columnLabel) {
        return isMissing(columnLabelsToInt.get(columnLabel));
    }

    LocalTime getTime(String colname) {
        return getTime(columnLabelsToInt.get(colname));
    }

    LocalTime getTime(int columnIndex) {
        if (columnIndex > getRecordLength()) throw new NullPointerException();
        return LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern("HH:mm"));
    }

    LocalDate getDate(String colname) {
        return getDate(columnLabelsToInt.get(colname));
    }

    LocalDate getDate(int columnIndex) {
        if (columnIndex > getRecordLength()) throw new NullPointerException();
        return LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    boolean isHasHeader() {
        return hasHeader;
    }

    public <T> T getIfOk(Function<Integer, T> function, int index) {
        if (this.isMissing(index))
            return null;
        return function.apply(index);
    }
}