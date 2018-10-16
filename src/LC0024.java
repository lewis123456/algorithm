public class LC0024 {
    public static void main(String[] args) {

    }

    public ListNode swapPairsOne(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode save = head.next;
        ListNode after = save.next;

        save.next = head;
        head.next = swapPairs(after);

        return save;
    }

    public ListNode swapPairs(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode current = head, pre = new ListNode(0), tmp;
        ListNode root = current.next;
        while (null != current && null != current.next) {
            pre.next = current.next;
            tmp = current.next.next;
            current.next.next = current;
            current.next = tmp;
            pre = current;
            current = tmp;
        }
        return root;
    }
}
