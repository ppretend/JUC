import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟有3个车位
        for (int i = 1; i <=6 ; i++) {//模拟6部汽车占车位
            new Thread(() -> {
                boolean flag = false ;
                try {
                    semaphore.acquire();
                    flag = true ;
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(flag){
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
