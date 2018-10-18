package Lab1;

import java.util.Scanner;

public class Problem115A {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] empl = new int[size];

        for (int i = 0; i < size; ++i) {
            empl[i] = in.nextInt();
        }

        int answer = 0;

        for (int i = 0; i < size; ++i) {
            int tmp = 0;
            for (int j = i; j <= size && j != -2; j = empl[j]-1) {
                ++tmp;
            }
            answer = (answer > tmp) ? answer : tmp;
        }
        System.out.println(answer);
    }
}
