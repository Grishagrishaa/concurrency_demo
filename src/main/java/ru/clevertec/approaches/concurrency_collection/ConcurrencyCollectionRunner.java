package ru.clevertec.approaches.concurrency_collection;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ConcurrencyCollectionRunner {

    private static final int CONSUMER_DELAY = 1000;
    private static final int PRODUCER_RATE = 2;

    private final BlockingQueue<Integer> buffer = new LinkedBlockingQueue<>();

    @SneakyThrows
    public void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(0, 1)
                .mapToObj(i -> new ConsumerThread(buffer, CONSUMER_DELAY))
                .forEach(executorService::submit);

        IntStream.range(0, 1)
                .mapToObj(i -> new ProducerThread(buffer, PRODUCER_RATE))
                .forEach(executorService::submit);

        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }

    public static void main(String[] args) {
        new ConcurrencyCollectionRunner().run();
    }
}
