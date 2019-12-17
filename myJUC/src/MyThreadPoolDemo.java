import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        /*ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5线程
        ExecutorService threadPool2 =    Executors.newSingleThreadExecutor();
        ExecutorService threadPool3 =    Executors.newCachedThreadPool();*/
        ExecutorService threadPool = new ThreadPoolExecutor(3,5,1L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 1; i <=20 ; i++) {
                final int tempI = i ;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务"+"\t客户号："+tempI);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
