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

    // 14. Longest Common Prefix
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);

                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    // 205. Isomorphic Strings
    public boolean isIsomorphic(String s, String t) {
        int[] sToT = new int[128];
        int[] tToS = new int[128];

        for (int i = 0; i < s.length(); i++) {
            int a = s.charAt(i);
            int b = t.charAt(i);

            if (sToT[a] != 0 && sToT[a] != b + 1) {
                return false;
            }

            if (tToS[b] != 0 && tToS[b] != a + 1) {
                return false;
            }

            sToT[a] = b + 1;
            tToS[b] = a + 1;
        }

        return true;
    }

    // 796. Rotate String
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        int n = s.length();

        for (int shift = 0; shift < n; shift++) {
            boolean match = true;

            for (int i = 0; i < n; i++) {
                if (s.charAt((i + shift) % n) != goal.charAt(i)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                return true;
            }
        }

        return false;
    }

    //
    public boolean rotateStringCheating(String s, String goal) {
        if(s.length() != goal.length()){
            return false;
        }
        return (s+s).contains(goal);
    }

    // 242. Valid Anagram
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int value : count) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}
