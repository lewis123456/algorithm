import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TH0008 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tmp;
        List<Integer> list = new ArrayList<>();
        while ((tmp = scanner.nextInt()) != -1) {
            list.add(tmp);
        }
        Collections.sort(list);
        for (Integer item : list) {
            System.out.println(item + " ");
        }

        BTreeNode root = revertListToBTree(list, 0, list.size() - 1);
        System.out.println("finish revertListToBTree");
        BTreeNode head = revertBTreeToList(root);
        while (head != null) {
            System.out.println(head.i);
            head = head.right;
        }
        System.out.println("finish revertBTreeToList");
    }

    public static BTreeNode revertBTreeToList(BTreeNode root) {
        BTreeNode head, left, right, index;
        if (null == root) {
            return null;
        } else if (null == root.left && null == root.right) {
            return root;
        } else if (null == root.left) {
          head = root;
          right = revertBTreeToList(root.right);
          root.right = right;
          right.left = root;
        } else if (null == root.right) {
            left = revertBTreeToList(root.left);
            head = left;
        } else {
            left = revertBTreeToList(root.left);
            right = revertBTreeToList(root.right);
            head = left;
            index = left;
            while (index.right != null) {
                index = index.right;
            }
            index.right = root;
            root.left = index;
            root.right = right;
        }
        return head;
    }

    public static BTreeNode revertListToBTree(List<Integer> list, int left, int right) {
        if (left == right) {
            BTreeNode tmp = new BTreeNode();
            tmp.i = list.get(left);
            tmp.left = tmp.right = null;
            return tmp;
        } else if (left > right) {
            return null;
        } else {
            int mid = (left + right) / 2;
            BTreeNode tmp = new BTreeNode();
            tmp.i = list.get(mid);
            tmp.left = revertListToBTree(list, left, mid -1);
            tmp.right = revertListToBTree(list, mid + 1, right);
            return tmp;
        }
    }
}


class BTreeNode {
    public int i;
    public BTreeNode left;
    public BTreeNode  right;
}
