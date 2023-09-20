package ru.clevertec;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int rate; // Настройка скорости производства
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Producer(BlockingQueue<Integer> queue, int rate) {
        this.queue = queue;
        this.rate = rate;
    }

    @Override
    public void run() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                int item = produceItem();
                queue.put(item);
                System.out.println("Produced: " + item);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private int produceItem() {
        // Здесь можно реализовать логику генерации элементов
        return (int) (Math.random() * 100);
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int delay; // Настройка задержки потребления

    public Consumer(BlockingQueue<Integer> queue, int delay) {
        this.queue = queue;
        this.delay = delay;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int item = queue.take();
                System.out.println("Consumed: " + item);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        int producerRate = 1; // Настройка скорости продюсера (1 элемент в секунду)
        int consumerDelay = 2000; // Настройка задержки консьюмера (2 секунды)

        Thread producerThread = new Thread(new Producer(queue, producerRate));
        Thread consumerThread = new Thread(new Consumer(queue, consumerDelay));

        producerThread.start();
        consumerThread.start();
    }
}
