package Projekt07;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker("Worker1");
        worker.setListener(new classWorkerListener());


        worker.enqueueTask("One",new classTask());
        worker.enqueueTask("Two",new classTask());
        worker.enqueueTask("Three",new classTask());
        worker.enqueueTask("Four", new classHeavyTask());
        worker.enqueueTask("Five",new classYieldTask());
        // yield pauzuje obecnie wykonywany watek tymczasowo zeby dac szanse pozostalym wątkom o tej samej priorytecie mozliwosc wykonania.
        // Jesli pozostale maja mniejszy priorytet albo nie ma oczekujacych wtedy sie wykona.
        worker.start();
        worker.stop();

        System.out.println("checkpoint");

        worker.enqueueTask("One",new classTask());
        worker.enqueueTask("Two",new classTask());
        worker.enqueueTask("Three",new classTask());
        worker.enqueueTask("Four", new classHeavyTask());
        worker.enqueueTask("Five",new classYieldTask());
        worker.start();
        for(Thread thread:worker.getThreadarray()){
            thread.join();
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.stop();

    }
}
