package ru.clevertec.approaches.concurrency_collection;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;


@RequiredArgsConstructor
public class ConsumerThread implements Runnable{

    private final BlockingQueue<Integer> buffer;
    private final int delay;

    @Override
    @SneakyThrows
    public void run() {
        while (true){
            if(!buffer.isEmpty()){
                Integer removed = buffer.take();
                System.out.println("CONSUMED " + removed + ". SIZE = " + buffer.size());
            }else {
                System.out.println("BUFFER IS EMPTY, CONSUMER IS WAITING");
            }

            System.out.println("CONSUMER WAIT " + delay);
            Thread.sleep(delay);
        }
    }

}
