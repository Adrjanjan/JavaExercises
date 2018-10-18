package Lab1;

import java.util.Scanner;

public class Problem610A {

    public static void main (String args[]) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int counter = 0;
        int A, C;

        for (int i = 0; i < length; ++i) {
            if (length % 2 == 1) continue;
            A = i;
            if ((length - 2 * A) % 2 == 1) continue;

            C = (length - 2 * A) / 2;

            if (C < 0 | A == C | A == 0 | C == 0) continue;

            counter++;
            System.out.println("{" + A + "," + A + "," + C + "," + C +
                    '}');

        }
        System.out.println(counter/2);
    }

}
