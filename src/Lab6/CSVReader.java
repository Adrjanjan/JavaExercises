package Lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();
    String[] current;


    CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
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

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
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

    boolean next() {
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

    int getInt(String colname) {
        return Integer.parseInt(current[columnLabelsToInt.get(colname)]);
    }

    public int getInt(int columnIndex) {
        if (columnIndex > getRecordLength()) throw new NullPointerException();
        return Integer.parseInt(current[columnIndex]);
    }

    String get(String colname) {
        return current[columnLabelsToInt.get(colname)];
    }

    String get(int columnIndex) {
        if (columnIndex > getRecordLength()) throw new NullPointerException();
        return current[columnIndex];
    }

    double getDouble(String colname) {
        return Double.parseDouble(current[columnLabelsToInt.get(colname)]);
    }

    double getDouble(int columnIndex) {
        if (columnIndex > getRecordLength()) throw new NullPointerException();
        return Double.parseDouble(current[columnIndex]);
    }

    long getLong(String colname) {
        return Long.parseLong(current[columnLabelsToInt.get(colname)]);
    }

    long getLong(int columnIndex) {
        if (columnIndex > getRecordLength()) throw new NullPointerException();
        return Long.parseLong(current[columnIndex]);
    }

    List<String> getColumnLabels() {
        return new ArrayList<>(columnLabels);
    }

    int getRecordLength() {
        return current.length;
    }

    boolean isMissing(int columnIndex) {
        return columnIndex >= current.length || current[columnIndex].isEmpty();
    }

    boolean isMissing(String columnLabel) {
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

    <T> Optional<T> getIfOk(Function<Integer, T> function, int index) {
        if (this.isMissing(index))
            return Optional.empty();
        return Optional.of(function.apply(index));
    }


}