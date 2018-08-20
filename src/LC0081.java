/**
 * Search in Rotated Sorted Array II
 * 测试案例 [1,3,1,1,1], [1], [2,5,6,0,0,1,2]
 */

public class LC0081 {
    public boolean search(int[] nums, int target) {
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int originLeft = left;
            int originRight = right;
            if (target == nums[mid] || target == nums[left] || target == nums[right]) {
                return true;
            }

            if (nums[left] < nums[right]) {
                if (target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < nums[right]) {
                    if (target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        if (target > nums[right]) {
                            right = mid - 1 ;
                        } else {
                            left = mid + 1;
                        }
                    }
                } else {
                    if (target > nums[left]) {
                        if (target < nums[mid]) {
                            right = mid - 1;
                        } else {
                            left = mid + 1;
                        }
                    } else {
                        left = mid + 1;
                    }
                }
            }

            if ((originLeft == left) && (originRight == right)) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,1,1,1};
        int target = 3;
        System.out.println(new LC0081().search(nums, target));
    }
}
