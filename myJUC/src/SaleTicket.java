import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();


//        new Thread(()->{ for (int i = 0; i < 35 ; i++) ticket.sale(); },"A").start();
//        new Thread(()->{ for (int i = 0; i < 35 ; i++) ticket.sale(); },"B").start();
//        new Thread(()->{ for (int i = 0; i < 35 ; i++) ticket.sale(); },"C").start();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 1; i <= 30 ; i++) {
                threadPool.execute(()->{ticket.sale(); });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35 ; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35 ; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35 ; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();*/
    }
}
class Ticket{
    private  int num = 30 ;

    private Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try{
            if(num>0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第"+(num--)+"\t还剩下："+num);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}