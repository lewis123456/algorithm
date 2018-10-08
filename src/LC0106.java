public class LC0106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (null == inorder || 0 == inorder.length) {
            return null;
        }
        TreeNode head = new TreeNode(postorder[postorder.length - 1]);
        int[] leftNewInorder = null, leftNewPostorder = null, rightNewInorder = null, rightNewPostorder = null;
        int index = 0;
        for (int i=0; i<inorder.length; ++i) {
            if (head.val == inorder[i]) {
                index = i;
                break;
            }
        }
        if (0 != index) {
            leftNewInorder = new int[index];
            System.arraycopy(inorder, 0, leftNewInorder, 0, index);
        } else {
            head.left = null;
        }

        if (inorder.length - 1 != index) {
            rightNewInorder = new int[inorder.length - index - 1];
            System.arraycopy(inorder, index + 1, rightNewInorder, 0, inorder.length - index - 1);
        } else {
            head.right = null;
        }

        if (null == head.left && null == head.right) {
            return head;
        } else if (null == head.left) {
            rightNewPostorder = new int[postorder.length - index -1];
            System.arraycopy(postorder, index, rightNewPostorder, 0, postorder.length - index - 1);
            head.right = buildTree(rightNewInorder, rightNewInorder);
        } else if (null == head.right) {
            leftNewPostorder = new int[index];
            System.arraycopy(postorder, 0, leftNewPostorder, 0, index);
            head.left = buildTree(leftNewInorder, leftNewPostorder);
        } else {
            index = 0;
            for (int i=0; i<postorder.length; i++) {
                boolean flag = false;
                for (int j=0; j<leftNewInorder.length; j++) {
                    if (postorder[i] == leftNewInorder[j]) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    index = i;
                }
            }
            leftNewPostorder = new int[index];
            rightNewPostorder = new int[postorder.length - index -1];
            System.arraycopy(postorder, 0, leftNewPostorder, 0, index);
            System.arraycopy(postorder, index, rightNewPostorder, 0, postorder.length - index - 1);
            head.left = buildTree(leftNewInorder, leftNewPostorder);
            head.right = buildTree(rightNewInorder, rightNewInorder);
        }
        return head;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {val = x;}
}
