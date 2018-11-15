package Lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prod extends Node {

    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }

    Prod(Node... nodes){
        args.addAll(Arrays.asList(nodes));
    }

    Prod(double c){
        args.add(new Constant(c));
    }


    Prod(double c, Node n){
        this(new Constant(c), n);
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }

    @Override
    public double evaluate() {
        double result =1;
        for(Node n: args){
            result *= n.evaluate();
        }
        return sign*result;
    }

    @Override
    public String toString() {
        StringBuilder b =  new StringBuilder();
        if(sign<0) b.append("-");
        for(Node n: args){
            b.append(' ').append(n.toString()).append(" *");
        }
        b.delete(b.lastIndexOf(" *"), b.lastIndexOf(" *") + 2);
        b.append(") ");
        return b.toString();
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
