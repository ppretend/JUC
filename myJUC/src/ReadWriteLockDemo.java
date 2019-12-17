import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <=10 ; i++) {
            final int tmpI = i ;
            new Thread(() -> {
                myCache.put(tmpI+"",tmpI+"" );
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=10 ; i++) {
            final int tmpI = i ;
            new Thread(() -> {
                myCache.get(tmpI+"");
            },String.valueOf(i)).start();
        }

    }
}
class MyCache{
    private volatile Map<String,String> map = new HashMap<>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void put(String key,String value){
        rwl.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入结束");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();
        }
    }

    public void get(String key){
        rwl.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取结束:"+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }
    }


    /*private Lock lock = new ReentrantLock();
    public void put(String key,String value){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入结束");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void get(String key){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取结束:"+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }*/
}
