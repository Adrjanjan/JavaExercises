package Lab7;

import Lab6.CSVReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminUnitList {
    private List<AdminUnit> units = new ArrayList<>();

    /**
     * Reads data from CSV file and parses it to object
     *
     * @param filepath  CSV file to read data from
     * @param delimiter used in csv file
     * @param hasHeader true if csv has header
     * @throws IOException when file can't be opened
     */
    public void read(String filepath, String delimiter, boolean hasHeader) throws IOException {
        CSVReader reader = new CSVReader(filepath, delimiter, hasHeader);

        Map<AdminUnit, Long> unitToParentId = new HashMap<>();
        Map<Long, AdminUnit> idToUnit = new HashMap<>();
        Map<Long, List<AdminUnit>> parentIdToUnits = new HashMap<>();

        AdminUnit tmp;
        List<AdminUnit> tmp_list;
        while (reader.next()) {
            int i = 2;
            tmp = new AdminUnit(null,                                //parent
                    reader.getIfOk(reader::get, i),                   //name
                    reader.getIfOk(reader::getInt, i + 1),                //admin_lvl
                    reader.getIfOk(reader::getInt, i + 2),                //population
                    reader.getIfOk(reader::getDouble, i + 3),             //area
                    reader.getIfOk(reader::getDouble, i + 4),             //density
                    new BoundingBox());

            if (!reader.isMissing("x1")) {
                tmp.boundingBox.addPoint(reader.getDouble("x1"), reader.getDouble("y1"));
                tmp.boundingBox.addPoint(reader.getDouble("x2"), reader.getDouble("y2"));
                tmp.boundingBox.addPoint(reader.getDouble("x3"), reader.getDouble("y3"));
                tmp.boundingBox.addPoint(reader.getDouble("x4"), reader.getDouble("y4"));
            }

            unitToParentId.put(tmp, reader.getIfOk(reader::getLong, 1));
            idToUnit.put(reader.getIfOk(reader::getLong, 0), tmp);
            units.add(tmp);

            tmp_list = parentIdToUnits.get(reader.getIfOk(reader::getLong, 1));
            if (tmp_list == null) tmp_list = new ArrayList<>();
            tmp_list.add(tmp);
            parentIdToUnits.put(reader.getIfOk(reader::getLong, 1), tmp_list);
        }

        updateParents(idToUnit, unitToParentId);
        updateChildren(idToUnit, parentIdToUnits);
        fixMissingValues();
    }

    /**
     * Reads data from CSV file and parses it to object
     *
     * @param filepath CSV file to read data from
     */
    public void read(String filepath) throws IOException {
        read(filepath, ",", true);
    }

    /**
     * Updates references to children
     *
     * @param idToUnit        map to connect id with unit
     * @param parentIdToUnits map to connect parent's id with its children
     */
    private void updateChildren(Map<Long, AdminUnit> idToUnit, Map<Long, List<AdminUnit>> parentIdToUnits) {
        Map<AdminUnit, Long> unitToId = idToUnit.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        for (AdminUnit unit : units) {
            unit.setChildren(parentIdToUnits.get(unitToId.get(unit)));
        }
    }

    /**
     * Updates references to parents
     *
     * @param idToUnit       map to connect id with unit
     * @param unitToParentId map to connect unit with its parent id
     */
    private void updateParents(Map<Long, AdminUnit> idToUnit, Map<AdminUnit, Long> unitToParentId) {
        for (AdminUnit unit : units) {
            if (unit.getAdmin_level() == 1) {
                unit.setParent(null);
                continue;
            }
            unit.setParent(idToUnit.get(unitToParentId.get(unit)));
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
        units.stream().skip(offset).limit(limit).forEach(out::println);
    }

    private void fixMissingValues() {
        for (AdminUnit unit : units) {
            if (unit.density == null) {
                unit.fixMissingValues();
            }
        }
    }

    /**
     * Zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
     *
     * @param pattern - wzorzec dla nazwy
     * @param regex   - jeśli regex=true, użyj finkcji String matches(); jeśli false użyj funkcji contains()
     * @return podzbiór elementów, których nazwy spełniają kryterium wyboru
     */
    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();

        if (regex) {
            for (AdminUnit unit : units) {
                if (unit.getName().matches(pattern)) {
                    ret.units.add(unit);
                }
            }
        } else {
            for (AdminUnit unit : units) {
                if (unit.getName().contains(pattern)) {
                    ret.units.add(unit);
                }
            }
        }

        return ret;
    }

    public AdminUnit getUnit(int index) {
        return units.get(index);
    }

    /**
     * Zwraca listę jednostek sąsiadujących z jendostką unit na tym samym poziomie hierarchii admin_level.
     * Czyli sąsiadami wojweództw są województwa, powiatów - powiaty, gmin - gminy, miejscowości - inne miejscowości
     *
     * @param unit        - jednostka, której sąsiedzi mają być wyznaczeni
     * @param maxdistance - parametr stosowany wyłącznie dla miejscowości, maksymalny promień odległości od środka unit,
     *                    w którym mają sie znaleźć punkty środkowe BoundingBox sąsiadów
     * @return lista wypełniona sąsiadami
     */
    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) {
        AdminUnitList neighbours = new AdminUnitList();
        for (AdminUnit neighbour : units) {
            if (!unit.admin_level.equals(neighbour.admin_level) || neighbour.equals(unit)) continue;
            if (unit.boundingBox.intersects(neighbour.boundingBox) || maxdistance > unit.boundingBox.distanceTo(neighbour.boundingBox)) {
                neighbours.units.add(neighbour);
            }
        }
        return neighbours;
    }

}
