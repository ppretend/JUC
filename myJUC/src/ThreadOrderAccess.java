import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                shareResource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                shareResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}

class ShareResource{
    private int flag = 1 ; //1:A    2:B     3:C
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            //1.判断
            while (flag != 1){
                c1.await();//A系统停止
            }
            //2.干活
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.通知
                flag = 2 ;
                c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try{
            //1.判断
            while (flag != 2){
                c2.await();//A系统停止
            }
            //2.干活
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.通知
            flag = 3 ;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try{
            //1.判断
            while (flag != 3){
                c3.await();//A系统停止
            }
            //2.干活
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.通知
            flag = 1 ;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}