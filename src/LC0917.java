
public class LC0917 {
    public static void main(String[] args) {
        System.out.println(reverseOnlyLetters("ab-cd"));
        System.out.println(reverseOnlyLetters("a-bC-dEf-ghIj"));
        System.out.println(reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }

    public static String reverseOnlyLetters(String S) {
        int i = 0, j = S.length() - 1;
        char[] s = S.toCharArray();
        while (i < j) {
            while (!((S.charAt(i) >= 'A' && S.charAt(i) <= 'Z') ||
                    (S.charAt(i) >= 'a' && S.charAt(i) <= 'z')) && i < j) i++;
            while (!((S.charAt(j) >= 'A' && S.charAt(j) <= 'Z') ||
                    (S.charAt(j) >= 'a' && S.charAt(j) <= 'z')) && i < j) j--;
            if (i < j) {
                char ch = s[i];
                s[i] = s[j];
                s[j] = ch;
                i++;
                j--;
            }
        }
        return new String(s);
    }
}
