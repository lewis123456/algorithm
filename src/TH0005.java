
/**
 * 最长回文子串
 */
public class TH0005 {
    public static void main(String[] args) {
        String str = "fabcbag";
        System.out.println(maxPalindromeSub(str));
    }

    public static String maxPalindromeSub(String str) {
        if (null == str || str.length() <= 1) {
            return str;
        }
        
        int size = str.length();
        boolean[][] dp = new boolean[size][size];
        int first = 0, last = 0, length = 1;

        for (int i=0; i<size; i++) {
            dp[i][i] = true;
            if (i+1 < size) {
                if (str.charAt(i) == str.charAt(i+1)) {
                    dp[i][i+1] = true;
                    if (2 > length) {
                        first = i;
                        last = i + 1;
                        length = 2;
                    }
                }
            }
        }

        for (int j=0; j<size; j++) {
            for (int i=j-2; i>=0; i--) {
                dp[i][j] = dp[i+1][j-1] && (str.charAt(i) == str.charAt(j));
                if (true == dp[i][j] && length < (j - i + 1)) {
                    first = i;
                    last = j;
                    length = j - i + 1;
                }
            }
        }

        return str.substring(first,last + 1);
    }
}
