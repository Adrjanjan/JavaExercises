package Lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum extends Node {
    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node... nodes){
        args.addAll(Arrays.asList(nodes));
    }

    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(Node ...nodes){
        args.addAll(Arrays.asList(nodes));
        return this;
    }
    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum addProd(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    public double evaluate() {
        double d = 0.0;
        for(Node n : args){
            d += n.evaluate();
        }
        return d * sign;
    }

    @Override
    public String toString() {
        if (getArgumentsCount() == 0)
            return "";

        if(getArgumentsCount() == 1)
            return args.get(0).toString();

        StringBuilder builder = new StringBuilder();
        if(sign < 0)
            builder.append('-');

        for(Node n : args){
            builder.append(n.toString());
            builder.append(" + ");
        }
        builder.delete(builder.lastIndexOf(" + "), builder.lastIndexOf(" + ") + 3);
        builder.append(") ");
        return builder.toString();

    }

    @Override
    public Node diff(Variable var) {
        return null;
    }

    @Override
    int getArgumentsCount(){
        return args.size();
    }

}
