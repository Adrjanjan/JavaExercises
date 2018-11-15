package Lab5;

public class Variable extends Node {
    private String name;
    private Double value;

    public Variable(String name) {
        this.name = name;
    }

    public Variable(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        String sgn=sign<0?"-":"";
        return sgn+name;
    }

    @Override
    public Node diff(Variable var) {
        return new Constant(1.0);
    }
}
