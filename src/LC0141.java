public class LC0141 {
    public static void main(String[] args) {

    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
