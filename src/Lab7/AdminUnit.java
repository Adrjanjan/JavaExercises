package Lab7;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AdminUnit extends BoundingBox {
    AdminUnit parent;
    String name;
    Integer admin_level;
    Integer population;
    Double area;
    Double density;
    BoundingBox boundingBox;
    List<AdminUnit> children = new ArrayList<>();

    public AdminUnit(AdminUnit parent, String name, Integer admin_level, Integer population, Double area, Double density, BoundingBox boundingBox) {
        this.parent = parent;
        this.name = name;
        this.admin_level = admin_level;
        this.population = population;
        this.area = area;
        this.density = density;
        this.boundingBox = boundingBox;
    }

    @Override
    public String toString() {
        String parentString;
        if (parent == null)
            parentString = "none";
        else
            parentString = parent.getName();
        return "AdminUnit{" +
                "parent=" + parentString +
                ", name='" + name + '\'' +
                ", admin_level=" + admin_level +
                ", population=" + population +
                ", area=" + area +
                ", density=" + density +
                ", boundingBox=" + boundingBox +
                '}';
    }

    /**
     * @return name of this administration unit
     */
    public String getName() {
        return name;
    }

    /**
     * @param parent of this object
     */
    public void setParent(AdminUnit parent) {
        this.parent = parent;
    }

    /**
     * @return administraion level of this object
     */
    public Integer getAdmin_level() {
        if (admin_level != null)
            return admin_level;
        return 11;
    }

    /**
     * Fixes missing values of density and population
     */
    void fixMissingValues() {
        if (parent == null || density != null) return;
        parent.fixMissingValues();
        if (parent.density == null) return;
        density = parent.density;
        if (area == null) return;
        population = (int) (density * area);
    }

    /**
     * Sets children to this object
     *
     * @param children of this object
     */
    void setChildren(List<AdminUnit> children) {
        this.children = children;
    }

    public void printChildren(PrintStream out) {
        children.forEach(out::println);
    }

    public void printParent(PrintStream out) {
        out.println(parent);
    }
}
