import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 阻塞式线程安全
 */
public class TH0009 {
    private int n;
    private List<Integer> safeQueue;
    private Object object = new Object();

    public int get() {
        try {
            synchronized (object) {
                System.out.println(Thread.currentThread() + " get begin" );
                if (safeQueue.size() == 0) {
                    object.wait();
                }
                int result = safeQueue.get(0);
                safeQueue.remove(0);
                System.out.println(Thread.currentThread() + " get end " + result);
                object.notifyAll();
                return result;
            }
        } catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public void put(int x) {
        try {
            synchronized (object) {
                System.out.println(Thread.currentThread() + " put begin");
                if (safeQueue.size() == n) {
                    object.wait();
                }
                safeQueue.add(x);
                System.out.println(Thread.currentThread() + " put end " + x);
                object.notifyAll();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public TH0009(int n) {
        this.n = n;
        safeQueue = new ArrayList<>(n);
    }

    public static void main(String[] args) {
        TH0009 th0009 = new TH0009(5);
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i=0; i<200; i++) {
            int tmp = i;
            executorService.submit(() -> {
                if (random.nextInt() % 2 == 0) {
                    th0009.get();
                } else {
                    th0009.put(tmp);
                }
            });
        }
        executorService.shutdown();
    }
}
