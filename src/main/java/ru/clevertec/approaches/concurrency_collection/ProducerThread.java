package ru.clevertec.approaches.concurrency_collection;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.clevertec.approaches.util.RandomUtil;

import java.util.concurrent.BlockingQueue;


@RequiredArgsConstructor
public class ProducerThread implements Runnable{

    private final BlockingQueue<Integer> buffer;
    private final int delay;


    @Override
    @SneakyThrows
    public void run() {
        while (true){
            int produced = RandomUtil.getRandom();

            System.out.println("PRODUCED " + produced + ". SIZE = " + buffer.size());
            buffer.put(produced);

            System.out.println("PRODUCER WAITS - " + delay);
            Thread.sleep(1000 / delay);
        }
    }
}
