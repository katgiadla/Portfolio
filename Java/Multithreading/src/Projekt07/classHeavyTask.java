package Projekt07;

import java.util.Random;

public class classHeavyTask implements Task {

    @Override
    public void run(int taskNumber) throws InterruptedException {
        System.out.println("HeavyTask");
        for (double i = 0; i < 80000000; i++) {
            double pow = Math.pow(i,1024)/i;
        }
    }
}
