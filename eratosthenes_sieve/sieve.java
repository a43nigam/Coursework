// Sieve of Eratosthenes

public class Sieve {
    // Returns an array isPrime of n elements;
    // isPrime[p] is set to true if and only if p is a prime
    public static boolean[] markPrimes(int n) {
        boolean[] isPrime = new boolean[n]; // all set to false by default

//        isPrime[0] = isPrime[1] = false; // optional

        for (int i = 2; i < n; i++) { // {false, false, true, true,..., true}
            isPrime[i] = (i == 2 || i % 2 != 0);
        }

        for (int p = 3; p < n; p += 2) {
//            if (isPrime[p])  // if isPrime[p] is true     <- not needed because of above loop.
//
//            The below code is an alternative for the for loop below it.

//            for (int j = 2; (j * p) <= (n - 1); j++) {
//                isPrime[j * p] = false;
//            }

            for (int i = 2 * p; i < n; i += p) {
                isPrime[i] = false;
            }
            if (!isPrime[p + 2]) {
                p += 2;
            }
        }
        return isPrime;
    }

    public static void main(String[] args) {
        int n = 10;
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
