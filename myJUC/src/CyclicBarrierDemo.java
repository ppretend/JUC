import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("*************集齐七龙珠");
        });

        for (int i = 1; i <=7 ; i++) {
            final int tmpI = i ;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t收集到第："+tmpI+"\t颗龙珠");
            },String.valueOf(i)).start();
        }
    }
}
