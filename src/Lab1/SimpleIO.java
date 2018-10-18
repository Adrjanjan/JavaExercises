package Lab1;

import java.util.Scanner;

public class SimpleIO {

    public  static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int i = in.nextInt();
        double d = in.nextDouble();
        System.out.printf("%s\n%d \n%f", s, i, d);
    }
}
