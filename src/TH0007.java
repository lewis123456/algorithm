import java.util.Scanner;

/**
 * 多个有序链表归并成一个有序链表，并去重
 */
public class TH0007 {
    public static Node merge(Node[] lists) {
        Node tail = null;
        Node head = null;
        while (true) {
            boolean flag = false;
            Node min = new Node();
            min.i = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i=0; i<lists.length; i++) {
                if (null == lists[i]) {
                    continue;
                }
                if (!flag) {
                    flag = true;
                }
                if (lists[i].i < min.i) {
                    min = lists[i];
                    minIndex = i;
                }
            }

            if (false == flag) {
                break;
            }

            if (null == tail) {
                tail = min;
                head = tail;
            } else {
                tail.next = min;
                tail = min;
            }

            lists[minIndex] = lists[minIndex].next;
        }

        Node p = head;
        while (true){
            if (null == p) {
                break;
            }
            Node next = p.next;
            while (next != null && next.i == p.i) {
                next = next.next;
            }
            p.next = next;
            p = next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node[] lists = new Node[4];
        Scanner scanner = new Scanner(System.in);
        for (int i=0; i<4; i++) {
            int temp;
            Node pre = null;
            while (-1 != (temp = scanner.nextInt())) {
                Node now = new Node();
                now.i = temp;
                if (null != pre) {
                    pre.next = now;
                    pre = now;
                } else {
                    lists[i] = now;
                    pre = now;
                }
            }
        }
        Node result = merge(lists);
        while (result != null) {
            System.out.println(result.i);
            result = result.next;
        }
    }
}

class Node {
    public int i;
    public Node next = null;
}
