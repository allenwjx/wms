import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final String A = "_A";
    private static final String B = "_B";
    private static final String C = "_C";

    public static void main(String[] args) throws InterruptedException {
        // 获取输入数据
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();

        MyContext context = new MyContext(input);

        FutureTask<String> task1 = new FutureTask<>(new MyCallable(context, A));
        FutureTask<String> task2 = new FutureTask<>(new MyCallable(context, B));
        FutureTask<String> task3 = new FutureTask<>(new MyCallable(context, C));

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();

        System.out.println(context.getInput());
    }

    public static class MyCallable implements Callable<String> {
        private static final Lock lock = new ReentrantLock();
        private MyContext         context;
        private String            str;

        public MyCallable(MyContext context, String str) {
            this.context = context;
            this.str = str;
        }

        @Override
        public String call() throws Exception {
            try {
                lock.lock();
                context.setInput(context.getInput() + str);
            } finally {
                lock.unlock();
            }
            return context.getInput();
        }
    }

    public static class MyContext {

        private String input;

        public MyContext(String input) {
            this.input = input;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }
    }

}