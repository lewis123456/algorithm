import java.util.ArrayDeque;
import java.util.Deque;

public class LC0225 {
    private Deque<Integer> stack;

    public LC0225() {
        stack = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        stack.push(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return stack.pop();
    }

    /** Get the top element. */
    public int top() {
        return stack.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}
