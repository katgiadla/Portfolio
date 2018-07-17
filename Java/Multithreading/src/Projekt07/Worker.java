package Projekt07;
import sun.misc.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.lang.InterruptedException;
//import java.util.Random;

public class Worker {
    private volatile String workerName;
    private boolean isWorking;
    private boolean isStarted;
    private int threadCount;

    public Thread[] getThreadarray() {
        return threadarray;
    }

    private Thread [] threadarray;
    public Thread getThread() {
        return thread;
    }

    private Thread thread;
    Queue<Element>taskList;
    //private Random random;
    WorkerListener classWoLi;
    private int counter;

    public Worker(String Work){
        isWorking = false;
        isStarted = false;

        taskList = new Queue<>();
        workerName = "Worker "+Work+" thread";
        //random = new Random();
        counter = 0;
        threadCount = Runtime.getRuntime().availableProcessors();
        threadarray = new Thread[threadCount];
    }

    synchronized void enqueueTask(String taskName, Task task){
        taskList.enqueue(new Element(taskName, task));
        notify();
    }

    public synchronized void start(){
        if(isStarted){
            System.out.println("juz dziala ");
            return;
        }
        System.out.println("start");
        isStarted = true;
        isWorking = true;
        for(int i =0;i<threadCount;i++){
            int finalI = i;
            threadarray[i] = new Thread(() -> {

                String name = workerName;
                System.out.println("new Thread: "+ finalI);
                classWoLi.onWorkerStarted();
                while(isWorking){
                    System.out.println(name);
                    if(taskList.isEmpty() == true){
                        isWorking = false;
                        return;
                    }
                    try{
                        Element element = taskList.dequeue();
                        String string = element.getNameTask();
                        System.out.println(string +" is running");
                        Task task = element.getTask();

                        //task.run(random.nextInt(10));
                        classWoLi.onTaskStarted(counter,element.getNameTask());
                        task.run(counter);
                        classWoLi.onTaskCompleted(counter,element.getNameTask());
                        counter++;
                    }catch(InterruptedException e){
                        break;
                    }

                }
                classWoLi.onWorkerStoped();
            });
            threadarray[i].start();
        }


    }


    public synchronized void stop(){
        if(!isStarted){
            System.out.println("wylaczone");
            return;
        }
        System.out.println("stop");
        isWorking = false;
        isStarted = false;
        for(Thread thread:threadarray){
            thread.interrupt();
        }

        counter = 0;
        //isStarted = false;
    };

    void setListener( WorkerListener w){
        classWoLi = w;
    }

    public boolean isStarted(){
        if(isStarted)return true;
        else return false;
    }
    public boolean isWorkingd(){
        if(isWorking)return true;
        else return false;
    }
}
