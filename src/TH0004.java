/**
 * 汉诺塔问题
 * f(n) = 2^n - 1
 */
public class TH0004 {
    public static void main(String[] args) {
        System.out.println(new TH0004().move(5, 'A', 'B', 'C'));
        return;
    }

    /**
     *
     * @param n
     */
    int move(int n, char x, char y, char z) {
        if (1 == n) {
            System.out.format("%d : %c -> %c", n, x, z);
            System.out.println("");
            return 1;
        } else {
            int a = move(n-1, x, z, y);
            System.out.format("%d : %c -> %c", n, x, z);
            System.out.println("");
            int b = move(n-1, y, x, z);
            return a + b + 1;
        }
    }
}

