import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        //blockingQueue.put("x");
        System.out.println("*********************");
        //System.out.println(blockingQueue.offer("x"));

        //System.out.println(blockingQueue.element());
       //System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());


    }
}
