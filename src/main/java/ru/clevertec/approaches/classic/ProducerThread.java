package ru.clevertec.approaches.classic;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.clevertec.approaches.util.RandomUtil;

import java.util.Queue;


@RequiredArgsConstructor
public class ProducerThread implements Runnable{

    private final Queue<Integer> buffer;
    private final int delay;


    @Override
    @SneakyThrows
    public void run() {
        synchronized (buffer){
            while (true){
                int produced = RandomUtil.getRandom();

                System.out.println("PRODUCED " + produced + ". SIZE = " + buffer.size());
                buffer.add(produced);

                System.out.println("PRODUCER WAITS - " + delay);
                buffer.wait(1000 / delay);
            }
        }
    }
}
