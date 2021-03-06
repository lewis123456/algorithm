import java.util.*;

/**
 *  Maximum Swap
 */
public class LC0670 {
    public int maximumSwap(int num) {
        List<Integer> list = new ArrayList();
        List<Integer> orderList = new ArrayList<>();
        int temp;
        int originNum = num;
        while (num != 0) {
            temp = num % 10;
            list.add(temp);
            orderList.add(temp);
            num /= 10;
        }

        Collections.sort(orderList);
        System.out.println(list);
        System.out.println(orderList);
        int size = list.size();

        int index = -1, value = -1;
        for (int i=size-1; i>0; --i) {
            if (orderList.get(i) != list.get(i)) {
                index = i;
                value = orderList.get(i);
                break;
            }
        }
        if (-1 == index) {
            return originNum;
        }
        int p = 0;
        for (int i=0; i<size; ++i) {
            if (value == list.get(i)) {
                p = i;
                break;
            }
        }
        temp = list.get(index);
        list.set(index, list.get(p));
        list.set(p, temp);

        int total = 0;
        for (int i=0; i<size; ++i) {
            total += list.get(i)*Math.pow(10, i);
        }

        return total;
    }

    public int maximumSwap2(int num2) {
        char [] num2Char = Integer.toString(num2).toCharArray();
        int[] list = new int[10];
        for (int i=0; i<num2Char.length; ++i) {
            list[num2Char[i] - '0'] = i;
        }
        for (int i=0; i<num2Char.length; ++i) {
            for (int d = 9; d > num2Char[i] - '0'; --d) {
                if ((num2Char[i] - '0') < d && 0 != list[d] && i < list[d]) {
                    char temp = num2Char[i];
                    num2Char[i] = (char)('0' + d);
                    num2Char[list[d]] = temp;
                    return Integer.valueOf(new String(num2Char));
                }
            }
        }
        return num2;
    }

    public static void main(String[] args) {
        LC0670 lc0670 = new LC0670();
        System.out.println(lc0670.maximumSwap2(9973));
    }
}
