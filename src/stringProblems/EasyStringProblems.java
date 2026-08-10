package stringProblems;

public class EasyStringProblems {
    // 1021. Remove Outermost Parentheses
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int balance = 0;

        for(char c : s.toCharArray()){
            if(c == '('){
                if (balance > 0) {
                    result.append(c);
                }
                balance++;
            }
            else{
                balance--;
                if (balance > 0) {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }

    // 151. Reverse Words in a String
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");

        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);

            if (i > 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    // 1903. Largest Odd Number in String
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }

        return "";
    }
}
