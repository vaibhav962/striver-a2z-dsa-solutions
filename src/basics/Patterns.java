package basics;

public class Patterns {
    static void printSquare(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    static void printTriangle(int n){
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    static void printColumnNumbersTriangle(int n){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    static void printRowNumbersTriangle(int n){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    static void printTriangleUpsideDown(int n){
        for(int i = 0; i < n; i++){
            for(int j = n-1-i; j >= 0; j--){
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    static void printColumnNumbersTriangleUpsideDown(int n){
        for(int i = 0; i < n; i++){
            for(int j = 1; j <= n-i; j++){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    static void printPyramid(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n-i-1; j++){
                System.out.print(" ");
            }
            for(int j = 0; j < (2*i)+1; j++){
                System.out.print("*");
            }
            for(int j = 0; j < n-i-1; j++){
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    static void printPyramidInverted(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                System.out.print(" ");
            }
            for(int j = 0; j < (2*n)-(2*i)-1; j++){
                System.out.print("*");
            }
            for(int j = 0; j < i; j++){
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    static void printRightAngleSymmetricalTriangle(int n){
        for (int i = 1; i <= 2 * n - 1; i++) {
            int stars = i;
            if(i > n) stars =2*n-i;
            for (int j = 1; j <= stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void pattern11(int n){
        int start = 1;
        for (int i = 0; i < n; i++) {
            if(i % 2 ==0)
                start = 1;
            else start = 0;

            for (int j = 0; j <= i; j++) {
                System.out.print(start+" ");
                start = 1 - start;
            }
            System.out.println();
        }
    }

    static void pattern12(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            for (int j = 0; j <= (2*n)-(2*i)-1; j++) {
                System.out.print(" ");
            }
            for (int j = i; j > 0; j--) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    static void pattern13(int n){
        int number = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(number+" ");
                number++;
            }
            System.out.println();
        }
    }

    static void pattern14(int n){
        for (int i = 0; i < n; i++) {
            for (char j = 'A'; j <= 'A' + i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    static void pattern15(int n){
        for (int i = 0; i < n; i++) {
            for (char j = 'A'; j <= 'A' + n-i-1; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    static void pattern16(int n){
        for (int i = 0; i < n; i++) {
            char ch =(char)('A' + i);
            for (char j = 0; j <= i; j++) {
                System.out.print(ch +" ");
            }
            System.out.println();
        }
    }

    static void pattern17(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n-i-1; j++){
                System.out.print(" ");
            }
            char ch = 'A';
            int breakpoint = ((2*i)+1)/2;
            for(int j = 1; j <= (2*i)+1; j++){
                if(j <= breakpoint)
                    System.out.print(ch++);
                else
                    System.out.print(ch--);
            }
            for(int j = 0; j < n-i-1; j++){
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    static void pattern18(int n){
        char ch = (char)('A'+n-1);
        for (int i = 0; i < n; i++) {
            for (char j = (char)(ch - i); j <=ch; j++) {
                System.out.print(j +" ");
            }
            System.out.println();
        }
    }

    static void pattern19(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print("*");
            }

            for (int j = 0; j < 2 * i; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < n - i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }

            for (int j = 0; j < 2*(n - i - 1); j++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void pattern20(int n){
        int spaces = 2 * n - 2;
        for (int i = 1; i <= 2 * n - 1; i++) {
            int stars = i;
            if(i > n) stars = 2 * n - i;
            for (int j = 1; j < stars; j++) {
                System.out.print("*");
            }

            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }

            for (int j = 1; j < stars; j++) {
                System.out.print("*");
            }
            System.out.println();
            if(i<n) spaces -= 2;
            else spaces += 2;
        }
    }

    static void pattern21(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 || i == n-1 || j == 0 || j == n-1){
                    System.out.print("*");
                }
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    static void pattern22(int n){
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                int top = i;
                int left = j;
                int down = 2*n - i - 2;
                int right = 2*n - j - 2;
                System.out.print(n - Math.min(Math.min(top, down), Math.min(left, right)));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 5;
        pattern22(n);
    }
}
