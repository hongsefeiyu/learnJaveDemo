package thread.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(6));
    private static AtomicInteger i = new AtomicInteger();

    public static void main(String[] args) {
        while (i.intValue() < 10){

            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("ThreadName:"+Thread.currentThread()+" start");
                    int count = i.incrementAndGet();


                    try {
                        Thread.sleep(1000*count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(count+" active Count "+threadPoolExecutor.getActiveCount());
                    System.out.println(count+" queue size "+threadPoolExecutor.getQueue().size());
                    System.out.println(count+" pool size "+threadPoolExecutor.getPoolSize());
                    System.out.println("ThreadName:"+Thread.currentThread()+" end");

                }
            });
        }


    }
}
