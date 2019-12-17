import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
class AirConditioner {//资源类
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (num != 0) {
                condition.await();
            }
            //2.干活
            ++num;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //3.通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (num == 0) {
                condition.await();
            }
            //2.干活
            --num;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //3.通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

   /* public synchronized void  increment() throws InterruptedException {
        //1.判断
        while (num != 0 ){
            this.wait();
        }
        //2.干活
        ++num ;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        //3.通知
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        //1.判断
        while(num == 0 ){
            this.wait();
        }
        //2.干活
        --num ;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        //3.通知
        this.notifyAll();*/

    }
}