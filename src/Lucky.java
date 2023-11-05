package src;

import java.util.concurrent.atomic.AtomicInteger;

public class Lucky {
    static AtomicInteger x = new AtomicInteger(0);
    static AtomicInteger count = new AtomicInteger(0);

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (x.get() < 999999) {
                int currentValue = x.incrementAndGet();
                if ((currentValue % 10) + (currentValue / 10) % 10 + (currentValue / 100) % 10 == (currentValue / 1000)
                        % 10 + (currentValue / 10000) % 10 + (currentValue / 100000) % 10) {
                    count.incrementAndGet();
                    System.out.println(count);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}