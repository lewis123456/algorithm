import java.util.Stack;

public class LC0232 {
    private Stack<Integer> dequeue;

    public LC0232() {
        dequeue = new Stack<>();
    }

    public void push(int x) {
        Stack<Integer> tmp = new Stack<>();
        while (!dequeue.empty()) {
            tmp.push(dequeue.pop());
        }
        tmp.push(x);
        while (!tmp.empty()) {
            dequeue.push(tmp.pop());
        }
    }

    public int pop() {
        return dequeue.pop();
    }

    public int peek() {
        return dequeue.peek();
    }

    public boolean empty() {
        return dequeue.empty();
    }
}
