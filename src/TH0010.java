import java.util.Arrays;

/**
 * shopee，对只由0,1,2组成的数组排序
 * 时间复杂度O(n), 空间复杂度O(1)
 */
public class TH0010 {
    public static void main(String[] args) {
        int arr[] = {1, 0, 1, 2, 2, 2, 0, 1, 2};
        System.out.println(Arrays.toString(arr));
        //sortByCount(arr, arr.length);
        sort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    //指针法
    public static void sort(int arr[], int n) {
        int n0 = 0, n1 = 0,  n2 = 0;
        for (int i=0; i<n; i++) {
            switch (arr[i]) {
                case 0: {
                    n0 = 1;
                    break;
                }
                case 1: {
                    n1 = 1;
                    break;
                }
                case 2: {
                    n2 = 1;
                    break;
                }
            }
        }

        int temp = n0 + n1 + n2;
        if (1 == temp) {
            return;
        } else if (2 == temp) {
            int min, max;
            if (1 == n0) {
                min = 0;
                if (1 == n1) {
                    max = 1;
                } else {
                    max = 2;
                }
            } else {
                min = 1;
                max = 2;
            }
            int left = 0, right = n - 1;
            while (left < right) {
                while (left < right && min == arr[left]) {
                    left++;
                }
                while (left < right && max == arr[right]) {
                    right--;
                }
                int now = arr[left];
                arr[left] = arr[right];
                arr[right] = now;
            }
        } else {
            int i = 0, j = n-1;
            for (int index = 0; index < n && index < j; index++) {
                if (0 == arr[index]) {
                    arr[index] = arr[i];
                    arr[i++] = 0;
                } else if (2 == arr[index]) {
                    arr[index--] = arr[j];
                    arr[j--] = 2;
                }
            }
        }
    }

    //计数法
    public static void sortByCount(int arr[], int n) {
        int n0 = 0, n1 = 0,  n2 = 0;
        for (int i=0; i<n; i++) {
            switch (arr[i]) {
                case 0: {
                    n0++;
                    break;
                }
                case 1: {
                    n1++;
                    break;
                }
                case 2: {
                    n2++;
                    break;
                }
            }
        }
        for (int i=0; i<n; ++i) {
            if (i < n0) {
                arr[i] = 0;
            } else if (i < n0 + n1) {
                arr[i] = 1;
            } else {
                arr[i] = 2;
            }
        }
    }
}
