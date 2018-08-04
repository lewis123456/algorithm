import java.util.Arrays;

/***
 *  Beautiful Arrangement II
 *  k=1时，1,2,3,...,n
 *  k=n-1时，1,n,2,n-1,...k-1,k
 *  k=x时，1,2,...,n-x, n,n-x+1,n-1,...,
 */

public class LC0667 {
    public int[] constructArray(int n, int k) {
        if (n < 2 || k > n - 1 || k < 1) {
            return null;
        }

        int[] result = new int[n];
        for (int i=0; i<n-k; ++i) {
            result[i] = i + 1;
        }
        boolean flag = true;
        for (int i=0; i<k; ++i) {
            if (flag) {
                result[i + n - k] = n - i/2;
            } else {
                result[i + n - k] = n - k + (i+1)/2;
            }
            flag = !flag;
        }
        return result;
    }

    public static void main(String[] args) {
        LC0667 lc0667 = new LC0667();
        System.out.println(Arrays.toString(lc0667.constructArray(5, 3)));
    }
}
