package ru.clevertec.approaches.classic;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Queue;


@RequiredArgsConstructor
public class ConsumerThread implements Runnable{

    private final Queue<Integer> buffer;
    private final int delay;

    @Override
    @SneakyThrows
    public void run() {
        synchronized (buffer){
            while (true){

                if(!buffer.isEmpty()){
                    Integer removed = buffer.remove();
                    System.out.println("CONSUMED " + removed + ". SIZE = " + buffer.size());
                }else {
                    System.out.println("BUFFER IS EMPTY, CONSUMER IS WAITING");
                }

                System.out.println("CONSUMER WAIT " + delay);
                buffer.wait(delay);
            }
        }
    }
}
