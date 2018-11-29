package Lab7;

public class AdminUnit {
    Long id;
    AdminUnit parent;
    String name;
    Integer admin_level;
    Integer population;
    Double density;
    BoundingBox boundingBox;

    public AdminUnit(Long id, AdminUnit parent, String name, Integer admin_level, Integer population, Double density, BoundingBox boundingBox) {
        this.id = id;
        this.parent = parent;
        this.name = name;
        this.admin_level = admin_level;
        this.population = population;
        this.density = density;
        this.boundingBox = boundingBox;
    }

    @Override
    public String toString() {
        return "AdminUnit{" +
                "id=" + id +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                ", admin_level=" + admin_level +
                ", population=" + population +
                ", density=" + density +
                ", boundingBox=" + boundingBox +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setParent(AdminUnit parent) {
        this.parent = parent;
    }
}
