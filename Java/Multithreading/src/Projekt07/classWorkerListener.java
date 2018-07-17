package Projekt07;

public class classWorkerListener implements WorkerListener{
    @Override
    public void onWorkerStarted() {
        System.out.println("Worker Started");
    }
    @Override
    public void onWorkerStoped() {
        System.out.println("Worker Stoped");
    }
    @Override
    public void onTaskStarted(int taskNumber, String taskName) {
        System.out.println("Task started: "+taskNumber+" "+taskName);
    }
    @Override
    public void onTaskCompleted(int taskNumber, String taskName) {
        System.out.println("Task Completed: "+taskNumber+" "+taskName);
    }
}
