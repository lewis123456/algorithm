import java.util.PriorityQueue;

public class LC0703 {
    private int k;
    private int[] nums;
    private PriorityQueue<Integer> priorityQueue;
    public LC0703(int k, int[] nums) {
        this.k = k;
        this.nums = nums;
        priorityQueue = new PriorityQueue<>();
        for (int i=0; i<nums.length; i++) {
            if (i < k) {
                priorityQueue.add(nums[i]);
            } else {
                if (nums[i] <= priorityQueue.peek()) {
                    continue;
                } else {
                    priorityQueue.poll();
                    priorityQueue.add(nums[i]);
                }
            }
        }
    }

    public int add(int val) {
        if (k  > priorityQueue.size()) {
            priorityQueue.add(val);
        } else {
            if (val > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.add(val);
            }
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = {};
        LC0703 test = new LC0703(k, arr);
        System.out.println(test.add(-3));
        System.out.println(test.add(-2));
        System.out.println(test.add(-4));
        System.out.println(test.add(0));
        System.out.println(test.add(4));
    }
}
