package bitManipulation;

public class Basics {
    // Swap two numbers using XOR
    public static void swap(int a, int b) {
        System.out.println("Before swap: a=" + a + ", b=" + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("After swap: a=" + a + ", b=" + b);
    }

    // Check if i-th bit is set
    public static boolean isIthBitSet(int num, int i) {
        return (num & (1 << i)) != 0;
    }
    // Using Right Shift
    public static boolean isIthBitSetRS(int num, int i) {
        return ((num >> i)  & 1) != 0;
    }

    // Set i-th bit
    public static int setIthBit(int num, int i) {
        return num | (1 << i);
    }

    // Clear i-th bit
    public static int clearIthBit(int num, int i) {
        return num & ~(1 << i);
    }

    // Toggle i-th bit
    public static int toggleIthBit(int num, int i) {
        return num ^ (1 << i);
    }

    // Remove rightmost set bit
    public static int removeLastSetBit(int num) {
        return num & (num - 1);
    }

    // Check if power of 2
    public static boolean isPowerOfTwo(int num) {
        return num > 0 && (num & (num - 1)) == 0;
    }

    //  Count set bits (Java built-in)
    public static int countSetBitsBuiltIn(int num) {
        return Integer.bitCount(num);
    }

    //  Count set bits (Brian Kernighan’s Algorithm)
    public static int countSetBits(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }

    // Check odd or even
    public static String isOddOrEven(int num) {
        return (num & 1) == 1 ? "Odd" : "Even";
    }

    // Divide by 2
    public static int divideByTwo(int num) {
        return num >> 1;
    }

}
