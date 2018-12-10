package Lab7;

import org.junit.Test;

import java.io.IOException;

//TODO testy do lab 8
public class AdminUnitListTest {

    @Test
    public void testRead() throws IOException {
        AdminUnitList unitList = new AdminUnitList();
        unitList.read("admin-units.csv");
        unitList.list(System.out, 2, 10);
    }

    @Test
    public void testSelectByName() throws IOException {

        AdminUnitList unitList = new AdminUnitList();
        unitList.read("admin-units.csv");
        unitList.selectByName("województ", false).print_list(System.out);

    }

    @Test
    public void testRelations() throws IOException {
        AdminUnitList unitList = new AdminUnitList();
        unitList.read("admin-units.csv");

        AdminUnitList slaskie = unitList.selectByName("województwo śląskie", true);

        slaskie.getUnit(0).printChildren(System.out);
        slaskie.getUnit(0).printParent(System.out);
    }

    @Test
    public void testGetNeighbors() throws IOException {
        AdminUnitList unitList = new AdminUnitList();
        unitList.read("admin-units.csv");

        AdminUnitList slaskie = unitList.selectByName("województwo śląskie", true);

        System.out.println(slaskie.getUnit(0));
        unitList.getNeighbors(slaskie.getUnit(0), 15).print_list(System.out);
    }
}