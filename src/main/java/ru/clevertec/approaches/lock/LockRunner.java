package ru.clevertec.approaches.lock;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LockRunner {

    private static final int CONSUMER_DELAY = 500;
    private static final int PRODUCER_RATE = 1;
    private static final Lock lock = new ReentrantLock();

    private final BlockingQueue<Integer> buffer = new LinkedBlockingQueue<>();

    @SneakyThrows
    public void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(0, 1)
                .mapToObj(i -> new ConsumerThread(buffer, CONSUMER_DELAY, lock))
                .forEach(executorService::submit);

        IntStream.range(0, 1)
                .mapToObj(i -> new ProducerThread(buffer, PRODUCER_RATE, lock))
                .forEach(executorService::submit);

        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }

    public static void main(String[] args) {
        new LockRunner().run();
    }

}
