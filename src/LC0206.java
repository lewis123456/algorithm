import java.util.List;

public class LC0206 {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode pre = head;
        ListNode now = head.next;
        ListNode tmp;
        head.next = null;
        while (now != null) {
            tmp = now.next;
            now.next = pre;
            pre = now;
            now = tmp;
        }
        return pre;
    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
