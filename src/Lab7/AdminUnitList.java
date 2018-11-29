package Lab7;

import Lab6.CSVReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();


    public void read(String filepath) {

        CSVReader reader = null;
        try {
            reader = new CSVReader(filepath, ",", true);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Map<Long, Long> idToParent = new HashMap<>();
        Map<Long, Long> parentToId = new HashMap<>();

        AdminUnit tmp;
        while (reader.next()) {
            int i = 0;
            idToParent.put(reader.getIfOk(reader::getLong, 0), reader.getIfOk(reader::getLong, 1));
            idToParent.put(reader.getIfOk(reader::getLong, 1), reader.getIfOk(reader::getLong, 0));

            units.add(new AdminUnit(reader.getIfOk(reader::getLong, i++),
                            null,
                            reader.getIfOk(reader::get, i += 2),
                            reader.getIfOk(reader::getInt, i++),
                            reader.getIfOk(reader::getInt, i++),
                            reader.getIfOk(reader::getDouble, i++),
                            new BoundingBox(reader.getIfOk(reader::getDouble, i++),
                                    reader.getIfOk(reader::getDouble, i++),
                                    reader.getIfOk(reader::getDouble, i++),
                                    reader.getIfOk(reader::getDouble, i++),
                                    reader.getIfOk(reader::getDouble, i++),
                                    reader.getIfOk(reader::getDouble, i++),
                                    reader.getIfOk(reader::getDouble, i++),
                                    reader.getIfOk(reader::getDouble, i))

                    )
            );
            i = 0;
        }

        tmp = null;
        for (AdminUnit unit : units) {
            tmp = units.get(idToParent.get(unit.getId()))
            unit.setParent;
        }


    }

    /**
     * Writes to stream units using AdminUnit.toString()
     *
     * @param out - stream to write
     */
    void print_list(PrintStream out) {
        units.forEach(out::println);
    }

    /**
     * Wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
     *
     * @param out    - strumień wyjsciowy
     * @param offset - od którego elementu rozpocząć wypisywanie
     * @param limit  - ile (maksymalnie) elementów wypisać
     */
    void list(PrintStream out, int offset, int limit) {


    }


    public void fixMissingLevel(int level) {


    }

    public List<AdminUnit> selectNameMatches(String regex) {

    }

    public List<AdminUnit> selectInside(String regex) {

    }


}
