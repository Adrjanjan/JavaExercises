package Lab1;

import java.util.Scanner;

public class Fibo {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        if(n < 0 | n > 45) return;

        int[] tab = new int[n];
        tab[0] = tab[1] = 1;
        for(int i=2; i< n ; ++i){
                tab[i] = tab[i-1] + tab[i-2];
        }

        for(int i:tab){
            System.out.println(i);
        }

    }
}
