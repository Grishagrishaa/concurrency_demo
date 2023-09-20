package ru.clevertec.approaches.classic;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ClassicRunner {

    private static final int CONSUMER_DELAY = 30;
    private static final int PRODUCER_RATE = 2;

    @SneakyThrows
    public void run() {
        LinkedList<Integer> buffer = new LinkedList<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(0, 2)
                 .mapToObj(i -> new ConsumerThread(buffer, CONSUMER_DELAY))
                 .forEach(executorService::submit);

        IntStream.range(0, 2)
                .mapToObj(i -> new ProducerThread(buffer, PRODUCER_RATE))
                .forEach(executorService::submit);

        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }

    public static void main(String[] args) {
        new ClassicRunner().run();
    }

}
