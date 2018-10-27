import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC0020 {
    public static void main(String[] args) {
        LC0020 lc0020 = new LC0020();
        try {
            System.out.println(lc0020.isValid("([)]"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isValid(String s) {
        if (null == s || "".equals(s)) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for (int i=0; i<s.length(); i++) {
            char now = s.charAt(i);
            if (0 == stack.size()) {
                if (null == map.get(now)) {
                    return false;
                } else {
                    stack.push(now);
                }
            } else {
                if (map.get(stack.peek()) == now) {
                    stack.pop();
                } else {
                    if (null == map.get(now)) {
                        return false;
                    } else {
                        stack.push(now);
                    }
                }
            }
        }
        return (0 == stack.size());
    }
}
