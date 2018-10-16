public class LC0025 {
    public ListNode reverseKGroup(ListNode head, int k) {
        int size = 0;
        ListNode temp = head;
        while (null != temp) {
            temp = temp.next;
            size++;
        }
        if (size < k) {
            return head;
        } else {
            ListNode now = head.next, nextNow = now.next, newHead = head;
            for (int i=2; i<k; i++) {
                now.next = newHead;
                newHead.next = nextNow;
                newHead = now;
                now = nextNow;
                nextNow = now.next;
            }
            now = reverseKGroup()
        }
    }
}
