import java.util.*;

/**
 * Largest Rectangle in Histogram
 */
public class LC0084 {
    public int largestRectangleArea(int[] heights) {
        int x = heights.length;
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<x; ++i) {
            set.add(heights[i]);
        }
        int max = 0;
        for (int i : set) {
            int num = 0;
            for (int j=0; j<x; ++j) {
                int temp = 0;
                while (j < x && heights[j] >= i) {
                    ++temp;
                    ++j;
                }
                if (temp > num) {
                    num = temp;
                }
            }
            if (max < num * i) {
                max = num * i;
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception{
        int[] heights = new int[] {2,1,5,6,2,3};
        LC0084 lc0084 = new LC0084();
        Thread.sleep(60000);
        System.out.println(lc0084.largestRectangleArea(heights));
    }
}
