package Projekt07;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class classTask implements Task {
    @Override
    public void run(int taskNumber) throws InterruptedException {
        Random rand = new Random();
        System.out.println("run number: "+taskNumber);
        Thread.sleep(10000);

    }
}
