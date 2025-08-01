package producerConsumer.semaphore;

import java.util.concurrent.Semaphore;

public class Consumer implements Runnable{
    private Store store;
    private Semaphore producerSemaphore;
    private Semaphore consumerSemaphore;

    public Consumer(Store store,
                    Semaphore producerSemaphore,
                    Semaphore consumerSemaphore)
    {
        this.store = store;
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
    }
    @Override
    public void run() {
        while(true){
//            if items are available then consume
            try {
                consumerSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            store.remove();
            producerSemaphore.release();
        }
    }
}