package ru.clevertec.approaches.lock;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Queue;
import java.util.concurrent.locks.Lock;


@RequiredArgsConstructor
public class ConsumerThread implements Runnable {

    private final Queue<Integer> buffer;
    private final int delay;
    private final Lock lock;

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (!buffer.isEmpty()) {
                    Integer removed = buffer.poll();
                    System.out.println("CONSUMED " + removed + ". SIZE = " + buffer.size());

                } else {
                    System.out.println("BUFFER IS EMPTY, CONSUMER IS WAITING");
                }
            } finally {
                lock.unlock();
            }

            System.out.println("CONSUMER WAIT " + delay);
            Thread.sleep(delay);

        }
    }
}

