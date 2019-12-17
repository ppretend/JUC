import sun.rmi.runtime.NewThreadAction;

import java.util.concurrent.TimeUnit;

public class Lock8 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);

        new Thread(()->{
            //phone.hello();
            //phone.sendSMS();
            phone2.sendSMS();
        },"B").start();
    }
}
class Phone{
    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("****************sendEamil");
    }
    public synchronized  void sendSMS(){
        System.out.println("****************sendSMS");
    }
    public void hello(){
        System.out.println("****************hello");
    }
}