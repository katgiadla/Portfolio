package Projekt07;

import java.util.Random;

public class classYieldTask implements Task {
    @Override
    public void run(int taskNumber) throws InterruptedException {
        Thread.yield();
    }
}
