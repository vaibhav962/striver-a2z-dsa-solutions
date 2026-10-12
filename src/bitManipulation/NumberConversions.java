package bitManipulation;

public class NumberConversions {
    // Basic Ones - No overflow detection, just the idea

    // Convert decimal to binary
    // TC: O(log N) | SC: O(log N)
    public static String convertDecimalToBinary(int n){
        if (n == 0) return "0";

        StringBuilder res = new StringBuilder();
        while (n > 0) {
            res.append(n & 1);   // same as n % 2
            n >>= 1;             // same as n / 2
        }
        return res.reverse().toString();
    }

    // Convert binary to decimal
    // TC: O(len) | SC: O(1)
    public static int convertBinaryToDecimal(String n){
        int answer = 0;
        for(int i = n.length()-1; i>=0; i--){
            if(n.charAt(i) == '1'){
                answer += (int)Math.pow(2, n.length()-1-i);
            }
        }
        return answer;
    }

    // Convert decimal to hexadecimal
    public static String convertDecimalToHex(int n){
        if(n==0) return "0";
        StringBuilder res = new StringBuilder();
        char[] hexChar = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n>0){
            int remainder = n % 16;
            res.append(hexChar[remainder]);
            n = n/16;
        }
        return res.reverse().toString();
    }

    // Convert hexadecimal to decimal
    public static int convertHexToDecimal(String n){
        int answer = 0;
        for(int i = n.length()-1; i>=0; i--){
            char ch = n.charAt(i);
            int value = 0;

            if(ch >= '0' && ch <= '9'){
                value = ch - '0';
            }
            else if(ch >= 'A' && ch <= 'F'){
                value = ch - 'A' + 10;
            }
            else if(ch >= 'a' && ch <= 'f'){
                value = ch - 'a' + 10;
            }

            answer += (int) (value * Math.pow(16, n.length() - 1 - i));
        }
        return answer;
    }

    // Ideal - closer to Integer.toBinaryString, Integer.toHexString

    // Convert decimal to binary
    // TC: O(log N) | SC: O(log N)
    public static String convertDecimal2Binary(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative input not supported: " + n);
        if (n == 0) return "0";

        StringBuilder res = new StringBuilder();
        while (n > 0) {
            res.append(n & 1);   // same as n % 2
            n >>= 1;             // same as n / 2
        }
        return res.reverse().toString();
    }

    // Convert binary to decimal (Horner's method)
    // TC: O(len) | SC: O(1)
    public static int convertBinary2Decimal(String s) {
        if (s == null || s.isEmpty()) throw new IllegalArgumentException("Empty input");

        int answer = 0;
        for (char c : s.toCharArray()) {
            if (c != '0' && c != '1') {
                throw new IllegalArgumentException("Invalid binary digit: " + c);
            }
            // throws ArithmeticException on int overflow
            answer = Math.addExact(Math.multiplyExact(answer, 2), c - '0');
        }
        return answer;
    }

    // Convert decimal to hexadecimal
    // TC: O(log N) | SC: O(log N)
    public static String convertDecimal2Hex(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative input not supported: " + n);
        if (n == 0) return "0";

        char[] hexChar = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            res.append(hexChar[n & 15]);  // same as n % 16
            n >>= 4;                      // same as n / 16
        }
        return res.reverse().toString();
    }

    // Convert hexadecimal to decimal (Horner's method)
    // TC: O(len) | SC: O(1)
    public static int convertHex2Decimal(String s) {
        if (s == null || s.isEmpty()) throw new IllegalArgumentException("Empty input");

        int answer = 0;
        for (char ch : s.toCharArray()) {
            int value = Character.digit(ch, 16);  // handles 0-9, a-f, A-F
            if (value < 0) {
                throw new IllegalArgumentException("Invalid hex digit: " + ch);
            }
            // throws ArithmeticException on int overflow
            answer = Math.addExact(Math.multiplyExact(answer, 16), value);
        }
        return answer;
    }
}
