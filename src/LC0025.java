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
            ListNode now = head, realHead = head, next = null;
            for (int i=1; i<k; i++) {
                next = now.next;
                now.next = next.next;
                next.next = realHead;
                realHead = next;
            }
            now.next = reverseKGroup(now.next, k);
            return realHead;
        }
    }
}
