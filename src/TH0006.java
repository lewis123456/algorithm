/**
 * 最少平方和
 */
public class TH0006 {
    public static void main(String[] args) {
        int n = 7;
        System.out.println(minSquareSumNum(n));
    }

    public static int minSquareSumNum(int n) {
        int maxSqrtRoot = (int)Math.sqrt(n);
        int[] square = new int[maxSqrtRoot+1];
        for (int i=1; i<=maxSqrtRoot; i++) {
            square[i] = i * i;
        }
        int[] dp = new int[n+1];
        for (int i=0; i<=n; i++) {
            dp[i] = i;
        }

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=maxSqrtRoot; j++) {
                if (i < j) {
                    continue;
                } else {
                    int size = i / square[j];
                    dp[i] = Math.min(dp[i], dp[i - size * square[j]] + size);
                }
            }
        }
        return dp[n];
    }
}
