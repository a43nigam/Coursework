// Sieve of Eratosthenes

import java.util.Arrays;

public class Sieve2 {
    // Returns an array isPrime of n elements;
    // isPrime[p] is set to true if and only if p is a prime
    public static boolean[] markPrimes(int n) {
        int input;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, Boolean.TRUE);

//        setting 0 as false because 1 is not prime
        isPrime[0] = false;

        for (int k = 1; k < n; k++) {
            input = ((2 * k) + 1);
            for (int i = 2; i <= (input / 2); i++) {
                if (input % i == 0) {
                    isPrime[k] = false;
                    break;
                }
            }
        }
        return isPrime;
    }


    public static void main(String[] args) {

        int n = 20;
        boolean[] isPrime = markPrimes(n);
        int count = 0;
        for (int p = 0; p < n; p++)
            if (isPrime[p]) {
                System.out.print(p + " ");
                count++;
            }
        System.out.println();
        System.out.println(count + " primes under " + n);
    }
}
