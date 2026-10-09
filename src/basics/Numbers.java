package basics;

public class Numbers {
    static int countNumbers(int n){
        int count = 0;
        while(n > 0){
//            int digit = n % 10;
            n = n / 10;
            count++;
        }

        return count;
    }

    // 7. Reverse Integer
    static int reverse(int x) {
        int reverseNum = 0;
        while (x != 0) {
            int digit = x % 10;
            reverseNum = reverseNum * 10 + digit;
            x /= 10;

            // check for overflow
            if (reverseNum > Integer.MAX_VALUE/10 || (reverseNum == Integer.MAX_VALUE/10 && digit > 7))
                return 0;
            if (reverseNum < Integer.MIN_VALUE/10 || (reverseNum == Integer.MIN_VALUE/10 && digit < -8))
                return 0;
        }
        return reverseNum;
    }

    // 9. Palindrome Number
    static boolean isPalindrome(int x) {
        int n = x;
        int reverseNum = 0;
        while(n > 0){
            int digit = n % 10;
            n = n / 10;

            // check for overflow!!
            if(reverseNum > Integer.MAX_VALUE/10 || (reverseNum == Integer.MAX_VALUE/10 && digit > 7))
                return false;

            reverseNum = reverseNum * 10 + digit;
        }
        if(x == reverseNum)
            return true;
        return false;
    }

    // Armstrong Number
    static boolean isArmstrong(int x) {
        int sum = 0;
        int n = x;
        while(n > 0){
            int digit = n % 10;
            n = n / 10;

            sum = sum + digit*digit*digit;
        }
        if(sum == x)
            return true;
        return false;
    }

    // Divisors
    // O(n)
    static void printAllDivisors(int n){
        for (int i = 1; i <= n; i++) {
            if(n % i == 0)
                System.out.print(i + " ");
        }
    }

    // O(sqrt(n))
    static void printDivisors(int n){
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                System.out.print(i + " ");
                if ((n / i) != i)
                    System.out.print(n / i + " ");
            }
        }
    }

    // Prime number
    static boolean isPrime(int n){
        int counter = 0;
        for (int i = 1; i*i <= n; i++) {
            if(n % i == 0){
                counter++;
                if((n/i) != i){
                    counter++;
                }
            }
        }
        return counter == 2;
    }

    // GCD O(min(n1, n2))
    static int GCD(int n1, int n2){
        int gcd = 1;
        int x = Math.min(n1, n2);
        for (int i = x; i >= 1; i--) {
            if(n1 % i == 0 && n2 % i == 0) {
                gcd = i;
                break;
            }
        }
        return gcd;
    }
    // O(log(min(a, b)))
    static int betterGCD(int a, int b){
        while(a > 0 && b > 0){
            if(a > b) a = a % b;
            else b = b % a;
        }
        if(a == 0) return b;
        else return a;
    }

    public static void main(String[] args) {
        int num = 9;
//        System.out.println(isPrime(num));
//        printDivisors(num);
        System.out.println(betterGCD(13,11));
    }

}
