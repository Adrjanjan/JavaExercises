package Lab5;

public class Power extends Node {
    Node arg;
    double p;

    public Power(Node arg, double p) {
        this.arg = arg;
        this.p = p;
    }

    @Override
    public double evaluate() {
        double argVal = arg.evaluate();
        return Math.pow(argVal,p);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        int argSign = arg.getSign();
        int cnt = arg.getArgumentsCount();
        boolean useBracket = false;

        if (sign < 0)
            b.append("-");


        if (argSign < 0 || cnt > 1)
            useBracket = true;

        String argString = arg.toString();

        if (useBracket)
            b.append("(");

        b.append(argString);

        if (useBracket)
            b.append(")");

        b.append("^");
        b.append(p);
        return b.toString();
    }

    @Override
    public Node diff(Variable var) {
        return null;
    }

    @Override
    int getArgumentsCount(){return 1;}

}
