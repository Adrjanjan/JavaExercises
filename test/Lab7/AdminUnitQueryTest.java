package Lab7;

import org.junit.Test;

import java.io.IOException;

public class AdminUnitQueryTest {

    @Test
    public void testQuery() throws IOException {
        AdminUnitList unitList = new AdminUnitList();
        unitList.read("admin-units.csv");

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(unitList)
                .where(a -> a.area != null && a.area > 500)
                .and(a -> a.name.startsWith("Sz"))
                .sort((b, a) -> {
                    if (a.area == null) return -1;
                    if (b.area == null) return 1;
                    return Double.compare(a.area, b.area);
                })
                .limit(100);
        query.execute().print_list(System.out);
    }

}