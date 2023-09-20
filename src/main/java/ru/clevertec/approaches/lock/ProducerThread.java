package ru.clevertec.approaches.lock;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.clevertec.approaches.util.RandomUtil;

import java.util.Queue;
import java.util.concurrent.locks.Lock;



@RequiredArgsConstructor
public class ProducerThread implements Runnable {

    private final Queue<Integer> buffer;
    private final int delay;
    private final Lock lock;

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            lock.lock();
            try {
                int produced = RandomUtil.getRandom();

                buffer.add(produced);
                System.out.println("PRODUCED " + produced + ". SIZE = " + buffer.size());

            } finally {
                lock.unlock();
            }

            System.out.println("PRODUCER WAITS - " + 1000 / delay);
            Thread.sleep(1000 / delay);
        }
    }
}
