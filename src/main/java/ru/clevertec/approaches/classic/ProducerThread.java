package ru.clevertec.approaches;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.clevertec.approaches.classic.util.RandomUtil;

import java.util.Queue;

/**
 * @author Grisha Mitskevich on 20.09.23.
 */
@RequiredArgsConstructor
public class Producer implements Runnable{

    private final Queue<Integer> buffer;
    private final int delay;


    @Override
    @SneakyThrows
    public void run() {
        synchronized (buffer){
            while (true){
                buffer.add(RandomUtil.getRandom());
                buffer.wait(delay);
            }
        }
    }
}
